package com.SpringBootAssignment.Controllers;

import com.SpringBootAssignment.Model.Student;
import com.SpringBootAssignment.Service.CoueseService;
import com.SpringBootAssignment.Service.StudentService;
import com.SpringBootAssignment.Utills.AssignmentException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.SpringBootAssignment.Utills.ApplicationHelper.errorResponseBuilder;
import static org.springframework.http.HttpStatus.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/student", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "/student/all", notes = "Returns the list of student")
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllCourses() {
        List<Student> students = new ArrayList<>();
        try{
            students = studentService.getAllStudent();
            if (CollectionUtils.isEmpty(students)) {
                return errorResponseBuilder(NO_CONTENT, "No Students present");
            }
        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching All students : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR , ex.getErrorMessage());
        }
        return new ResponseEntity<List<Student>>(students, OK);
    }

    @ApiOperation(value = "/student/{id}", notes = "Returns the data of student")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id){
        Student student = null;
        try{
            student = studentService.getStudentById(id);

        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching a student by id : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Student>(student, OK);
    }

    @ApiOperation(value = "/student", notes = "Create Or Update the data of student")
    @PostMapping()
    public ResponseEntity<?> createOrUpdateCourse(@RequestBody Student student)  throws AssignmentException {
        Student updated = null;
        try{
            updated = studentService.createOrUpdateStudent(student);
        } catch (AssignmentException ex) {
            logger.error("Error Occurred while updating or creating a student : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Student>(updated, OK);
    }

    @ApiOperation(value = "/student/{id}", notes = "Deletes the data of student")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("id") Integer id) throws AssignmentException {
        String delete = null;
        try{
            delete = studentService.deleteStudentById(id);
        } catch (AssignmentException exception){
            logger.error("Error Occurred while updating or creating a student : [{}]", exception.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, exception.getErrorMessage());
        }
        return new ResponseEntity<String>(delete, OK);
    }

}
