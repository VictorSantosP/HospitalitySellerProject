package com.Hospitality.HospitalityWebsiteProject.user.services;

import com.Hospitality.HospitalityWebsiteProject.user.dto.UserRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        return null;
    }

    @Override
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public UserResponseDTO updateById(Long id, UserRequestDTO requestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO findALlByName(String name) {
        return null;
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        return null;
    }

    @Override
    public UserResponseDTO findAllByRole(UserRole role) {
        return null;
    }
}
