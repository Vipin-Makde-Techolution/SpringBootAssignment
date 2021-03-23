package com.SpringBootAssignment.Repositories;

import com.SpringBootAssignment.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, String> {

}
