package com.SpringBootAssignment.Repositories;

import com.SpringBootAssignment.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepo extends JpaRepository<Course, Integer> {

    @Query(
            value = "SELECT SUM(COURSE.duration) FROM COURSE LEFT JOIN STUDENT_COURSE WHERE COURSE.id=STUDENT_COURSE.c_id AND s_id=?1",
            nativeQuery = true)
    Integer findTotalDurationByStudentId(Integer studentId);
}
