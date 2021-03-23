package com.SpringBootAssignment.Controllers;

import com.SpringBootAssignment.Model.Course;
import com.SpringBootAssignment.Service.CoueseService;
import com.SpringBootAssignment.Utills.AssignmentException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.SpringBootAssignment.Utills.ApplicationHelper.errorResponseBuilder;
import static org.springframework.http.HttpStatus.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/course", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CoueseService coueseService;

  @ApiOperation(value = "/course/all", notes = "Returns the list of courses")
  @GetMapping(value = "/all")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public ResponseEntity<?> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try{
            courses = coueseService.getAllCourses();
            if (CollectionUtils.isEmpty(courses)) {
                return errorResponseBuilder(NO_CONTENT, "No Courses present");
            }
        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching All courses : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR , ex.getErrorMessage());
        }
        return new ResponseEntity<List<Course>>(courses, OK);
    }


    @ApiOperation(value = "/course/{id}", notes = "Returns the courses for id")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id){
        Course course = null;
        try{
            course = coueseService.getCourseById(id);

        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching a course by courseId : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Course>(course, OK);
    }

    @ApiOperation(value = "/course", notes = "Create or Update Course")
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<?> createOrUpdateCourse(@RequestBody Course course)  throws AssignmentException {
        Course updated = null;
        try{
            updated = coueseService.createOrUpdateCourse(course);
        } catch (AssignmentException ex) {
            logger.error("Error Occurred while updating or creating a course : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Course>(updated, OK);
    }

    @ApiOperation(value = "/course/{id}", notes = "delete Course for id")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<?> deleteCourseById(@PathVariable("id") Integer id) throws AssignmentException {
        String delete = null;
        try{
            delete = coueseService.deleteCourseById(id);
        } catch (AssignmentException exception){
            logger.error("Error Occurred while updating or creating a course : [{}]", exception.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, exception.getErrorMessage());
        }
        return new ResponseEntity<String>(delete, OK);
    }

}
