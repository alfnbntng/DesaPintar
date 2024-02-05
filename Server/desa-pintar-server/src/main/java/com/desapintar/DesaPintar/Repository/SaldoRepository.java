package com.desapintar.DesaPintar.Repository;

import com.desapintar.DesaPintar.Model.SaldoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoModel, Long> {
}
