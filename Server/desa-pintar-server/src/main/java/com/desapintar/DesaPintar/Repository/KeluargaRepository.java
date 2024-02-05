package com.desapintar.DesaPintar.Repository;


import com.desapintar.DesaPintar.Model.KeluargaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface KeluargaRepository extends JpaRepository<KeluargaModel, Long> {

}
