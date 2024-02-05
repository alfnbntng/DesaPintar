package com.desapintar.DesaPintar.Repository;


import com.desapintar.DesaPintar.Model.GaleriModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GaleriRepository extends JpaRepository<GaleriModel, Long> {
    GaleriModel findById(long id);

}
