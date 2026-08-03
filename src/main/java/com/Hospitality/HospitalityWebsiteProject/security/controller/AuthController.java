package com.Hospitality.HospitalityWebsiteProject.security.controller;


import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO dto){
        LoginResponseDTO response = service.login(dto);

        return ResponseEntity.ok(response);
    }
}
