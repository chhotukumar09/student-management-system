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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StController {
  private final StudentService studentService;
@GetMapping("/auth/public/{id}")
public StudentDTO getStudent(@Valid @PathVariable Long id) {

        return studentService.getById(id);
   // } catch (RuntimeException e) {
  //      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  //  }

}
    @GetMapping("/public/student/{id}")
    public CourseDTO getCourse(@Valid@PathVariable Long id) {
       return studentService.getCourseBYID(id);
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(studentService.getAllCourses());
    }
    @GetMapping("/public/allstudent")
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
    return ResponseEntity.ok(studentService.getAllStudent());
    }
    @PutMapping("/update/student")
    public StudentDTO putStudent(@Valid @RequestBody StudentDTO dto){
        return studentService.putSt( dto);
    }
    @PostMapping("/insert")
    public ResponseEntity<String> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.saveStudent(studentDTO);
        return new ResponseEntity<>("Student inserted successfully!", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/student/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id){
    studentService.DeleteById(id);
    return new ResponseEntity<>("Student delete successfully!",HttpStatus.CREATED);
    }

    @GetMapping("/collage/{id}")
    public ResponseEntity<CollageDTO> give(@Valid @PathVariable Long id){
    return ResponseEntity.ok(studentService.GiveCollage(id));

    }
    @PostMapping("/add")
    public ResponseEntity<CollageDTO> addNew(@Valid @RequestBody CollageDTO collageDTO){
    CollageDTO collagedto = studentService.addcollage(collageDTO);

    return new ResponseEntity<>(collagedto,HttpStatus.CREATED);
    }
    @GetMapping("/allcollage")
    public List<CollageDTO> allCollages(){
    List<CollageDTO> collageDTOS = studentService.allCollage();
    return collageDTOS;
    }
    //  All Attendance show
    @GetMapping("/allattendance")
    public List<AttendanceDTO> allList(){
        return studentService.allAttendance();

    }
    // getById
    @GetMapping("/attendance/{id}")
    public AttendanceDTO getBYId(@Valid @PathVariable Long id){
    return studentService.FindById(id);
    }
    //delete Attendance
    @DeleteMapping("/delete/{id}")
    public void deleteId(@Valid @PathVariable Long id){
    studentService.deleteAtt(id);
    }
    //add new attendance
    @PostMapping("/add/addendance")
    public AttendanceDTO newAttendanceADD(@Valid @RequestBody AttendanceDTO attendanceDTO){
    return studentService.addAttendance(attendanceDTO);
    }

}
