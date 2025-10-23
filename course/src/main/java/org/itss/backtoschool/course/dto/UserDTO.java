package org.itss.backtoschool.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String homeAddress;
    private String role;

}
