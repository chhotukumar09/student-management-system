package com.Project.StudentManagement.Service;

import com.Project.StudentManagement.DTO.AttendanceDTO;
import com.Project.StudentManagement.DTO.CollageDTO;
import com.Project.StudentManagement.DTO.CourseDTO;
import com.Project.StudentManagement.DTO.StudentDTO;
import com.Project.StudentManagement.Entity.Attendance;
import com.Project.StudentManagement.Entity.Collage;
import com.Project.StudentManagement.Entity.Course;
import com.Project.StudentManagement.Entity.Student;
import com.Project.StudentManagement.Exception.ResourceNotFoundException;
import com.Project.StudentManagement.Mapper.AttendanceMapper;
import com.Project.StudentManagement.Mapper.CollageMapper;
import com.Project.StudentManagement.Mapper.CourseMapper;
import com.Project.StudentManagement.Mapper.StudentMapper;
import com.Project.StudentManagement.Repository.AttendanceRepository;
import com.Project.StudentManagement.Repository.CollageRepository;
import com.Project.StudentManagement.Repository.CourseRepository;
import com.Project.StudentManagement.Repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    public final AttendanceRepository attendanceRepository;
    private final CollageRepository collageRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final CollageMapper collageMapper;
    private final AttendanceMapper attendanceMapper;

    // Get student
   public StudentDTO getById(Long id) {
    return studentRepository.findById(id)
            .map(studentMapper::MapToStudent)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
     // get all Student
    public List<StudentDTO> getAllStudent(){
        List<Student> allstudent = studentRepository.findAll();
        return studentMapper.mapDTO(allstudent);
    }

    // update student
    @Transactional
    public StudentDTO putSt(StudentDTO dto) {
        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + dto.getId()));
        Course course = courseRepository.findById(dto.getCourse_id())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + dto.getCourse_id()));

        Collage collage = collageRepository.findById(dto.getCollage_id()).
                orElseThrow(() -> new ResourceNotFoundException("NOt found collage id " + dto.getCollage_id()));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setBranch(dto.getBranch());
        student.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getCollage_id() != null){
            student.setCollage(collage);
            collage.addStudentInCollage(student);
        }
        if (dto.getCourse_id() != null){
            student.setCourse(course);
            course.addStudentInCourse(student);

        }
        Student saved= studentRepository.save(student);
        return studentMapper.MapToStudent(saved);
    }
    // add new Student
    @Transactional
    public void saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setBranch(studentDTO.getBranch());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        if (studentDTO.getCourse_id() != null) {
            Course course = courseRepository.findById(studentDTO.getCourse_id()).orElseThrow(() -> new ResourceNotFoundException("They id is not found"));
            student.setCourse(course);
            course.addStudentInCourse(student);
        }
        if (studentDTO.getCollage_id() != null){
            Collage collage = collageRepository.findById(studentDTO.getCollage_id()).orElseThrow(()-> new ResourceNotFoundException("Collage id is not found"));
            student.setCollage(collage);
            collage.addStudentInCollage(student);
        }

        studentRepository.save(student);
    }
    // delete
    public void DeleteById(Long id){
       Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID is not found "+ id));
       if (student.getId() != null) {
           studentRepository.deleteById(student.getId());
       }else {
           throw new ResourceNotFoundException("id is not found" +id);
       }
    }

    // get courseBYId
   public CourseDTO getCourseBYID(Long id){
     Course cr = courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));
     return courseMapper.toDTO(cr);
   }

     //get All Course
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDTOList(courses);
    }
      // get collage
    public CollageDTO GiveCollage(Long id){
    Collage collage = collageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found "+id));
    return collageMapper.mapToDTO(collage);
    }


        // get all collage
    public List<CollageDTO>  allCollage(){
      List<Collage> collages = collageRepository.findAll();
      return collageMapper.MapDTO(collages);
    }

       // add new collage
    @Transactional
    public CollageDTO addcollage(CollageDTO collageDTO){
      Collage collage = collageMapper.mapToEntity(collageDTO);
      Collage saved = collageRepository.save(collage);

      return collageMapper.mapToDTO(saved);

    }
       // All Attendance get
    @Transactional
    public List<AttendanceDTO> allAttendance(){
        List<Attendance> List= attendanceRepository.findAll();
        return attendanceMapper.ToDTO(List);
    }

    // Attendance getById()
    @Transactional
    public AttendanceDTO FindById(Long id){
    Attendance attendance = attendanceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID is not found"+id));
    return attendanceMapper.mapToDTO(attendance);
    }


      // delete Attendance
     @Transactional
     public  void deleteAtt(Long id){
       if (id == null){
         throw new ResourceNotFoundException("id is not found");
       }if (attendanceRepository.existsById(id)){
        attendanceRepository.deleteById(id);
       }else {
         throw new ResourceNotFoundException("Attendance not found");
       }
    }


       //add new attendance
    public AttendanceDTO addAttendance(AttendanceDTO attendancedto){
      Attendance attendance = attendanceMapper.mapToEntity(attendancedto);
      Attendance saved = attendanceRepository.save(attendance);
      return attendanceMapper.mapToDTO(saved);

    }

}
