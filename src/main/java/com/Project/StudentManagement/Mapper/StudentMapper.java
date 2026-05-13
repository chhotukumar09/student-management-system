package com.Project.StudentManagement.Mapper;

import com.Project.StudentManagement.DTO.StudentDTO;
import com.Project.StudentManagement.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
     @Mapping(source = "collage.id", target = "collage_id")
     @Mapping(source = "course.id", target = "course_id")
    StudentDTO MapToStudentDTO(Student student);

    List<StudentDTO> mapToStudentDTO(List<Student> studentList);

    @Mapping(target = "collage",ignore = true)
    @Mapping(target = "course",ignore = true)
    @Mapping(target = "attendance",ignore = true)
    Student mapToStudentDTO(StudentDTO studentDTO);



}
