package com.example.templateProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonsResponse {

    private String status;
    private Integer code;
    private Integer total;
    private List<Person> data;
}
