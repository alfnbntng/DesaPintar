package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.KeluargaModel;
import com.desapintar.DesaPintar.Request.KeluargaDto;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Service.KeluargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/keluarga")
public class KeluargaController {

    @Autowired
    private KeluargaService keluargaService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<Page<KeluargaModel>>> getAllKeluarga(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<KeluargaModel> keluargas = keluargaService.getAllKeluarga(page, size);
        CustomResponse<Page<KeluargaModel>> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.OK.value());
        response.setData(keluargas);
        response.setMessage("Daftar keluarga berhasil diambil");
        return ResponseEntity.ok(response);
    }
    @GetMapping("/ById{id}")
    public ResponseEntity<CustomResponse<Optional<KeluargaModel>>> getKeluargaById(@PathVariable Long id) {
        Optional<KeluargaModel> keluarga = keluargaService.getKeluargaById(id);
        CustomResponse<Optional<KeluargaModel>> response = new CustomResponse<>();
        if (keluarga.isPresent()) {
            response.setStatus("sukses");
            response.setCode(HttpStatus.OK.value());
            response.setData(keluarga);
            response.setMessage("Keluarga berhasil diambil");
            return ResponseEntity.ok(response);
        } else {
            response.setStatus("gagal");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Keluarga tidak ditemukan dengan ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<KeluargaModel>> saveKeluarga(@RequestBody KeluargaDto keluargaDto) {
        // Mengonversi KeluargaDto menjadi KeluargaModel
        KeluargaModel keluargaModel = convertToKeluargaModel(keluargaDto);
        keluargaModel.setJumlah_keluarga(keluargaDto.getJumlah_keluarga()); // Set jumlah_keluarga
        KeluargaModel savedKeluarga = keluargaService.saveKeluarga(keluargaModel);
        CustomResponse<KeluargaModel> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.CREATED.value());
        response.setData(savedKeluarga);
        response.setMessage("Keluarga berhasil disimpan");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse<KeluargaModel>> updateKeluarga(@PathVariable Long id, @RequestBody KeluargaDto keluargaDto) {
        // Mengonversi KeluargaDto menjadi KeluargaModel
        KeluargaModel keluargaModel = convertToKeluargaModel(keluargaDto);
        keluargaModel.setJumlah_keluarga(keluargaDto.getJumlah_keluarga());
        keluargaModel.setId(id);
        KeluargaModel updatedKeluarga = keluargaService.saveKeluarga(keluargaModel);
        CustomResponse<KeluargaModel> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.OK.value());
        response.setData(updatedKeluarga);
        response.setMessage("Keluarga berhasil diperbarui");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteKeluarga(@PathVariable Long id) {
        keluargaService.deleteKeluarga(id);
        CustomResponse<Void> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Keluarga berhasil dihapus");
        return ResponseEntity.ok(response);
    }

    private KeluargaModel convertToKeluargaModel(KeluargaDto keluargaDto) {
        KeluargaModel keluargaModel = new KeluargaModel();
        keluargaModel.setKepala_keluarga(keluargaDto.getKepala_keluarga());
        keluargaModel.setNo_kk(keluargaDto.getNo_kk());
        keluargaModel.setAlamat(keluargaDto.getAlamat());
        return keluargaModel;
    }
}
