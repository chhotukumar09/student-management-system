package com.Project.StudentManagement.Controller;

import com.Project.StudentManagement.DTO.AttendanceDTO;
import com.Project.StudentManagement.DTO.CollageDTO;
import com.Project.StudentManagement.DTO.CourseDTO;
import com.Project.StudentManagement.DTO.StudentDTO;
import com.Project.StudentManagement.Entity.Attendance;
import com.Project.StudentManagement.Entity.Collage;
import com.Project.StudentManagement.Entity.Student;
import com.Project.StudentManagement.Mapper.CollageMapper;
import com.Project.StudentManagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasAnyRole('STUDENT','TEACHER','ADMIN')")
@RequiredArgsConstructor
public class StController {
  private final StudentService studentService;

     //get by id

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

       // get all student
    @GetMapping("/get/all")
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

     //update student
    @PutMapping("/update")
    public StudentDTO putStudent(@Valid @RequestBody StudentDTO dto){
        return studentService.putSt( dto);
    }

    // add student
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.saveStudent(studentDTO);
        return new ResponseEntity<>("Student inserted successfully!", HttpStatus.CREATED);
    }

       // delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id){
    studentService.DeleteById(id);
    return new ResponseEntity<>("Student delete successfully!",HttpStatus.CREATED);
    }


}
