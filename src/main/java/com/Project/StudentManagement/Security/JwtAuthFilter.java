package com.Project.StudentManagement.Security;


import com.Project.StudentManagement.Entity.User;
import com.Project.StudentManagement.Exception.ResourceNotFoundException;
import com.Project.StudentManagement.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try {
           log.info("Incoming request" + request.getRequestURI());
           final String authHeader = request.getHeader("Authorization");
           if (authHeader == null || !authHeader.startsWith("Bearer ")) {
               filterChain.doFilter(request, response);
               return;
           }
           String token = authHeader.substring(7);
           String username = authUtil.validateToken(token);
           if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               User user = userRepository.findByUsername(username).orElseThrow(()
                       -> new ResourceNotFoundException("Username not found"));
               UsernamePasswordAuthenticationToken tokenAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(tokenAuth);
           }
           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           System.out.println(auth.getAuthorities());
           filterChain.doFilter(request, response);
       }catch (Exception e) {
           log.error(e.getMessage());
       }
    }
}
