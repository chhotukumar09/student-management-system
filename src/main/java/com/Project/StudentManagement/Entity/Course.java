package com.Project.StudentManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String courseCode;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> studentList = new ArrayList<>();
    public void addStudentInCourse(Student student){
       this. studentList.add(student);
       student.setCourse(this);
    }

    @ManyToMany(mappedBy = "course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendanceList = new ArrayList<>();
    public void addAttendanceInCourse(Attendance attendance){
        this.attendanceList.add(attendance);
        attendance.getCourse().add(this);
    }

    @ManyToOne
    @JoinColumn(name = "collage_id")
    @JsonIgnore
    private Collage collage;

}
