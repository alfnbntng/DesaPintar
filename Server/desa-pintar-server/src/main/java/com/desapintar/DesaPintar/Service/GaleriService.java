package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.GaleriModel;
import com.desapintar.DesaPintar.Repository.GaleriRepository;
import com.desapintar.DesaPintar.Request.GaleriRequestDto;
import com.desapintar.DesaPintar.Response.GaleriResponseDto;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GaleriService {

    @Autowired
    private GaleriRepository galeriRepository;

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/desapintar-f5706.appspot.com/o/%s?alt=media";

    public List<GaleriResponseDto> getAllGaleri() {
        List<GaleriModel> galeri = galeriRepository.findAll();
        return galeri.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<GaleriResponseDto> getGaleriById(Long id) {
        return galeriRepository.findById(id)
                .map(this::convertToDTO);
    }
    public Optional<GaleriModel> findById(Long id) {
        return galeriRepository.findById(id);
    }


    public GaleriModel saveGaleri(GaleriRequestDto galeriRequestDto, MultipartFile multipartFile) throws Exception {
        try {
            GaleriModel newGaleri = new GaleriModel();
            String foto = imageConverter(multipartFile);
            newGaleri.setFoto(foto);
            newGaleri.setNamaMedia(galeriRequestDto.getNamaMedia());
            return galeriRepository.save(newGaleri);
        } catch (Exception e) {
            throw new Exception("Gagal menyimpan Media: " + e.getMessage());
        }
    }

    public GaleriModel updateGaleri(Long id, GaleriRequestDto galeriRequestDto, MultipartFile multipartFile) throws Exception {
        Optional<GaleriModel> optionalGaleriModel = galeriRepository.findById(id);
        if (optionalGaleriModel.isPresent()) {
            try {
                GaleriModel galeri = optionalGaleriModel.get();
                String foto = imageConverter(multipartFile);
                galeri.setNamaMedia(galeriRequestDto.getNamaMedia());
                galeri.setFoto(foto);
                return galeriRepository.save(galeri);
            } catch (Exception e) {
                throw new Exception("Gagal memperbarui Media: " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Media tidak ditemukan dengan id: " + id);
        }
    }


    public void deleteGaleri(Long id) {
        galeriRepository.deleteById(id);
    }


    private GaleriResponseDto convertToDTO(GaleriModel carousel) {
        GaleriResponseDto dto = new GaleriResponseDto();
        dto.setId(carousel.getId());
        dto.setNamaMedia(carousel.getNamaMedia());
        dto.setFoto(carousel.getFoto());
        return dto;
    }

    private String imageConverter(MultipartFile multipartFile) throws Exception {
        try {
            String fileName = getExtension(multipartFile.getOriginalFilename());
            File file = convertFile(multipartFile, fileName);
            var RESPONSE_URL = uploadFile(file, fileName);
            file.delete();
            return RESPONSE_URL;
        } catch (Exception e) {
            e.getStackTrace();
            throw new Exception("Error upload file!");
        }
    }

    private String getExtension(String fileName) {
        return  fileName.split("\\.")[0];
    }

    private File convertFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return file;
    }

    private String uploadFile(File file, String fileName) throws IOException {
        try {
            BlobId blobId = BlobId.of("desapintar-f5706.appspot.com", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("desa-pintar-firebase.json");
            Credentials credentials = GoogleCredentials.fromStream(serviceAccount);
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            storage.create(blobInfo, Files.readAllBytes(file.toPath()));
            return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error uploading file to Google Cloud Storage: " + e.getMessage());
        }
    }

}

