package com.Project.StudentManagement.Repository;

import com.Project.StudentManagement.Entity.User;
import com.Project.StudentManagement.StudentStatus.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<User>findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);

}
