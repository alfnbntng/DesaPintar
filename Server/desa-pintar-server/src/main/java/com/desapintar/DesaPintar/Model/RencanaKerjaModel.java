package com.desapintar.DesaPintar.Model;

import com.desapintar.DesaPintar.Auditing.DateConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "galeri")
public class RencanaKerjaModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rencana_kerja")
    private String rencana_kerja;
    private String anggaran;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRencana_kerja() {
        return rencana_kerja;
    }

    public void setRencana_kerja(String rencana_kerja) {
        this.rencana_kerja = rencana_kerja;
    }

    public String getAnggaran() {
        return anggaran;
    }

    public void setAnggaran(String anggaran) {
        this.anggaran = anggaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
