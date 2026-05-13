package com.Project.StudentManagement.Controller;

import com.Project.StudentManagement.DTO.AttendanceDTO;
import com.Project.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@PreAuthorize("hasAnyRole('STUDENT','TEACHER','ADMIN')")
@RequiredArgsConstructor
public class AttendanceController {
    private final StudentService studentService;

    //  All Attendance show
    @GetMapping("/get/all")
    public List<AttendanceDTO> allList(){
        return studentService.allAttendance();
    }

    // getById
    @GetMapping("/get/{id}")
    public AttendanceDTO getBYId(@Valid @PathVariable Long id){
        return studentService.FindById(id);
    }


}
