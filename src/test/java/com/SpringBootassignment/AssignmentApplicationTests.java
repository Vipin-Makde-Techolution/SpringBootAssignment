package com.SpringBootassignment;

import com.SpringBootAssignment.Model.Course;
import com.SpringBootAssignment.Model.Department;
import com.SpringBootAssignment.Model.Instructor;
import com.SpringBootAssignment.Model.Student;
import com.SpringBootAssignment.Repositories.CourseRepo;
import com.SpringBootAssignment.Repositories.StudentRepo;
import com.SpringBootAssignment.Service.RestApiService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AssignmentApplicationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private RestApiService restApiService;


    @Test
    public void when_GetStudentsByInstructorId_thenReturnSuccess() {
        Instructor instructor =
                Instructor.builder()
                        .firstName("test")
                        .lastName("test")
                        .departmentName("test department")
                        .headedBy("test")
                        .phone("1234567799")
                        .build();
        int instructorId = entityManager.persist(instructor).getId();
        Course course =
                Course.builder()
                        .name("test")
                        .instructor(instructor)
                        .departmentName("test department")
                        .duration(100)
                        .build();
        int courseId = entityManager.persist(course).getId();
        Student student =
                Student.builder().firstName("test").lastName("test").phone("1234567890").build();
        int studentId = entityManager.persist(student).getId();
        Department department =
                Department.builder().name("test department").location("test location").build();

        entityManager.persist(department);
        entityManager.flush();
        List<Student> studentList = studentRepo.findStudentsByInstructorId(instructorId);
        Assert.assertEquals(1, studentList.size());
    }

    @Test
    public void when_GetCourseDurationByStudentId_thenReturnSuccess() {
        Instructor instructor =
                Instructor.builder()
                        .firstName("test")
                        .lastName("test")
                        .departmentName("test department")
                        .headedBy("test")
                        .phone("1234567799")
                        .build();
        int instructorId = entityManager.persist(instructor).getId();
        Course course =
                Course.builder()
                        .name("test")
                        .instructor(instructor)
                        .departmentName("test department")
                        .duration(100)
                        .build();

        Course course2 =
                Course.builder()
                        .name("test")
                        .instructor(instructor)
                        .departmentName("test department")
                        .duration(120)
                        .build();
        int courseId = entityManager.persist(course).getId();
        int courseId2 = entityManager.persist(course2).getId();
        Student student =
                Student.builder().firstName("test").lastName("test").phone("1234567890").build();
        int studentId = entityManager.persist(student).getId();
        Department department =
                Department.builder().name("test department").location("test location").build();


        entityManager.persist(department);
        entityManager.flush();
        long duration =courseRepo.findTotalDurationByStudentId(studentId);
        Assert.assertEquals(220, duration);
    }

    @Test
    public void when_GetCoursesByStudentId_thenReturnSuccess() {
        Instructor instructor =
                Instructor.builder()
                        .firstName("test")
                        .lastName("test")
                        .departmentName("test department")
                        .headedBy("test")
                        .phone("1234567799")
                        .build();
        Course course =
                Course.builder()
                        .name("test")
                        .instructor(instructor)
                        .departmentName("test department")
                        .duration(100)
                        .build();
        Student student =
                Student.builder().firstName("test").lastName("test").phone("1234567890").build();
        int studentId = entityManager.persist(student).getId();
        Department department =
                Department.builder().name("test department").location("test location").build();

        entityManager.persist(department);
        entityManager.flush();
        List<Course> courseList = restApiService.getCoursesByStudentId(studentId);
        Assert.assertEquals(1, courseList.size());
    }
}