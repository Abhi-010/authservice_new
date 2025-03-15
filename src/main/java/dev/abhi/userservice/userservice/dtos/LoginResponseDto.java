package dev.abhi.userservice.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LoginResponseDto {
    private String name ;
    private String email ;
    private String token ;
    private String SessionStatus ;
    private Date expDate ;
}
