package com.example.demo.representationObjects.users;

import lombok.Data;

@Data
public class PerformanceUsers {
    private String firstName;
    private String lastName;
    private String email;
    public PerformanceUsers(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
