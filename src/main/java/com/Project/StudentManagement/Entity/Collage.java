package com.Project.StudentManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {
        "courseList","clsStudent", "clgTeacher"
})
public class Collage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String enstablishedyear;
    private String code;
    private String emailclg;

    @OneToMany(mappedBy = "collage",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courseList = new ArrayList<>();
    public void addCourseList(Course course){
        this.courseList.add(course);
        course.setCollage(this);
    }

    @OneToMany(mappedBy = "collage",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> clsStudent = new ArrayList<>();
    public void addStudentInCollage(Student student){
        this.clsStudent.add(student);
        student.setCollage(this);
    }


    @OneToMany(mappedBy = "collage",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Teacher> clgTeacher = new ArrayList<>();
    public void addTeacherInCollage(Teacher teacher){
        this.clgTeacher.add(teacher);
        teacher.setCollage(this);
    }



}
