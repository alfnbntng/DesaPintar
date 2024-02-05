package com.desapintar.DesaPintar.Repository;


import com.desapintar.DesaPintar.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);

    UserModel findById(long userId);
}
