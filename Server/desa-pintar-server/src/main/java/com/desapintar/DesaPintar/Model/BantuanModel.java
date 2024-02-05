package com.desapintar.DesaPintar.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bantuan")
public class BantuanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jenis_bantuan")
    private String jenisBantuan;

    @Column(name = "jumlah_bantuan")
    private Double jumlahBantuan;

    @OneToMany(mappedBy = "bantuan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PenerimaBantuanModel> penerimaBantuans;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenisBantuan() {
        return jenisBantuan;
    }

    public void setJenisBantuan(String jenisBantuan) {
        this.jenisBantuan = jenisBantuan;
    }

    public Double getJumlahBantuan() {
        return jumlahBantuan;
    }

    public void setJumlahBantuan(Double jumlahBantuan) {
        this.jumlahBantuan = jumlahBantuan;
    }

    public List<PenerimaBantuanModel> getPenerimaBantuans() {
        return penerimaBantuans;
    }

    public void setPenerimaBantuans(List<PenerimaBantuanModel> penerimaBantuans) {
        this.penerimaBantuans = penerimaBantuans;
    }
}
