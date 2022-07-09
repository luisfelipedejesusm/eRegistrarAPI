package com.luisfelipedejesusm.eRegistrarWebAPI.application;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class StudentRequestDto {
    @NotBlank
    private String studentNumber;
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;
    private Long cgpa;
    @NotNull
    private LocalDate dateOfEnrollment;
    @NotNull
    private Boolean isInternational;
}
