package com.Hospitality.HospitalityWebsiteProject.security.service;


import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public LoginResponseDTO login (LoginRequestDTO dto){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
        );

        Authentication authentication =
                authenticationManager.authenticate(token);

        return new LoginResponseDTO(
                "Login realizado com sucesso!"
        );
    }
}
