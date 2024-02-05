package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.PerangkatModel;
import com.desapintar.DesaPintar.Request.PerangkatRequestDto;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Response.PerangkatResponseDto;
import com.desapintar.DesaPintar.Service.PerangkatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/perangkat")
public class PerangkatController {

    @Autowired
    private PerangkatService perangkatService;

    @GetMapping("/all")
    public List<PerangkatResponseDto> getAllPerangkat() {
        return perangkatService.getAllPerangkat();
    }

    @GetMapping("/ById/{id}")
    public ResponseEntity<PerangkatResponseDto> getPerangkatById(@PathVariable Long id) {
        return perangkatService.getPerangkatById(id)
                .map(perangkat -> new ResponseEntity<>(perangkat, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<CustomResponse<PerangkatModel>> createPerangkat(@ModelAttribute PerangkatRequestDto perangkatRequestDto, @RequestPart("file") MultipartFile multipartFile) {
        CustomResponse<PerangkatModel> response = new CustomResponse<>();
        try {
            PerangkatModel savedPerangkat = perangkatService.savePerangkat(perangkatRequestDto, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.CREATED.value());
            response.setData(savedPerangkat);
            response.setMessage("Perangkat berhasil dibuat.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Gagal membuat Perangkat: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "multipart/form-data")
    public ResponseEntity<CustomResponse<PerangkatModel>> updatePerangkat(@PathVariable("id") Long id, @ModelAttribute PerangkatRequestDto perangkatRequestDto, @RequestPart("file") MultipartFile multipartFile) {
        CustomResponse<PerangkatModel> response = new CustomResponse<>();
        try {
            PerangkatModel updatedPerangkat = perangkatService.updatePerangkat(id, perangkatRequestDto, multipartFile);
            response.setStatus("success");
            response.setCode(HttpStatus.OK.value());
            response.setData(updatedPerangkat);
            response.setMessage("Perangkat berhasil diperbarui.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setData(null);
            response.setMessage("Gagal memperbarui Perangkat: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerangkat(@PathVariable Long id) {
        perangkatService.deletePerangkat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
