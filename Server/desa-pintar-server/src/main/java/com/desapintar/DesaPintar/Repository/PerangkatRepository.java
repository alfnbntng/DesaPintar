package com.desapintar.DesaPintar.Repository;


import com.desapintar.DesaPintar.Model.PerangkatModel;
import com.desapintar.DesaPintar.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PerangkatRepository extends JpaRepository<PerangkatModel, Long> {
    PerangkatModel findById(long id);

}
