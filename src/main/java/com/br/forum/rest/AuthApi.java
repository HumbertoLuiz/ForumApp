package com.br.forum.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.forum.dtos.AuthRequest;
import com.br.forum.dtos.AuthResponse;
import com.br.forum.security.JwtTokenUtil;
import com.br.forum.services.AuthUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/login")
public class AuthApi {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication= authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));

            AuthUser user= (AuthUser) authentication.getPrincipal();
            String accessToken= jwtUtil.generateAccessToken(user);
            AuthResponse response= new AuthResponse(user.getUser().getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
