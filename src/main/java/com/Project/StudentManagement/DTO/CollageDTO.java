package com.Project.StudentManagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollageDTO {
    private Long id;
    @NotBlank(message = "Collage name must be not null")
    private String name;

    @NotBlank(message = "Establishedyear must required")
    private String enstablishedyear;

    @NotBlank(message = "Correct collage code require")
    private String code;

    @NotBlank(message = "Email is require")
    @Email(message = "Invalid email format")
    private String emailclg;
}
