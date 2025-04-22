package com.example.demo.representationObjects.users;

import com.example.demo.users.models.User;

public class RepresentationUsers {

    public static PerformanceUsers getPerformanceUsers(User user) {
        return new PerformanceUsers(
                user.getFirstName(),
                user.getLastName(),
                user.getMail()
        );
    }

}
