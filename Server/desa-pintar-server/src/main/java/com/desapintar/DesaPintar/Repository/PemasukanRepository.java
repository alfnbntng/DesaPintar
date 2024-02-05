package com.desapintar.DesaPintar.Repository;

import com.desapintar.DesaPintar.Model.PemasukanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PemasukanRepository extends JpaRepository<PemasukanModel, Long> {
}
