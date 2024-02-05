// PenerimaBantuanService.java
package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.BantuanModel;
import com.desapintar.DesaPintar.Model.KeluargaModel;
import com.desapintar.DesaPintar.Model.PenerimaBantuanModel;
import com.desapintar.DesaPintar.Repository.BantuanRepository;
import com.desapintar.DesaPintar.Repository.KeluargaRepository;
import com.desapintar.DesaPintar.Repository.PenerimaBantuanRepository;
import com.desapintar.DesaPintar.Request.PenerimaBantuanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenerimaBantuanService {

    @Autowired
    private PenerimaBantuanRepository penerimaBantuanRepository;

    @Autowired
    private BantuanRepository bantuanRepository;

    @Autowired
    private KeluargaRepository keluargaRepository;


    public List<PenerimaBantuanModel> getAllPenerimaBantuan() {
        return penerimaBantuanRepository.findAll();
    }

    public PenerimaBantuanModel getPenerimaBantuanById(Long id) {
        return penerimaBantuanRepository.findById(id).orElse(null);
    }

    public PenerimaBantuanModel addPenerimaBantuan(PenerimaBantuanDto penerimaBantuanDto) {
        Optional<BantuanModel> bantuanOptional = bantuanRepository.findById(penerimaBantuanDto.getBantuanId());
        Optional<KeluargaModel> keluargaOptional = keluargaRepository.findById(penerimaBantuanDto.getKeluargaId());

        if (bantuanOptional.isPresent() && keluargaOptional.isPresent()) {
            BantuanModel bantuan = bantuanOptional.get();
            KeluargaModel keluarga = keluargaOptional.get();

            PenerimaBantuanModel penerimaBantuan = new PenerimaBantuanModel(bantuan, keluarga);
            return penerimaBantuanRepository.save(penerimaBantuan);
        } else {
            return null; // Handle jika entitas BantuanModel atau KeluargaModel tidak ditemukan
        }
    }

    public PenerimaBantuanModel updatePenerimaBantuan(Long id, PenerimaBantuanDto updatedPenerimaBantuanDto) {
        PenerimaBantuanModel penerimaBantuan = penerimaBantuanRepository.findById(id).orElse(null);

        if (penerimaBantuan != null) {
            Optional<BantuanModel> bantuanOptional = bantuanRepository.findById(updatedPenerimaBantuanDto.getBantuanId());
            Optional<KeluargaModel> keluargaOptional = keluargaRepository.findById(updatedPenerimaBantuanDto.getKeluargaId());

            if (bantuanOptional.isPresent() && keluargaOptional.isPresent()) {
                BantuanModel updatedBantuan = bantuanOptional.get();
                KeluargaModel updatedKeluarga = keluargaOptional.get();

                // Update properties from DTO to Model
                penerimaBantuan.setBantuan(updatedBantuan);
                penerimaBantuan.setKeluarga(updatedKeluarga);

                return penerimaBantuanRepository.save(penerimaBantuan);
            } else {
                return null; // Handle jika entitas BantuanModel atau KeluargaModel tidak ditemukan
            }
        }
        return null;
    }

    public void deletePenerimaBantuan(Long id) {
        penerimaBantuanRepository.deleteById(id);
    }
}
