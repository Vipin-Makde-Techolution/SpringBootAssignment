package com.SpringBootAssignment.Controllers;

import com.SpringBootAssignment.Model.Course;
import com.SpringBootAssignment.Model.Student;
import com.SpringBootAssignment.Service.RestApiService;
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
@RequestMapping(value = "/restApi", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestApiController {

    @Autowired
    private RestApiService restApiService;

    private final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @ApiOperation(value = "/restApi/coursesByStudentId", notes = "get the list of course for a particular student id")
    @GetMapping(value = "/coursesByStudentId")
    public ResponseEntity<?> getCoursesByStudentId(@RequestParam Integer studentId) {
        List<Course> courses = new ArrayList<>();
        try{
            courses = restApiService.getCoursesByStudentId(studentId);
            if (CollectionUtils.isEmpty(courses)) {
                return errorResponseBuilder(NO_CONTENT, "No Courses present");
            }
        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching All courses : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR , ex.getErrorMessage());
        }
        return new ResponseEntity<List<Course>>(courses, OK);
    }

    @ApiOperation(value = "/restApi/studentsByInstructorId", notes = "get the list of students for a particular instructor id")
    @GetMapping(value = "/studentsByInstructorId")
    public ResponseEntity<?> getStudentsByInstructorId(@RequestParam Integer instructorId) {
        List<Student> students = new ArrayList<>();
        try{
            students = restApiService.getStudentsByInstructorId(instructorId);
            if (CollectionUtils.isEmpty(students)) {
                return errorResponseBuilder(NO_CONTENT, "No Student present");
            }
        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching All courses : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR , ex.getErrorMessage());
        }
        return new ResponseEntity<List<Student>>(students, OK);
    }

    @ApiOperation(value = "/restApi/totalCourseDurationByStudentId", notes = "get the total course duration for a particular student id")
    @GetMapping(value = "/totalCourseDurationByStudentId")
    public ResponseEntity<?> getTotalCourseDurationByStudentId(@RequestParam Integer studentId) {
        Integer totalDuration = 0;
        try{
            totalDuration = restApiService.getTotalCourseDurationByStudentId(studentId);
            if (totalDuration==0) {
                return errorResponseBuilder(NO_CONTENT, "No Courses present");
            }
        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching All courses for a perticular Student: [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR , ex.getErrorMessage());
        }
        return new ResponseEntity<Integer>(totalDuration, OK);
    }

}
