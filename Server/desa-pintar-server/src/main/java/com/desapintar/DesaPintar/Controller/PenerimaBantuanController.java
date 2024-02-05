package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.PenerimaBantuanModel;
import com.desapintar.DesaPintar.Request.PenerimaBantuanDto;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Service.PenerimaBantuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/penerima-bantuan")
public class PenerimaBantuanController {

    @Autowired
    private PenerimaBantuanService penerimaBantuanService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<PenerimaBantuanModel>>> getAllPenerimaBantuan() {
        List<PenerimaBantuanModel> penerimaBantuans = penerimaBantuanService.getAllPenerimaBantuan();
        CustomResponse<List<PenerimaBantuanModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setData(penerimaBantuans);
        response.setMessage("List of assistance recipients retrieved successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/ById/{id}")
    public ResponseEntity<CustomResponse<PenerimaBantuanModel>> getPenerimaBantuanById(@PathVariable Long id) {
        PenerimaBantuanModel penerimaBantuan = penerimaBantuanService.getPenerimaBantuanById(id);
        CustomResponse<PenerimaBantuanModel> response = new CustomResponse<>();
        if (penerimaBantuan != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(penerimaBantuan);
            response.setMessage("Assistance recipient details retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Assistance recipient not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<PenerimaBantuanModel>> addPenerimaBantuan(@RequestBody PenerimaBantuanDto penerimaBantuanDto) {
        PenerimaBantuanModel addedPenerimaBantuan = penerimaBantuanService.addPenerimaBantuan(penerimaBantuanDto);
        CustomResponse<PenerimaBantuanModel> response = new CustomResponse<>();

        if (addedPenerimaBantuan != null) {
            response.setStatus("success");
            response.setCode(201);
            response.setData(addedPenerimaBantuan);
            response.setMessage("Assistance recipient data added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Family not found or invalid family ID");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse<PenerimaBantuanModel>> updatePenerimaBantuan(@PathVariable Long id, @RequestBody PenerimaBantuanDto updatedPenerimaBantuanDto) {
        PenerimaBantuanModel updatedPenerimaBantuan = penerimaBantuanService.updatePenerimaBantuan(id, updatedPenerimaBantuanDto);
        CustomResponse<PenerimaBantuanModel> response = new CustomResponse<>();
        if (updatedPenerimaBantuan != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(updatedPenerimaBantuan);
            response.setMessage("Assistance recipient data updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Assistance recipient not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<String>> deletePenerimaBantuan(@PathVariable Long id) {
        penerimaBantuanService.deletePenerimaBantuan(id);
        CustomResponse<String> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setMessage("Assistance recipient deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
