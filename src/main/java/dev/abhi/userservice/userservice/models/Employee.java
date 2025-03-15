package dev.abhi.userservice.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee extends BaseModel{
    private String name ;
    private int age ;
    private String email ;
}
