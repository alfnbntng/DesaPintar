package com.desapintar.DesaPintar.Repository;

import com.desapintar.DesaPintar.Model.BantuanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BantuanRepository extends JpaRepository<BantuanModel, Long> {
    // Custom queries or methods can be added here if needed
}
