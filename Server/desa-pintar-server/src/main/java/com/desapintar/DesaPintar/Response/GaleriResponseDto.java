package com.desapintar.DesaPintar.Response;

public class GaleriResponseDto {
    private Long id;
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
