package com.Project.StudentManagement.Controller;

import com.Project.StudentManagement.DTO.CourseDTO;
import com.Project.StudentManagement.DTO.StudentDTO;
import com.Project.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@PreAuthorize("hasAnyRole('STUDENT','TEACHER','ADMIN')")
@RequiredArgsConstructor
public class CourseController {
    private final StudentService studentService;
    // get all courses
    @GetMapping("/get/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(studentService.getAllCourses());
    }

       // get course by id
    @GetMapping("/get/{id}")
    public CourseDTO getCourse(@Valid @PathVariable Long id) {
        return studentService.getCourseBYID(id);
    }

}
