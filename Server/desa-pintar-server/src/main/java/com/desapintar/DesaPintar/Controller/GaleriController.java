package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.GaleriModel;
import com.desapintar.DesaPintar.Request.GaleriRequestDto;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Response.GaleriResponseDto;
import com.desapintar.DesaPintar.Service.GaleriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/galeri")
public class GaleriController {

    @Autowired
    private GaleriService galeriService;

    @GetMapping("/all")
    public List<GaleriResponseDto> getAllGaleri() {
        return galeriService.getAllGaleri();
    }

    @GetMapping("/ById/{id}")
    public ResponseEntity<GaleriResponseDto> getGaleriById(@PathVariable Long id) {
        return galeriService.getGaleriById(id)
                .map(galeri -> new ResponseEntity<>(galeri, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<CustomResponse<GaleriModel>> createGaleri(@ModelAttribute GaleriRequestDto galeriRequestDto, @RequestPart("file") MultipartFile multipartFile) {
        CustomResponse<GaleriModel> response = new CustomResponse<>();
        try {
            GaleriModel savedGaleri = galeriService.saveGaleri(galeriRequestDto, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(savedGaleri);
            response.setMessage("Galeri berhasil dibuat.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Gagal membuat Galeri: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "multipart/form-data")
    public ResponseEntity<CustomResponse<GaleriModel>> updateGaleri(@PathVariable("id") Long id, @ModelAttribute GaleriRequestDto galeriRequestDto, @RequestPart("file") MultipartFile multipartFile) {
        CustomResponse<GaleriModel> response = new CustomResponse<>();
        try {
            GaleriModel updatedGaleri = galeriService.updateGaleri(id, galeriRequestDto, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(updatedGaleri);
            response.setMessage("Galeri berhasil diperbarui.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Gagal memperbarui Galeri: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGaleri(@PathVariable Long id) {
        galeriService.deleteGaleri(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
