package com.Project.StudentManagement.DTO;

import com.Project.StudentManagement.StudentStatus.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDTO {
    @NotBlank(message = "Username is require")
    @Size(min = 3, max = 30, message = "Username must be 3-20 character")
    private String username;

    @NotBlank(message = "Password must be require")
    @Size(min = 6, max = 6, message = "Password must will be 6 digit")
    private String password;
    private Role role;
}
