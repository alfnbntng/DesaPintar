package com.desapintar.DesaPintar.Model;

import com.desapintar.DesaPintar.Auditing.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "pengeluaran")
public class PengeluaranModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kegiatan")
    private String kegiatan;

    @Column(name = "anggaran_keluar")
    private Double anggaranKeluar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public Double getAnggaranKeluar() {
        return anggaranKeluar;
    }

    public void setAnggaranKeluar(Double anggaranKeluar) {
        this.anggaranKeluar = anggaranKeluar;
    }
}
