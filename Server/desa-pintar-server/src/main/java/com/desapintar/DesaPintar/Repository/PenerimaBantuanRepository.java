package com.desapintar.DesaPintar.Repository;

import com.desapintar.DesaPintar.Model.PenerimaBantuanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenerimaBantuanRepository extends JpaRepository<PenerimaBantuanModel, Long> {
    // Custom queries or methods can be added here if needed
}
