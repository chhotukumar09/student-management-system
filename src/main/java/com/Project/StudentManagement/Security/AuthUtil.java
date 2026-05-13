package com.Project.StudentManagement.Security;

import com.Project.StudentManagement.Exception.ResourceNotFoundException;
import com.Project.StudentManagement.StudentStatus.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.UserTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.AuthProvider;
import java.security.Key;
import java.security.KeyStore;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

@Component
@Slf4j
public class AuthUtil {
    private final String secreteKey = "ujhgfdkjahfvyasdrlukyfjgevfwehfguytawefr804764gcb";

    private Key getKey(){
        return Keys.hmacShaKeyFor(secreteKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey())
                .compact();

    }

    public String validateToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();

        }catch (Exception e){
            throw new ResourceNotFoundException("Invalid Token");
        }
    }
    public AuthProviderType getAuthProviderTypeFormRegistration(String registrationId){
        return switch (registrationId.toLowerCase()) {
            case "google" -> AuthProviderType.GOOGLE;
            case "facebook" -> AuthProviderType.FACEBOOK;
            case "github" -> AuthProviderType.GITHUB;
            default -> throw new ResourceNotFoundException("Unsupported Oauth2 provider");
        };
    }

    public String determineProviderIdFromOauth2User(OAuth2User oAuth2User, String registerId){
        String providerId = switch (registerId.toLowerCase()){
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();
            default ->{
                log.error("Unsupported Oauth2 provider: {}", registerId);
                throw new ResourceNotFoundException("Invalid Oauth2 provider");
            }
        };
       return providerId;
    }
    public String determineUsernameFromOauth2User(OAuth2User oAuth2User, String registerId, String providerId){
        String email = oAuth2User.getAttribute("email");
        if (email != null && !email.isBlank()){
            return email;
        }
        return switch (registerId.toLowerCase()){
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("login");
            default -> registerId;
        };
    }
}
