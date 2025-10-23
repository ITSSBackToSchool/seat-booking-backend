package org.itss.backtoschool.course.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;
    private String phone;
    private String homeAddress;
}


