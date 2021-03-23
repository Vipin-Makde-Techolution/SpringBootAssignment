package com.SpringBootAssignment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "INSTRUCTOR")
@JsonIgnoreProperties({"course"})
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String departmentName;
    
    private String headedBy;
    
    private String firstName;
    
    private String lastName;
    
    private String phone;


    @OneToMany(mappedBy = "instructor")
    private List<Course> course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadedBy() {
        return headedBy;
    }

    public void setHeadedBy(String headedBy) {
        this.headedBy = headedBy;
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

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public Instructor() {
    }

    public Instructor(int id, String departmentName, String headedBy, String firstName, String lastName, String phone, List<Course> course) {
        this.id = id;
        this.departmentName = departmentName;
        this.headedBy = headedBy;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.course = course;
    }
}
