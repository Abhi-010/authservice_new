package dev.abhi.userservice.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponseDto {
    private String name ;
    private String email ;
    private Long sessionId ;
    private String SessionStatus ;
}
