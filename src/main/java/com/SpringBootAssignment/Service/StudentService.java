package com.SpringBootAssignment.Service;

import com.SpringBootAssignment.Model.Student;
import com.SpringBootAssignment.Repositories.StudentRepo;
import com.SpringBootAssignment.Utills.AssignmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudent() throws AssignmentException {
        List<Student> students = studentRepo.findAll();
        if(students.size() > 0) {
            return students;
        } else {
            return new ArrayList<Student>();
        }
    }

    public Student getStudentById(Integer id) throws AssignmentException
    {
        Optional<Student> student = studentRepo.findById(id);

        if(student.isPresent()) {
            return student.get();
        } else {
            throw new AssignmentException("No student record exist for given id");
        }
    }

    public Student createOrUpdateStudent(Student entity) throws AssignmentException
    {
        Optional<Student> student = studentRepo.findById(entity.getId());

        if(student.isPresent())
        {
            Student newEntity = student.get();
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setPhone(entity.getPhone());

            newEntity = studentRepo.save(newEntity);

            return newEntity;
        } else {
            entity = studentRepo.save(entity);

            return entity;
        }
    }

    public String deleteStudentById(Integer id) throws AssignmentException
    {
        Optional<Student> student = studentRepo.findById(id);
        try{
            if(student.isPresent())
            {
                studentRepo.delete(student.get());
            } else {
                throw new AssignmentException("No student record exist for given id");
            }
        } catch (AssignmentException e){
            e.printStackTrace();
        }

        return "student deleted with id : " + id.toString();
    }


}
