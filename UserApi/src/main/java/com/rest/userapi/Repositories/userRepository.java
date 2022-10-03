package com.rest.userapi.Repositories;

import com.rest.userapi.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepository extends JpaRepository<user, Integer> {
    List<user> findBynameContaining(String name);
}
