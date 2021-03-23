package com.SpringBootAssignment.Repositories;

import com.SpringBootAssignment.Model.Course;
import com.SpringBootAssignment.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query(
            value = "select DISTINCT  student.id, student.first_name, student.last_name, student.phone" +
                    " from Student left join student_course on student.id=student_course.s_id  " +
                    "left join course where student_course.c_id=course.id AND course.instructor_id=?1",
            nativeQuery = true)
    List<Student> findStudentsByInstructorId(Integer instructor_id);

}
