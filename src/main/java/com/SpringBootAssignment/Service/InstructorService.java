package com.SpringBootAssignment.Service;

import com.SpringBootAssignment.Model.Instructor;
import com.SpringBootAssignment.Repositories.InstructorRepo;
import com.SpringBootAssignment.Utills.AssignmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepo instructorRepo;

    public List<Instructor> getAllInstructor() throws AssignmentException {
        List<Instructor> instructors = instructorRepo.findAll();
        if(instructors.size() > 0) {
            return instructors;
        } else {
            return new ArrayList<Instructor>();
        }
    }

    public Instructor getInstructorById(Integer id) throws AssignmentException
    {
        Optional<Instructor> instructor = instructorRepo.findById(id);

        if(instructor.isPresent()) {
            return instructor.get();
        } else {
            throw new AssignmentException("No instructor record exist for given id");
        }
    }

    public Instructor createOrUpdateInstructor(Instructor entity) throws AssignmentException
    {
        Optional<Instructor> instructor = instructorRepo.findById(entity.getId());

        if(instructor.isPresent())
        {
            Instructor newEntity = instructor.get();
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setDepartmentName(entity.getDepartmentName());
            newEntity.setHeadedBy(entity.getHeadedBy());
            newEntity.setPhone(entity.getPhone());

            newEntity = instructorRepo.save(newEntity);

            return newEntity;
        } else {
            entity = instructorRepo.save(entity);

            return entity;
        }
    }

    public String deleteInstructorById(Integer id) throws AssignmentException
    {
        Optional<Instructor> instructor = instructorRepo.findById(id);

        if(instructor.isPresent())
        {
            instructorRepo.delete(instructor.get());
        } else {
            throw new AssignmentException("No instructor record exist for given id");
        }
        return "instructor deleted with instructor id : " + id.toString();
    }


}
