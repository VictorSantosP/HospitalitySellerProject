package com.Hospitality.HospitalityWebsiteProject.security.controller;


import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.RegisterRequestDTO;import com.Hospitality.HospitalityWebsiteProject.security.dto.RegisterResponseDTO;import com.Hospitality.HospitalityWebsiteProject.security.service.AuthenticationService;
import com.Hospitality.HospitalityWebsiteProject.security.service.UserDetailsServiceImpl;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.user.dto.UserResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.entity.UserEntity;
import com.Hospitality.HospitalityWebsiteProject.user.services.UserService;import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO dto) {
        LoginResponseDTO response = authenticationService.login(dto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> createUser (@Valid @RequestBody RegisterRequestDTO dto){
        RegisterResponseDTO response = authenticationService.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
