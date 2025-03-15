package dev.abhi.userservice.userservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SignUpRequestDto {
    private String email ;
    private String name ;
    private String password ;
}
