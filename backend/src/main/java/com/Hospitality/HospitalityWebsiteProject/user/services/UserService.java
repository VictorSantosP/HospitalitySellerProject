package com.Hospitality.HospitalityWebsiteProject.user.services;

import com.Hospitality.HospitalityWebsiteProject.user.dto.UserRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.entity.UserEntity;
import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;
import com.Hospitality.HospitalityWebsiteProject.security.dto.RegisterRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    UserEntity registerUser (RegisterRequestDTO requestDTO);
    Page<UserResponseDTO> findAll (Pageable pageable);
    UserResponseDTO findById (Long id);
    void deleteById (Long id);
    UserResponseDTO updateById (Long id, UserRequestDTO requestDTO);
    List<UserResponseDTO> findAllByNameContaining(String name);
    UserResponseDTO findByEmail(String email);
    List<UserResponseDTO> findAllByRole(UserRole role);
}
