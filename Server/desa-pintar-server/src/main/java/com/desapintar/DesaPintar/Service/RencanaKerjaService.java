package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.RencanaKerjaModel;
import com.desapintar.DesaPintar.Repository.RencanaKerjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RencanaKerjaService {

    @Autowired
    private RencanaKerjaRepository rencanaKerjaRepository;

    public List<RencanaKerjaModel> getAllRencanaKerja() {
        return rencanaKerjaRepository.findAll();
    }

    public Optional<RencanaKerjaModel> getRencanaKerjaById(Long id) {
        return rencanaKerjaRepository.findById(id);
    }

    public RencanaKerjaModel saveRencanaKerja(RencanaKerjaModel rencanaKerjaModel) {
        return rencanaKerjaRepository.save(rencanaKerjaModel);
    }

    public void deleteRencanaKerja(Long id) {
        rencanaKerjaRepository.deleteById(id);
    }
}
