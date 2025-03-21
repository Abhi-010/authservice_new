package dev.abhi.userservice.userservice.controllers;
import dev.abhi.userservice.userservice.dtos.*;
import dev.abhi.userservice.userservice.services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        LoginResponseDto loginResponseDto =
                authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        MultiValueMap<String, String> header = new HttpHeaders() ;
        header.add("token",loginResponseDto.getToken());

        return new ResponseEntity<>(loginResponseDto,header, HttpStatus.OK) ;
    }

    // logout
    @PostMapping("/logout/{id}")
    public ResponseEntity<LogoutResponseDto> logout(@PathVariable("id") Long sessionId){
            LogoutResponseDto logoutResponseDto = authService.logout(sessionId) ;
            return new ResponseEntity<>(logoutResponseDto,HttpStatus.OK) ;
    }
}
