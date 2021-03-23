package com.SpringBootAssignment.Service;

import com.SpringBootAssignment.Model.Course;
import com.SpringBootAssignment.Model.Student;
import com.SpringBootAssignment.Repositories.CourseRepo;
import com.SpringBootAssignment.Repositories.InstructorRepo;
import com.SpringBootAssignment.Repositories.StudentRepo;
import com.SpringBootAssignment.Utills.AssignmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestApiService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private InstructorRepo instructorRepo;

    @Autowired
    private CourseRepo courseRepo;

    public List<Course> getCoursesByStudentId(Integer studentId) throws AssignmentException {
        List<Course> courses = new ArrayList<>();
        Optional<Student> student = studentRepo.findById(studentId);
        if(student.isPresent()){
            courses = student.get().getCourses();
        }
        return courses;
    }


    public Integer getTotalCourseDurationByStudentId(Integer studentId) throws AssignmentException {
        Integer totalDuration = 0;
        totalDuration = courseRepo.findTotalDurationByStudentId(studentId);
        return totalDuration;
    }

    public List<Student> getStudentsByInstructorId(Integer instructorId) throws AssignmentException {
        List<Student> students = new ArrayList<>();
        students = studentRepo.findStudentsByInstructorId(instructorId);

        return students;
    }


}
