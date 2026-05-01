package com.Project.StudentManagement;

import com.Project.StudentManagement.DTO.CourseDTO;
import com.Project.StudentManagement.DTO.StudentDTO;
import com.Project.StudentManagement.Entity.Course;
import com.Project.StudentManagement.Entity.Student;
import com.Project.StudentManagement.Repository.CourseRepository;
import com.Project.StudentManagement.Repository.StudentRepository;
import com.Project.StudentManagement.Service.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class StudentTest{
    @Autowired
    //private StudentRepository studentRepository;
   private StudentService studentService;

 //   @Autowired
//    private CourseRepository courseRepository;
//  //  @Test
//  //  public void getByName(){
//   //     List<Student> studentsname=  studentRepository.findByName("Pooja Sharma");
////        System.out.println(student.getName());
////        System.out.println(student.getId());
////        System.out.println(student.getCollage().getName());
////        System.out.println(student.getEmail());
////        System.out.println(student.getPhoneNumber());
////        System.out.println(student.getBranch());
////        System.out.println(student.getCollage().getCode());
// //       studentsname.forEach(System.out::println);
//  //      assertFalse(studentsname.isEmpty());
////
////
//  //  }
////    @Test
////    public void allSudent(){
////        List<Course> list = courseRepository.findAll();
////        list.forEach(System.out::println);
////    }
////    @Test
////    public void testById(){
////        Student student = studentRepository.findById(1L).orElseThrow(()-> new RuntimeException("not found"));
////        System.out.println(student);
////
////    }
////    @Test
////    public void fStudent(){
////        List<Student> studentList = studentRepository.findAll();
////
////        System.out.println(studentList);
////    }
    @Test
    public void findByIdddd(){
        CourseDTO dto = studentService.getCourseBYID(101L);
        System.out.println(dto);
    }


}
