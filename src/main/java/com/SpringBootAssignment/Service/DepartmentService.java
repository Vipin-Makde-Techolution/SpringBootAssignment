package com.SpringBootAssignment.Service;

import com.SpringBootAssignment.Model.Department;
import com.SpringBootAssignment.Repositories.DepartmentRepo;
import com.SpringBootAssignment.Utills.AssignmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Department> getAllDepartment() throws AssignmentException {
        List<Department> departments = departmentRepo.findAll();
        if(departments.size() > 0) {
            return departments;
        } else {
            return new ArrayList<Department>();
        }
    }

    public Department getDepartmentByName(String name) throws AssignmentException
    {
        Optional<Department> department = departmentRepo.findById(name);

        if(department.isPresent()) {
            return department.get();
        } else {
            throw new AssignmentException("No department record exist for given name");
        }
    }

    public Department createOrUpdateDepartment(Department entity) throws AssignmentException
    {
        Optional<Department> department = departmentRepo.findById(entity.getName());

        if(department.isPresent())
        {
            Department newEntity = department.get();
            newEntity.setName(entity.getName());
            newEntity.setLocation(entity.getLocation());
            newEntity = departmentRepo.save(newEntity);

            return newEntity;
        } else {
            entity = departmentRepo.save(entity);

            return entity;
        }
    }

    public String deleteDepartmentByName(String name) throws AssignmentException
    {
        Optional<Department> department = departmentRepo.findById(name);

        if(department.isPresent())
        {
            departmentRepo.delete(department.get());
        } else {
            throw new AssignmentException("No department record exist for given name");
        }
        return "department deleted with name : " + name.toString();
    }


}
