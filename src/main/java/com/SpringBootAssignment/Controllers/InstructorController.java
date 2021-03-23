package com.SpringBootAssignment.Controllers;

import com.SpringBootAssignment.Model.Instructor;
import com.SpringBootAssignment.Service.InstructorService;
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
@RequestMapping(value = "/instructor", produces = {MediaType.APPLICATION_JSON_VALUE})
public class InstructorController {

    private final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private InstructorService instructorService;

    @ApiOperation(value = "/instructor/all", notes = "Returns the list of instructor")
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllInstructor() {
        List<Instructor> instructor = new ArrayList<>();
        try{
            instructor = instructorService.getAllInstructor();
            if (CollectionUtils.isEmpty(instructor)) {
                return errorResponseBuilder(NO_CONTENT, "No Instructor present");
            }
        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching All instructor : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR , ex.getErrorMessage());
        }
        return new ResponseEntity<List<Instructor>>(instructor, OK);
    }


    @ApiOperation(value = "/instructor/{id}", notes = "Returns the data of  instructor")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable Integer id){
        Instructor course = null;
        try{
            course = instructorService.getInstructorById(id);

        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching a Instructor by InstructorId : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Instructor>(course, OK);
    }

    @ApiOperation(value = "/instructor", notes = "Create Or Update the data of  instructor")
    @PostMapping()
    public ResponseEntity<?> createOrUpdateInstructor(@RequestBody Instructor course)  throws AssignmentException {
        Instructor updated = null;
        try{
            updated = instructorService.createOrUpdateInstructor(course);
        } catch (AssignmentException ex) {
            logger.error("Error Occurred while updating or creating a Instructor : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Instructor>(updated, OK);
    }

    @ApiOperation(value = "/instructor/{id}", notes = "Delete data of instructor")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable("id") Integer id) throws AssignmentException {
        String delete = null;
        try{
            delete = instructorService.deleteInstructorById(id);
        } catch (AssignmentException exception){
            logger.error("Error Occurred while deleting a Instructor : [{}]", exception.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, exception.getErrorMessage());
        }
        return new ResponseEntity<String>(delete, OK);
    }

}
