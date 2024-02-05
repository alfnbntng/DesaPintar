package com.desapintar.DesaPintar.Request;

public class RencanaKerjaDto {
    private String rencana_kerja;
    private String anggaran;
    private String status;

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
