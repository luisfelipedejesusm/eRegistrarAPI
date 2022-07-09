package com.luisfelipedejesusm.eRegistrarWebAPI.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Student Number must not be blank")
    private String studentNumber;

    @NotBlank(message = "First Name must not be blank")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last Name must not be blank")
    private String lastName;

    private Double cgpa;

    @NotNull(message = "Date of enrollment must not be empty")
    private Date dateOfEnrollment;

    @NotNull(message = "Must define internationality")
    private Boolean isInternational;

}
