package com.desapintar.DesaPintar.Service;

import com.desapintar.DesaPintar.Model.BantuanModel;
import com.desapintar.DesaPintar.Model.PenerimaBantuanModel;
import com.desapintar.DesaPintar.Repository.BantuanRepository;
import com.desapintar.DesaPintar.Request.BantuanDetailDto;
import com.desapintar.DesaPintar.Request.PenerimaBantuanDetailDto;
import com.desapintar.DesaPintar.Request.PenerimaBantuanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BantuanService {

    @Autowired
    private BantuanRepository bantuanRepository;

    public List<BantuanModel> getAllBantuan() {
        return bantuanRepository.findAll();
    }

    public BantuanModel getById(Long id) {
        Optional<BantuanModel> optionalBantuan = bantuanRepository.findById(id);
        return optionalBantuan.orElse(null);
    }

    public BantuanModel addBantuan(BantuanModel bantuanModel) {
        return bantuanRepository.save(bantuanModel);
    }

    public BantuanModel updateBantuan(Long id, BantuanModel updatedBantuan) {
        BantuanModel bantuanModel = getById(id);

        if (bantuanModel != null) {
            bantuanModel.setJenisBantuan(updatedBantuan.getJenisBantuan());
            bantuanModel.setJumlahBantuan(updatedBantuan.getJumlahBantuan());

            return bantuanRepository.save(bantuanModel);
        }

        return null;
    }

    public BantuanModel getBantuanById(Long id) {
        Optional<BantuanModel> optionalBantuan = bantuanRepository.findById(id);
        return optionalBantuan.orElse(null);
    }

    public void deleteBantuan(Long id) {
        bantuanRepository.deleteById(id);
    }

    private List<PenerimaBantuanDetailDto> mapToPenerimaBantuanDtoList(List<PenerimaBantuanModel> penerimaBantuanList) {
        List<PenerimaBantuanDetailDto> penerimaBantuanDtoList = new ArrayList<>();
        for (PenerimaBantuanModel penerimaBantuan : penerimaBantuanList) {
            PenerimaBantuanDetailDto penerimaBantuanDto = new PenerimaBantuanDetailDto();
            penerimaBantuanDto.setKepalaKeluarga(penerimaBantuan.getKeluarga().getKepala_keluarga());
            penerimaBantuanDto.setNoKK(penerimaBantuan.getKeluarga().getNo_kk());
            penerimaBantuanDto.setAlamat(penerimaBantuan.getKeluarga().getAlamat());
            penerimaBantuanDtoList.add(penerimaBantuanDto);
        }
        return penerimaBantuanDtoList;
    }

    public BantuanDetailDto getBantuanDetail(Long id) {
        BantuanModel bantuan = getBantuanById(id);
        if (bantuan != null) {
            BantuanDetailDto bantuanDetailDto = new BantuanDetailDto();
            bantuanDetailDto.setJenisBantuan(bantuan.getJenisBantuan());
            bantuanDetailDto.setJumlahBantuan(bantuan.getJumlahBantuan());
            List<PenerimaBantuanDetailDto> penerimaBantuanDtoList = mapToPenerimaBantuanDtoList(bantuan.getPenerimaBantuans());
            bantuanDetailDto.setPenerima(penerimaBantuanDtoList);
            return bantuanDetailDto;
        }
        return null;
    }

}
