package com.sample.gateway.view;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserView {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String birthPlace;

    private String sex;

    private String currentAddress;
}
