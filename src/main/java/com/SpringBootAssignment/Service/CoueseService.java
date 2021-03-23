package com.SpringBootAssignment.Service;

import com.SpringBootAssignment.Model.Course;
import com.SpringBootAssignment.Model.Instructor;
import com.SpringBootAssignment.Repositories.CourseRepo;
import com.SpringBootAssignment.Repositories.InstructorRepo;
import com.SpringBootAssignment.Utills.AssignmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoueseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private InstructorRepo instructorRepo;

    public List<Course> getAllCourses() throws AssignmentException {
        List<Course> courses = courseRepo.findAll();
        if(courses.size() > 0) {
            return courses;
        } else {
            return new ArrayList<Course>();
        }
    }

    public Course getCourseById(Integer id) throws AssignmentException
    {
        Optional<Course> course = courseRepo.findById(id);

        if(course.isPresent()) {
            return course.get();
        } else {
            throw new AssignmentException("No course record exist for given id");
        }
    }

    public Course createOrUpdateCourse(Course entity) throws AssignmentException
    {
        Optional<Course> course = courseRepo.findById(entity.getId());
        Optional<Instructor> instructor = instructorRepo.findById(entity.getInstructor().getId());

        if(course.isPresent())
        {
            Course newEntity = course.get();
            newEntity.setName(entity.getName());
            newEntity.setDepartmentName(entity.getDepartmentName());
            newEntity.setDuration(entity.getDuration());
            if (instructor.isPresent()) {
                newEntity.setInstructor(instructor.get());
            } else {
                newEntity.setInstructor(entity.getInstructor());
            }

            newEntity = courseRepo.save(newEntity);

            return newEntity;
        } else {
            entity = courseRepo.save(entity);

            return entity;
        }
    }

    public String deleteCourseById(Integer id) throws AssignmentException
    {
        Optional<Course> course = courseRepo.findById(id);

        if(course.isPresent())
        {
            courseRepo.delete(course.get());
        } else {
            throw new AssignmentException("No course record exist for given id");
        }
        return "course deleted with course id : " + id.toString();
    }


}
