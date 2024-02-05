package com.desapintar.DesaPintar.Repository;

import com.desapintar.DesaPintar.Model.RencanaKerjaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RencanaKerjaRepository extends JpaRepository<RencanaKerjaModel, Long> {
}
