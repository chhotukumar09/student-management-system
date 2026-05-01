package com.Project.StudentManagement.Mapper;

import com.Project.StudentManagement.DTO.CollageDTO;
import com.Project.StudentManagement.DTO.CourseDTO;
import com.Project.StudentManagement.Entity.Collage;
import com.Project.StudentManagement.Entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollageMapper {

    CollageDTO mapToDTO(Collage collage);

    List<CollageDTO> MapDTO(List<Collage> collageList);

    Collage mapToEntity(CollageDTO collageDTO);

}
