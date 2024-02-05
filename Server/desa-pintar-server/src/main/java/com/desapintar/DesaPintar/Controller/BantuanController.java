package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.BantuanModel;
import com.desapintar.DesaPintar.Request.BantuanDetailDto;
import com.desapintar.DesaPintar.Request.BantuanDto;
import com.desapintar.DesaPintar.Response.CommonResponse;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Service.BantuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/bantuan")
public class BantuanController {

    @Autowired
    private BantuanService bantuanService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<BantuanModel>>> getAllBantuan() {
        List<BantuanModel> listBantuan = bantuanService.getAllBantuan();
        CustomResponse<List<BantuanModel>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setData(listBantuan);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ById/{id}")
    public ResponseEntity<CustomResponse<BantuanModel>> getBantuanById(@PathVariable Long id) {
        BantuanModel bantuanModel = bantuanService.getById(id);
        CustomResponse<BantuanModel> response = new CustomResponse<>();

        if (bantuanModel != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(bantuanModel);
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Bantuan not found");
        }

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<BantuanModel>> addBantuan(@RequestBody BantuanDto bantuanDto) {
        BantuanModel bantuanModel = new BantuanModel();
        bantuanModel.setJenisBantuan(bantuanDto.getJenisBantuan());
        bantuanModel.setJumlahBantuan(bantuanDto.getJumlahBantuan());

        BantuanModel addedBantuan = bantuanService.addBantuan(bantuanModel);
        CustomResponse<BantuanModel> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(201);
        response.setData(addedBantuan);
        response.setMessage("Bantuan Sudah Di tambahkan");

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse<BantuanModel>> updateBantuan(
            @PathVariable Long id, @RequestBody BantuanDto bantuanDto) {
        BantuanModel updatedBantuan = new BantuanModel();
        updatedBantuan.setJenisBantuan(bantuanDto.getJenisBantuan());
        updatedBantuan.setJumlahBantuan(bantuanDto.getJumlahBantuan());

        BantuanModel result = bantuanService.updateBantuan(id, updatedBantuan);
        CustomResponse<BantuanModel> response = new CustomResponse<>();

        if (result != null) {
            response.setStatus("success");
            response.setCode(200);
            response.setData(result);
            response.setMessage("Bantuan sukses di perbarui");

        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Bantuan not found");
        }

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<String>> deleteBantuan(@PathVariable Long id) {
        bantuanService.deleteBantuan(id);
        CustomResponse<String> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setMessage("Bantuan deleted successfully");

        return ResponseEntity.status(response.getCode()).body(response);
    }
    @GetMapping("/getPenerimaByBantuan/{jenisBantuanId}")
    public ResponseEntity<CommonResponse<BantuanDetailDto>> getBantuanDetail(@PathVariable Long jenisBantuanId) {
        BantuanDetailDto bantuanDetailDto = bantuanService.getBantuanDetail(jenisBantuanId);

        CommonResponse<BantuanDetailDto> response = new CommonResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setData(bantuanDetailDto);
        response.setMessage("Bantuan details retrieved successfully");

        return ResponseEntity.ok(response);
    }
}
