package com.Project.StudentManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String spacelization;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course>  courseList = new ArrayList<>();
    public void addCourse(Course course){
        this.courseList.add(course);
        course.setTeacher(this);
    }


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "collage_id")
    private Collage collage;

}
