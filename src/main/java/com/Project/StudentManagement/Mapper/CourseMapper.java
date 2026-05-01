package com.Project.StudentManagement.Mapper;

import com.Project.StudentManagement.DTO.CourseDTO;
import com.Project.StudentManagement.Entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);
    List<CourseDTO> toDTOList(List<Course> courseList);
}
