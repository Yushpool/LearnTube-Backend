package com.learntube.learntube_backend.repository;

import com.learntube.learntube_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
