package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.PerangkatModel;
import com.desapintar.DesaPintar.Repository.PerangkatRepository;
import com.desapintar.DesaPintar.Request.PerangkatRequestDto;
import com.desapintar.DesaPintar.Response.PerangkatResponseDto;
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
public class PerangkatService {

    @Autowired
    private PerangkatRepository perangkatRepository;

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/desapintar-f5706.appspot.com/o/%s?alt=media";

    public List<PerangkatResponseDto> getAllPerangkat() {
        List<PerangkatModel> perangkats = perangkatRepository.findAll();
        return perangkats.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PerangkatResponseDto> getPerangkatById(Long id) {
        return perangkatRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PerangkatModel savePerangkat(PerangkatRequestDto perangkatRequestDto, MultipartFile multipartFile) throws Exception {
        try {
            PerangkatModel newPerangkat = new PerangkatModel();
            String foto = imageConverter(multipartFile);
            newPerangkat.setFoto(foto);
            newPerangkat.setNip(perangkatRequestDto.getNip());
            newPerangkat.setNama(perangkatRequestDto.getNama());
            newPerangkat.setJabatan(perangkatRequestDto.getJabatan());
            return perangkatRepository.save(newPerangkat);
        } catch (Exception e) {
            throw new Exception("Gagal menyimpan Perangkat: " + e.getMessage());
        }
    }

    public PerangkatModel updatePerangkat(Long id, PerangkatRequestDto perangkatRequestDto, MultipartFile multipartFile) throws Exception {
        Optional<PerangkatModel> optionalPerangkatModel = perangkatRepository.findById(id);
        if (optionalPerangkatModel.isPresent()) {
            try {
                PerangkatModel perangkat = optionalPerangkatModel.get();
                String foto = imageConverter(multipartFile);
                perangkat.setNip(perangkatRequestDto.getNip());
                perangkat.setNama(perangkatRequestDto.getNama());
                perangkat.setJabatan(perangkatRequestDto.getJabatan());
                perangkat.setFoto(foto);
                return perangkatRepository.save(perangkat);
            } catch (Exception e) {
                throw new Exception("Gagal memperbarui Perangkat: " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Perangkat tidak ditemukan dengan id: " + id);
        }
    }

    public void deletePerangkat(Long id) {
        perangkatRepository.deleteById(id);
    }

    private PerangkatResponseDto convertToDTO(PerangkatModel perangkat) {
        PerangkatResponseDto dto = new PerangkatResponseDto();
        dto.setId(perangkat.getId());
        dto.setNip(perangkat.getNip());
        dto.setNama(perangkat.getNama());
        dto.setJabatan(perangkat.getJabatan());
        dto.setFoto(perangkat.getFoto());
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
