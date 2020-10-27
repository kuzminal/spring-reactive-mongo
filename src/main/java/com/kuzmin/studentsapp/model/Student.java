package com.kuzmin.studentsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "Student")
public class Student {
    @Id
    @JsonIgnore
    private String id;
    @NotNull(message = "Roll no can not be empty")
    private Integer rollNo;
    @NotNull(message = "Name can not be empty")
    private String name;
    @NotNull(message = "Standard can not be empty")
    private Integer standard;
}
