package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.PemasukanModel;
import com.desapintar.DesaPintar.Repository.PemasukanRepository;
import com.desapintar.DesaPintar.Request.PemasukanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PemasukanService {

    @Autowired
    private PemasukanRepository pemasukanRepository;

    @Autowired
    private SaldoService saldoService;

    public void addPemasukan(PemasukanDto pemasukanDto) {
        PemasukanModel pemasukanModel = new PemasukanModel();
        pemasukanModel.setNama(pemasukanDto.getNama());
        pemasukanModel.setJumlahPemasukan(pemasukanDto.getJumlahPemasukan());
        pemasukanRepository.save(pemasukanModel);

        // Menambahkan jumlah pemasukan ke saldo
        saldoService.tambahSaldo(pemasukanDto.getJumlahPemasukan());
    }

    public Page<PemasukanModel> getAllPemasukan(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pemasukanRepository.findAll(pageable);
    }
    public PemasukanModel getDetailPemasukan(Long id) {
        return pemasukanRepository.findById(id).orElse(null);
    }

    public PemasukanModel updatePemasukan(Long id, PemasukanDto pemasukanDto) {
        PemasukanModel pemasukanModel = pemasukanRepository.findById(id).orElse(null);
        if (pemasukanModel != null) {
            // Mengurangkan jumlah pemasukan sebelumnya dari saldo
            saldoService.kurangiSaldo(pemasukanModel.getJumlahPemasukan());

            // Memperbarui data pemasukan
            pemasukanModel.setNama(pemasukanDto.getNama());
            pemasukanModel.setJumlahPemasukan(pemasukanDto.getJumlahPemasukan());

            // Menambahkan jumlah pemasukan yang baru ke saldo
            saldoService.tambahSaldo(pemasukanDto.getJumlahPemasukan());

            // Menyimpan data pemasukan yang diperbarui
            return pemasukanRepository.save(pemasukanModel);
        }
        return null;
    }

    public void deletePemasukan(Long id) {
        PemasukanModel pemasukanModel = pemasukanRepository.findById(id).orElse(null);
        if (pemasukanModel != null) {
            // Mengurangkan jumlah pemasukan dari saldo sebelum menghapus
            saldoService.kurangiSaldo(pemasukanModel.getJumlahPemasukan());

            // Hapus data pemasukan
            pemasukanRepository.deleteById(id);
        }
    }
}
