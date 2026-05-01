package com.Project.StudentManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
     @NotBlank(message = "Username must be required")
     @Size(min = 3, max = 20, message = "Username must be 3-20 characters")
    private String username;
    private String password;
}
