package com.desapintar.DesaPintar.Request;

public class KeluargaDto {
    private String kepala_keluarga;
    private String no_kk;
    private String alamat;
    private int jumlah_keluarga;

    public String getKepala_keluarga() {
        return kepala_keluarga;
    }

    public void setKepala_keluarga(String kepala_keluarga) {
        this.kepala_keluarga = kepala_keluarga;
    }

    public String getNo_kk() {
        return no_kk;
    }

    public void setNo_kk(String no_kk) {
        this.no_kk = no_kk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getJumlah_keluarga() {
        return jumlah_keluarga;
    }

    public void setJumlah_keluarga(int jumlah_keluarga) {
        this.jumlah_keluarga = jumlah_keluarga;
    }
}
