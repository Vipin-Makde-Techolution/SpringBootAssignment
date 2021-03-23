package com.SpringBootAssignment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
//@JsonIgnoreProperties({"courses"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;

    private String lastName;

    private String phone;

    @ManyToMany()
    @JoinTable(name = "student_course", joinColumns = {@JoinColumn(name = "s_id")},
    inverseJoinColumns = {@JoinColumn(name = "c_id")})
    private List<Course> courses;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Student() {
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourse(List<Course> course) {
        this.courses = course;
    }
}
