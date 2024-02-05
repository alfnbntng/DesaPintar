package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.PengeluaranModel;
import com.desapintar.DesaPintar.Request.PengeluaranDto;
import com.desapintar.DesaPintar.Response.CommonResponse;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Service.PengeluaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/pengeluaran")
public class PengeluaranController {

    @Autowired
    private PengeluaranService pengeluaranService;

    @GetMapping("/list")
    public ResponseEntity<CommonResponse<Page<PengeluaranModel>>> getAllPengeluaran(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<PengeluaranModel> pengeluaranPage = pengeluaranService.getAllPengeluaran(pageable);

        CommonResponse<Page<PengeluaranModel>> response = new CommonResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setData(pengeluaranPage);
        response.setMessage("Data retrieved successfully");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/add")
    public ResponseEntity<CommonResponse<PengeluaranModel>> addPengeluaran(@RequestBody PengeluaranDto pengeluaranDto) {
        pengeluaranService.addPengeluaran(pengeluaranDto);
        CommonResponse<PengeluaranModel> response = new CommonResponse<>();
        response.setStatus("success");
        response.setCode(201);
        response.setMessage("Pengeluaran added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CommonResponse<PengeluaranModel>> getDetailPengeluaran(@PathVariable Long id) {
        PengeluaranModel pengeluaran = pengeluaranService.getDetailPengeluaran(id);
        CommonResponse<PengeluaranModel> response = new CommonResponse<>();
        if (pengeluaran != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(pengeluaran);
            response.setMessage("Detail pengeluaran retrieved successfully");
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Pengeluaran not found");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CommonResponse<PengeluaranModel>> updatePengeluaran(
            @PathVariable Long id,
            @RequestBody PengeluaranDto pengeluaranDto
    ) {
        PengeluaranModel updatedPengeluaran = pengeluaranService.updatePengeluaran(id, pengeluaranDto);
        CommonResponse<PengeluaranModel> response = new CommonResponse<>();
        if (updatedPengeluaran != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(updatedPengeluaran);
            response.setMessage("Pengeluaran updated successfully");
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Pengeluaran not found");
        }
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<String>> deletePengeluaran(@PathVariable Long id) {
        pengeluaranService.deletePengeluaran(id);
        CustomResponse<String> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setMessage("Expense deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
