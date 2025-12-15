package com.apichallengeservice.dtos.user;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private  String  email;
    private  String  firstName;
    private  String   lastName;
    private  LocalDate dateOfBirth;
    private  String   gender;
    private  LocalDateTime  createdAt;
    private  LocalDateTime  updatedAt;
}
