package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.RencanaKerjaModel;
import com.desapintar.DesaPintar.Request.RencanaKerjaDto;
import com.desapintar.DesaPintar.Response.CustomResponse;
import com.desapintar.DesaPintar.Service.RencanaKerjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/rencana-kerja")
public class RencanaKerjaController {

    @Autowired
    private RencanaKerjaService rencanaKerjaService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<RencanaKerjaModel>>> getAllRencanaKerja() {
        List<RencanaKerjaModel> rencanaKerjas = rencanaKerjaService.getAllRencanaKerja();
        CustomResponse<List<RencanaKerjaModel>> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.OK.value());
        response.setData(rencanaKerjas);
        response.setMessage("Daftar Rencana Kerja berhasil diambil");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ById/{id}")
    public ResponseEntity<CustomResponse<Optional<RencanaKerjaModel>>> getRencanaKerjaById(@PathVariable Long id) {
        Optional<RencanaKerjaModel> rencanaKerja = rencanaKerjaService.getRencanaKerjaById(id);
        CustomResponse<Optional<RencanaKerjaModel>> response = new CustomResponse<>();
        if (rencanaKerja.isPresent()) {
            response.setStatus("sukses");
            response.setCode(HttpStatus.OK.value());
            response.setData(rencanaKerja);
            response.setMessage("Rencana Kerja berhasil diambil");
            return ResponseEntity.ok(response);
        } else {
            response.setStatus("gagal");
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Rencana Kerja tidak ditemukan dengan ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<RencanaKerjaModel>> saveRencanaKerja(@RequestBody RencanaKerjaDto rencanaKerjaDto) {
        // Mengonversi RencanaKerjaDto menjadi RencanaKerjaModel
        RencanaKerjaModel rencanaKerjaModel = convertToRencanaKerjaModel(rencanaKerjaDto);
        RencanaKerjaModel savedRencanaKerja = rencanaKerjaService.saveRencanaKerja(rencanaKerjaModel);
        CustomResponse<RencanaKerjaModel> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.CREATED.value());
        response.setData(savedRencanaKerja);
        response.setMessage("Rencana Kerja berhasil disimpan");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse<RencanaKerjaModel>> updateRencanaKerja(@PathVariable Long id, @RequestBody RencanaKerjaDto rencanaKerjaDto) {
        // Mengonversi RencanaKerjaDto menjadi RencanaKerjaModel
        RencanaKerjaModel rencanaKerjaModel = convertToRencanaKerjaModel(rencanaKerjaDto);
        // Mengasumsikan bahwa id di dalam path dan id di dalam objek sama
        rencanaKerjaModel.setId(id);
        RencanaKerjaModel updatedRencanaKerja = rencanaKerjaService.saveRencanaKerja(rencanaKerjaModel);
        CustomResponse<RencanaKerjaModel> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.OK.value());
        response.setData(updatedRencanaKerja);
        response.setMessage("Rencana Kerja berhasil diperbarui");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteRencanaKerja(@PathVariable Long id) {
        rencanaKerjaService.deleteRencanaKerja(id);
        CustomResponse<Void> response = new CustomResponse<>();
        response.setStatus("sukses");
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Rencana Kerja berhasil dihapus");
        return ResponseEntity.ok(response);
    }

    // Metode untuk mengonversi RencanaKerjaDto menjadi RencanaKerjaModel
    private RencanaKerjaModel convertToRencanaKerjaModel(RencanaKerjaDto rencanaKerjaDto) {
        RencanaKerjaModel rencanaKerjaModel = new RencanaKerjaModel();
        rencanaKerjaModel.setRencana_kerja(rencanaKerjaDto.getRencana_kerja());
        rencanaKerjaModel.setAnggaran(rencanaKerjaDto.getAnggaran());
        rencanaKerjaModel.setStatus(rencanaKerjaDto.getStatus());
        return rencanaKerjaModel;
    }
}
