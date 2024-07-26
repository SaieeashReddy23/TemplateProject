package com.example.templateProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

}
