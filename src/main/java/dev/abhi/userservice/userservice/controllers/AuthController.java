package dev.abhi.userservice.userservice.controllers;
import dev.abhi.userservice.userservice.dtos.*;
import dev.abhi.userservice.userservice.services.AuthService;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService ;

    public AuthController(AuthService authService){
        this.authService = authService ;
    }

    /**1.Sign Up **/

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        UserResponseDto userResponseDto =
                authService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK) ;
    }

    /**2.login **/
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        LoginResponseDto loginResponseDto =
                authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());

        MultiValueMapAdapter<String,String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"auth-token:" + loginResponseDto.getToken());

        UserResponseDto userResponseDto = new UserResponseDto() ;
        userResponseDto.setName(loginResponseDto.getName());
        userResponseDto.setEmail(loginResponseDto.getEmail());

        return new ResponseEntity<>(userResponseDto,headers,HttpStatus.OK);

//        MultiValueMap<String, String> header = new HttpHeaders() ;
//        header.add("token",loginResponseDto.getToken());
//
//        return new ResponseEntity<>(loginResponseDto,header, HttpStatus.OK) ;
    }

    // logout
    @PostMapping("/logout/{id}")
    public ResponseEntity<LogoutResponseDto> logout(@PathVariable("id") Long sessionId){
            LogoutResponseDto logoutResponseDto = authService.logout(sessionId) ;
            return new ResponseEntity<>(logoutResponseDto,HttpStatus.OK) ;
    }

    @GetMapping("/validateToken/{id}")
    public ResponseEntity<TokenValidResponseDto> validateToken(@RequestBody TokenValidRequestDto tokenValidateDto,
                                                               @PathVariable("id") Long id){
        TokenValidResponseDto tokenValidResponseDto =  authService.validateToken(tokenValidateDto.getToken(),id) ;
        return new ResponseEntity<TokenValidResponseDto>(tokenValidResponseDto,HttpStatus.OK);
    }
}
