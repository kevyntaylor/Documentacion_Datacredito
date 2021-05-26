package com.ebs.datacredito.repository;

import com.ebs.datacredito.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT x FROM User x WHERE x.email = ?1")
    Optional<User> findByEmail(String email);

}
