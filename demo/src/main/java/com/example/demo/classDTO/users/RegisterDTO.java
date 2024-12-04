package com.example.demo.classDTO.users;

import lombok.Data;

@Data
public class RegisterDTO {
    private String mail;
    private String password;
    private String first_name;
    private String last_name;
}
