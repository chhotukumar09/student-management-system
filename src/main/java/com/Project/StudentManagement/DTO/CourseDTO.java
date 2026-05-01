package com.Project.StudentManagement.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long Id;
    @NotNull(message = "Course name is null")
    @Size(min = 3, max = 15)
    private String name;

    @NotNull(message = "Course code required")
    @Size(min = 2, max = 12)
    private String courseCode;
}
