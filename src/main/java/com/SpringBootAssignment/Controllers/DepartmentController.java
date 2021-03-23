package com.SpringBootAssignment.Controllers;

import com.SpringBootAssignment.Model.Department;
import com.SpringBootAssignment.Service.CoueseService;
import com.SpringBootAssignment.Service.DepartmentService;
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
@RequestMapping(value = "/department", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "/department/all", notes = "Returns the list of courses")
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllDepartment() {
        List<Department> departments = new ArrayList<>();
        try{
            departments = departmentService.getAllDepartment();
            if (CollectionUtils.isEmpty(departments)) {
                return errorResponseBuilder(NO_CONTENT, "No departments present");
            }
        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching All departments : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR , ex.getErrorMessage());
        }
        return new ResponseEntity<List<Department>>(departments, OK);
    }

    @ApiOperation(value = "/department/{name}", notes = "Returns the department for id")
    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getDepartmentByName(@PathVariable String name){
        Department department = null;
        try{
            department = departmentService.getDepartmentByName(name);

        } catch (AssignmentException ex) {
            logger.error("Error occurred while fetching a department by departmentId : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Department>(department, OK);
    }

    @ApiOperation(value = "/department", notes = "Create Or Update department")
    @PostMapping()
    public ResponseEntity<?> createOrUpdateCourse(@RequestBody Department department)  throws AssignmentException {
        Department updated = null;
        try{
            updated = departmentService.createOrUpdateDepartment(department);
        } catch (AssignmentException ex) {
            logger.error("Error Occurred while updating or creating a department : [{}]", ex.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, ex.getErrorMessage());
        }
        return new ResponseEntity<Department>(updated, OK);
    }

    @ApiOperation(value = "/department/{name}", notes = "Delete department")
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<?> deleteCourseById(@PathVariable String name) throws AssignmentException {
        String delete = null;
        try{
            delete = departmentService.deleteDepartmentByName(name);
        } catch (AssignmentException exception){
            logger.error("Error Occurred while deleting a department : [{}]", exception.getExcep());
            return errorResponseBuilder(INTERNAL_SERVER_ERROR, exception.getErrorMessage());
        }
        return new ResponseEntity<String>(delete, OK);
    }

}
