package com.desapintar.DesaPintar.Controller;

import com.desapintar.DesaPintar.Model.SaldoModel;
import com.desapintar.DesaPintar.Response.CommonResponse;
import com.desapintar.DesaPintar.Service.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/desa-pintar/api/saldo")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/check")
    public ResponseEntity<CommonResponse<Double>> checkSaldo() {
        SaldoModel saldoModel = saldoService.getSaldo();

        // Memastikan saldoModel tidak null sebelum mengambil nilai saldo
        if (saldoModel != null) {
            Double saldo = saldoModel.getJumlahSaldo();
            CommonResponse<Double> response = new CommonResponse<>();
            response.setStatus("success");
            response.setCode(200);
            response.setData(saldo);
            response.setMessage("Saldo checked successfully");
            return ResponseEntity.ok(response);
        } else {
            CommonResponse<Double> response = new CommonResponse<>();
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Saldo not found");
            return ResponseEntity.ok(response);
        }
    }
}
