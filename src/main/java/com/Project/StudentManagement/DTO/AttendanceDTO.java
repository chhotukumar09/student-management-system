package com.Project.StudentManagement.DTO;

import com.Project.StudentManagement.StudentStatus.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    private Long id;
    @NotBlank(message = "Roll number is require")
    @Size(min = 4, max = 10)
    private String rollnumber;

    @NotNull(message = "Status is require")
    private Status status;

    @NotNull(message = "Student ID is required")
    private Long studentId;
}
