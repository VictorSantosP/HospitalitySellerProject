package com.Hospitality.HospitalityWebsiteProject.security.service;


import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginRequestDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.LoginResponseDTO;
import com.Hospitality.HospitalityWebsiteProject.security.dto.RegisterRequestDTO;import com.Hospitality.HospitalityWebsiteProject.security.dto.RegisterResponseDTO;import com.Hospitality.HospitalityWebsiteProject.user.entity.UserEntity;
import com.Hospitality.HospitalityWebsiteProject.user.services.UserService;import jakarta.validation.constraints.NotNull;
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

    private final UserService userService;

    private final JwtService jwtService;

    public LoginResponseDTO login (LoginRequestDTO dto){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
        );

        Authentication authentication =
                authenticationManager.authenticate(token);

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String jwt = jwtService.generateToken(user);

        return new LoginResponseDTO(user.getName(), jwt);
    }

    public RegisterResponseDTO register (RegisterRequestDTO dto){
        UserEntity user = userService.registerUser(dto);

        String jwt = jwtService.generateToken(user);

        return new RegisterResponseDTO(user.getName(), jwt);

    }
}
