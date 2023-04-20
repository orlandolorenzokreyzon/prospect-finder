package com.kreyzon.prospectfinder.auth.repository;

import com.kreyzon.prospectfinder.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);

    @Query(value = "SELECT * FROM app_user ORDER BY user_id DESC LIMIT 1", nativeQuery = true)
    Optional<AppUser> findTopByOrderByIdDesc();
}