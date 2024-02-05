package com.desapintar.DesaPintar.Model;

import com.desapintar.DesaPintar.Auditing.DateConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "keluarga")
public class KeluargaModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_kepala_keluarga")
    private String kepala_keluarga;
    @Column(length = 20, unique = true)
    private String no_kk;
    private String alamat;
    @Column(name = "jumlah_keluarga")
    private int jumlah_keluarga;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
