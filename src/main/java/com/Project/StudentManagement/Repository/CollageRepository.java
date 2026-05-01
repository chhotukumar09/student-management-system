package com.Project.StudentManagement.Repository;

import com.Project.StudentManagement.Entity.Collage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollageRepository extends JpaRepository<Collage, Long> {
}
