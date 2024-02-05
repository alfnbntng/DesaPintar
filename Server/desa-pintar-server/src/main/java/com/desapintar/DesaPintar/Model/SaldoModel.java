package com.desapintar.DesaPintar.Model;

import com.desapintar.DesaPintar.Auditing.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "saldo")
public class SaldoModel extends DateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double jumlahSaldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getJumlahSaldo() {
        return jumlahSaldo;
    }

    public void setJumlahSaldo(Double jumlahSaldo) {
        this.jumlahSaldo = jumlahSaldo;
    }
}
