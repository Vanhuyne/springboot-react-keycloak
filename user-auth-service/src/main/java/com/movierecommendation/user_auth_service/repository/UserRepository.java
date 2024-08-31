package com.movierecommendation.user_auth_service.repository;

import com.movierecommendation.user_auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
