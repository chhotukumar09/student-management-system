package com.Project.StudentManagement.Controller;

import com.Project.StudentManagement.DTO.AttendanceDTO;
import com.Project.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
@RequiredArgsConstructor
public class TeacherController {
    private final StudentService studentService;

    //delete Attendance
    @DeleteMapping("/delete/{id}")
    public void deleteId(@Valid @PathVariable Long id){
        studentService.deleteAtt(id);
    }

    //add new attendance
    @PostMapping("/add")
    public AttendanceDTO newAttendanceADD(@Valid @RequestBody AttendanceDTO attendanceDTO){
        return studentService.addAttendance(attendanceDTO);
    }
}
