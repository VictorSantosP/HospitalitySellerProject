package com.Hospitality.HospitalityWebsiteProject.user.repository;

import com.Hospitality.HospitalityWebsiteProject.user.entity.UserEntity;
import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
    Optional<List<UserEntity>> findAllByNameContaining(String name);
    Optional<UserEntity> findByEmail(String email);
    Optional<List<UserEntity>> findAllByRole(UserRole role);
    Boolean existsByEmail (String email);
}
