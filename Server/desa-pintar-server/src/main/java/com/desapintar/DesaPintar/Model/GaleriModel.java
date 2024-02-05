package com.desapintar.DesaPintar.Model;

import com.desapintar.DesaPintar.Auditing.DateConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "galeri")
public class GaleriModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_media")
    private String namaMedia;
    private String foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaMedia() {
        return namaMedia;
    }

    public void setNamaMedia(String namaMedia) {
        this.namaMedia = namaMedia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
