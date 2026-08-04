package com.Hospitality.HospitalityWebsiteProject.security.service;


import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public LoginResponseDTO login (LoginRequestDTO dto){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
        );

        Authentication authentication =
                authenticationManager.authenticate(token);

        UserEntity user =
                (UserEntity) authentication.getPrincipal();

        String jwt =
                jwtService.generateToken(user);

        return new LoginResponseDTO(jwt);
    }
}
