package com.Hospitality.HospitalityWebsiteProject.user.services;

import com.Hospitality.HospitalityWebsiteProject.exception.DataIntegrityException;
import com.Hospitality.HospitalityWebsiteProject.exception.UserAlreadyExistsException;
import com.Hospitality.HospitalityWebsiteProject.exception.UserNotFoundException;
import com.Hospitality.HospitalityWebsiteProject.reservation.repository.ReservationRepository;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.entity.UserEntity;
import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;
import com.Hospitality.HospitalityWebsiteProject.user.mapper.UserMapper;
import com.Hospitality.HospitalityWebsiteProject.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        if(userRepository.existsByEmail(requestDTO.email())) {
            userRepository.findByEmail(requestDTO.email()).
                    orElseThrow(() -> new UserAlreadyExistsException(
                            "O usuário com o email cadastrado já existe."
                    ));
        }
        try{
            UserEntity user = userMapper.toEntity(requestDTO);

            user.setPassword(
                    passwordEncoder.encode(
                            user.getPassword()
                    )
            );

            if(user.getReservations() != null){
                user.getReservations().forEach(reservation -> reservation.setUser(user));
            }

            UserEntity saved = userRepository.saveAndFlush(user);

            return userMapper.toResponseDTO(saved);

        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }

    }

    @Override
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<UserEntity> users = userRepository.findAll(pageable);
        return users.map(userMapper::toResponseDTO);
    }

    @Override
    public UserResponseDTO findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuário não encontrado com o Id:" + id
                ));
        return userMapper.toResponseDTO(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuário não encontrado com o Id:" + id
                ));
        try{
            userRepository.delete(user);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }
    }

    @Override
    @Transactional
    public UserResponseDTO updateById(Long id, UserRequestDTO requestDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuário não encontrado com o Id:" + id
                ));
        try{
            if(!user.getName().equals(requestDTO.name())){
                user.setName(requestDTO.name());
            }
            if(!user.getEmail().equals(requestDTO.email())){
                user.setEmail(requestDTO.email());
            }
            if(!user.getPassword().equals(requestDTO.password())){
                user.setPassword(requestDTO.password());
            }
            if(!user.getPhone().equals(requestDTO.phone())){
                user.setPhone(requestDTO.phone());
            }
            if(!user.getRole().equals(requestDTO.role())){
                user.setRole(requestDTO.role());
            }
            UserEntity saved = userRepository.saveAndFlush(user);
            return userMapper.toResponseDTO(saved);

        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException(
                    "Erro de integridade de dados."
            );
        }

    }

    public List<UserResponseDTO> findAllByNameContaining(String name) {
        List<UserEntity> users = userRepository.findAllByNameContaining(name)
                .orElseThrow(() -> new UserNotFoundException("Não encontrado usuário com esse nome"));
        return users.stream().map(userMapper::toResponseDTO).collect(Collectors.toList());

    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuário não encontrado com o Email:" + email
                ));
        return userMapper.toResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> findAllByRole(UserRole role) {
        List<UserEntity> users = userRepository.findAllByRole(role)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuário não encontrado com o Role:" + role
                ));
        return users.stream().map(userMapper::toResponseDTO).collect(Collectors.toList());
    }
}
