package com.Project.StudentManagement.Mapper;

import com.Project.StudentManagement.DTO.AttendanceDTO;
import com.Project.StudentManagement.Entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    @Mapping(source = "student.id", target = "studentId")
    AttendanceDTO mapToDTO(Attendance attendance);

    List<AttendanceDTO> ToDTO(List<Attendance> attendanceList);

    @Mapping(source = "studentId", target = "student.id")
    Attendance mapToEntity(AttendanceDTO attendanceDTO);
}
