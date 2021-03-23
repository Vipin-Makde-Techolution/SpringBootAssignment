package com.SpringBootAssignment.Repositories;

import com.SpringBootAssignment.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Instructor, Integer> {
}
