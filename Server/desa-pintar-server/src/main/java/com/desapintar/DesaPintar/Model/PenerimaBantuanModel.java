package com.desapintar.DesaPintar.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "penerima_bantuan")
public class PenerimaBantuanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bantuan_id")
    @JsonIgnore
    private BantuanModel bantuan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "keluarga_id")
    private KeluargaModel keluarga;

    public PenerimaBantuanModel() {
    }

    public PenerimaBantuanModel(BantuanModel bantuan, KeluargaModel keluarga) {
        this.bantuan = bantuan;
        this.keluarga = keluarga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BantuanModel getBantuan() {
        return bantuan;
    }

    public void setBantuan(BantuanModel bantuan) {
        this.bantuan = bantuan;
    }

    public KeluargaModel getKeluarga() {
        return keluarga;
    }

    public void setKeluarga(KeluargaModel keluarga) {
        this.keluarga = keluarga;
    }
}
