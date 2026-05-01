package com.Project.StudentManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String branch;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private  Course course;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Attendance> attendance = new ArrayList<>();
    public void addattendance(Attendance att){
        this.attendance.add(att);
        att.setStudent(this);
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "collage_id")
    private Collage collage;

}
