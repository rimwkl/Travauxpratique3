package com.example.travp3.security.repo;

import com.example.travp3.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    //chercher user par son username
    AppUser findByUsername(String username);
}
