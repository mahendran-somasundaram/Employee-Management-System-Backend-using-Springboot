package com.employee.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "First_Name",nullable = false)
    private String firstname;
    @Column(name = "Last_Name", nullable = false)
    private String lastname;
    @Column(name = "Email_Id", nullable = false)
    private String email;

}
