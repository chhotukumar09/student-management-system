package com.Project.StudentManagement.Security;

import com.Project.StudentManagement.DTO.LoginDTO;
import com.Project.StudentManagement.DTO.LoginResponseDTO;
import com.Project.StudentManagement.DTO.UserSignupDTO;
import com.Project.StudentManagement.Entity.User;
import com.Project.StudentManagement.Exception.ResourceNotFoundException;
import com.Project.StudentManagement.Repository.UserRepository;
import com.Project.StudentManagement.StudentStatus.AuthProviderType;
import com.Project.StudentManagement.StudentStatus.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.prefs.BackingStoreException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginDTO loginDTO) {
        try {


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );


            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();


            String token = authUtil.generateToken(user);

            return new LoginResponseDTO(token, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // SIGNUP METHOD
    public String signup(UserSignupDTO signupDTO) {

        if (userRepository.existsByUsername(signupDTO.getUsername())) {
            throw new RuntimeException("Username already taken!");
        }

        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Transactional
    public ResponseEntity<LoginResponseDTO> handleOauth2LoginRequest(OAuth2User oAuth2User,
                                                                     String registerId){
        AuthProviderType providerType = authUtil.getAuthProviderTypeFormRegistration(registerId);
        String providerId = authUtil.determineProviderIdFromOauth2User(oAuth2User, registerId);
        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User emailUser = userRepository.findByUsername(email).orElse(null);

        if (user == null && emailUser == null){
            //signUp flow
            String username = authUtil.determineUsernameFromOauth2User(oAuth2User, registerId, providerId);
            User oauth2User = new User();
            oauth2User.setUsername(username);
            oauth2User.setPassword(null);
            oauth2User.setRole(Role.USER);
            oauth2User.setProviderId(providerId);
            oauth2User.setProviderType(providerType);
            user = userRepository.save(oauth2User);
        } else if (user != null) {
            if (email != null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);

            }
            
        }else {
            throw new ResourceNotFoundException("This email is already registered with provider " + emailUser.getProviderType());
        }
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(authUtil.generateToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDTO);
    }
}
