package com.Project.StudentManagement.DTO;

import com.Project.StudentManagement.Entity.Attendance;
import com.Project.StudentManagement.Entity.Collage;
import com.Project.StudentManagement.Entity.Course;
import jakarta.validation.constraints.*;
import lombok.*;
import org.apache.logging.log4j.message.Message;

import javax.swing.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudentDTO {
    private Long id;

    @NotNull(message = "Field must not be null")
    private String name;

    @Email(message = "Email is wrong")
    private String email;

    @NotNull
    @Size(max = 10, min = 10,message = "Number must be 10 digit")
    private String phoneNumber;

    @Pattern(regexp = "CSE|IT|ME|CE|ECE", message = "Invalid branch")
    private String branch;

    @NotNull(message = "Collage ID required")
    @Min(value = 1, message = "Collage ID must be positive")
    private Long collage_id;

    @NotNull(message = "Course ID required")
    @Min(value = 1, message = "Course ID must be positive")
    private Long course_id;
}
