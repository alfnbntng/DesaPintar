package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.PengeluaranModel;
import com.desapintar.DesaPintar.Repository.PengeluaranRepository;
import com.desapintar.DesaPintar.Request.PengeluaranDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PengeluaranService {

    @Autowired
    private PengeluaranRepository pengeluaranRepository;

    @Autowired
    private SaldoService saldoService;

    public void addPengeluaran(PengeluaranDto pengeluaranDto) {
        PengeluaranModel pengeluaranModel = new PengeluaranModel();
        pengeluaranModel.setKegiatan(pengeluaranDto.getKegiatan());
        pengeluaranModel.setAnggaranKeluar(pengeluaranDto.getAnggaranKeluar());
        pengeluaranRepository.save(pengeluaranModel);

        // Mengurangkan jumlah pengeluaran dari saldo
        saldoService.kurangiSaldo(pengeluaranDto.getAnggaranKeluar());
    }

    public Page<PengeluaranModel> getAllPengeluaran(Pageable pageable) {
        return pengeluaranRepository.findAll(pageable);
    }

    public PengeluaranModel getDetailPengeluaran(Long id) {
        return pengeluaranRepository.findById(id).orElse(null);
    }

    public PengeluaranModel updatePengeluaran(Long id, PengeluaranDto pengeluaranDto) {
        PengeluaranModel pengeluaranModel = pengeluaranRepository.findById(id).orElse(null);
        if (pengeluaranModel != null) {
            // Mengurangkan jumlah pengeluaran sebelumnya dari saldo
            saldoService.tambahSaldo(pengeluaranModel.getAnggaranKeluar());

            // Memperbarui data pengeluaran
            pengeluaranModel.setKegiatan(pengeluaranDto.getKegiatan());
            pengeluaranModel.setAnggaranKeluar(pengeluaranDto.getAnggaranKeluar());

            // Mengurangkan jumlah pengeluaran yang baru dari saldo
            saldoService.kurangiSaldo(pengeluaranDto.getAnggaranKeluar());

            // Menyimpan data pengeluaran yang diperbarui
            return pengeluaranRepository.save(pengeluaranModel);
        }
        return null;
    }

    public void deletePengeluaran(Long id) {
        PengeluaranModel pengeluaranModel = pengeluaranRepository.findById(id).orElse(null);
        if (pengeluaranModel != null) {
            saldoService.tambahSaldo(pengeluaranModel.getAnggaranKeluar());
            pengeluaranRepository.deleteById(id);
        }
    }
}
