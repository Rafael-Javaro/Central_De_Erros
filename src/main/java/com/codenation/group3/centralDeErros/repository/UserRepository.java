package com.codenation.group3.centralDeErros.repository;

import com.codenation.group3.centralDeErros.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
