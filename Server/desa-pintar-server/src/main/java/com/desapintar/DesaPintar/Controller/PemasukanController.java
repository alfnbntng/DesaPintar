package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.PemasukanModel;
import com.desapintar.DesaPintar.Request.PemasukanDto;
import com.desapintar.DesaPintar.Response.CommonResponse;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Service.PemasukanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/pemasukan")
public class PemasukanController {

    @Autowired
    private PemasukanService pemasukanService;

    @GetMapping("/list")
    public ResponseEntity<CommonResponse<Page<PemasukanModel>>> getAllPemasukan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PemasukanModel> pemasukanPage = pemasukanService.getAllPemasukan(page, size);
        CommonResponse<Page<PemasukanModel>> response = new CommonResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setData(pemasukanPage);
        response.setMessage("Data retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<CommonResponse<PemasukanModel>> addPemasukan(@RequestBody PemasukanDto pemasukanDto) {
        pemasukanService.addPemasukan(pemasukanDto);
        CommonResponse<PemasukanModel> response = new CommonResponse<>();
        response.setStatus("success");
        response.setCode(201);
        response.setMessage("Pemasukan added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CommonResponse<PemasukanModel>> getDetailPemasukan(@PathVariable Long id) {
        PemasukanModel pemasukan = pemasukanService.getDetailPemasukan(id);
        CommonResponse<PemasukanModel> response = new CommonResponse<>();
        if (pemasukan != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(pemasukan);
            response.setMessage("Detail pemasukan retrieved successfully");
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Pemasukan not found");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CommonResponse<PemasukanModel>> updatePemasukan(
            @PathVariable Long id,
            @RequestBody PemasukanDto pemasukanDto
    ) {
        PemasukanModel updatedPemasukan = pemasukanService.updatePemasukan(id, pemasukanDto);
        CommonResponse<PemasukanModel> response = new CommonResponse<>();
        if (updatedPemasukan != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(updatedPemasukan);
            response.setMessage("Pemasukan updated successfully");
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Pemasukan not found");
        }
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<String>> deletePemasukan(@PathVariable Long id) {
        pemasukanService.deletePemasukan(id);
        CustomResponse<String> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setMessage("Income deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
