package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.KeluargaModel;
import com.desapintar.DesaPintar.Repository.KeluargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeluargaService {

    @Autowired
    private KeluargaRepository keluargaRepository;

    public Page<KeluargaModel> getAllKeluarga(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return keluargaRepository.findAll(pageable);
    }
    public Optional<KeluargaModel> getKeluargaById(Long id) {
        return keluargaRepository.findById(id);
    }

    public KeluargaModel saveKeluarga(KeluargaModel keluargaModel) {
        return keluargaRepository.save(keluargaModel);
    }

    public void deleteKeluarga(Long id) {
        keluargaRepository.deleteById(id);
    }
}
