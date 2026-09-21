package com.Hospitality.HospitalityWebsiteProject.user.controller;


import com.Hospitality.HospitalityWebsiteProject.user.dto.UserRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.enums.UserRole;
import com.Hospitality.HospitalityWebsiteProject.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser (@Valid @RequestBody UserRequestDTO dto){
        UserResponseDTO response = service.createUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<UserResponseDTO>> findAll (Pageable pageable){
        Page<UserResponseDTO> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<UserResponseDTO> findById (@PathVariable Long id){
        UserResponseDTO response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<UserResponseDTO> delete (@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto){
        UserResponseDTO response = service.updateById(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/name/{name}")
    public ResponseEntity<List<UserResponseDTO>> findAllByNameContaining(@PathVariable String name){
        List<UserResponseDTO> response = service.findAllByNameContaining(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email){
        UserResponseDTO response = service.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/role/{role}")
    public ResponseEntity<List<UserResponseDTO>> findAllByRole(@PathVariable UserRole role){
        List<UserResponseDTO> response = service.findAllByRole(role);
        return ResponseEntity.ok(response);

    }
}
