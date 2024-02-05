package com.desapintar.DesaPintar.Request;

import com.desapintar.DesaPintar.Request.PenerimaBantuanDetailDto;

import java.util.List;

public class BantuanDetailDto {
    private String jenisBantuan;
    private double jumlahBantuan;
    private List<PenerimaBantuanDetailDto> penerima;

    public String getJenisBantuan() {
        return jenisBantuan;
    }

    public void setJenisBantuan(String jenisBantuan) {
        this.jenisBantuan = jenisBantuan;
    }

    public double getJumlahBantuan() {
        return jumlahBantuan;
    }

    public void setJumlahBantuan(double jumlahBantuan) {
        this.jumlahBantuan = jumlahBantuan;
    }

    public List<PenerimaBantuanDetailDto> getPenerima() {
        return penerima;
    }

    public void setPenerima(List<PenerimaBantuanDetailDto> penerima) {
        this.penerima = penerima;
    }
}
