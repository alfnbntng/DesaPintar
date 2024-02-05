package com.desapintar.DesaPintar.Model;

import com.desapintar.DesaPintar.Auditing.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "pemasukan")
public class PemasukanModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private Double jumlahPemasukan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getJumlahPemasukan() {
        return jumlahPemasukan;
    }

    public void setJumlahPemasukan(Double jumlahPemasukan) {
        this.jumlahPemasukan = jumlahPemasukan;
    }
}
