package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.SaldoModel;
import com.desapintar.DesaPintar.Repository.SaldoRepository;
import com.desapintar.DesaPintar.Request.SaldoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {

    @Autowired
    private SaldoRepository saldoRepository;

    public SaldoModel getSaldo() {
        return saldoRepository.findById(1L).orElse(null);
    }

    public void tambahSaldo(Double jumlah) {
        SaldoModel saldoModel = getSaldo();
        if (saldoModel != null) {
            saldoModel.setJumlahSaldo(saldoModel.getJumlahSaldo() + jumlah);
            saldoRepository.save(saldoModel);
        }
    }

    public void kurangiSaldo(Double jumlah) {
        SaldoModel saldoModel = getSaldo();
        if (saldoModel != null) {
            saldoModel.setJumlahSaldo(saldoModel.getJumlahSaldo() - jumlah);
            saldoRepository.save(saldoModel);
        }
    }
}
