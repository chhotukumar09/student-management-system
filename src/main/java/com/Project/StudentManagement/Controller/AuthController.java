package com.Project.StudentManagement.Controller;

import com.Project.StudentManagement.DTO.LoginDTO;
import com.Project.StudentManagement.DTO.LoginResponseDTO;
import com.Project.StudentManagement.DTO.UserSignupDTO;
import com.Project.StudentManagement.Security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AuthController {

private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserSignupDTO signupDTO) {
        String message = authService.signup(signupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        System.out.println("LOGIN HIT: " + loginDTO.getUsername());
        LoginResponseDTO response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }
}
