package dev.abhi.userservice.userservice.services;

import dev.abhi.userservice.userservice.dtos.LoginResponseDto;
import dev.abhi.userservice.userservice.dtos.LogoutResponseDto;
import dev.abhi.userservice.userservice.models.Session;
import dev.abhi.userservice.userservice.models.SessionStatus;
import dev.abhi.userservice.userservice.models.User;
import dev.abhi.userservice.userservice.repo.SessionRepository;
import dev.abhi.userservice.userservice.repo.UserRepository;
import dev.abhi.userservice.userservice.dtos.UserResponseDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

   private UserRepository userRepository ;
   private SessionRepository sessionRepository ;
   private BCryptPasswordEncoder bCryptPasswordEncoder ;
   public AuthService(UserRepository userRepository,SessionRepository sessionRepository,BCryptPasswordEncoder bCryptPasswordEncoder)
   {
       this.sessionRepository = sessionRepository ;
       this.userRepository = userRepository ;
       this.bCryptPasswordEncoder = bCryptPasswordEncoder ;
   }

   public UserResponseDto signUp(String name, String email, String password){
       User newUser = new User() ;
       newUser.setName(name);
       newUser.setEmail(email);
       //bCryptPasswordEncoder.encode(password)
       //newUser.setPassword(password);
       newUser.setPassword(bCryptPasswordEncoder.encode(password));

       User savedUser = userRepository.save(newUser) ;
       return UserResponseDto.from(savedUser) ;
   }

   public LoginResponseDto login(String email, String password) throws Exception {

       Optional<User> user = userRepository.findByEmail(email);
       if(user.isEmpty()){
//           System.out.println("No user with this email");
//           return null ;
           throw new Exception("Invalid email") ;
       }

       User savedUser = user.get() ;

       if(!bCryptPasswordEncoder.matches(password,savedUser.getPassword())){
//           System.out.println("Invalid Password");
//           return null ;
           throw new Exception("Invalid password") ;
       }

       Session session = new Session();
       session.setUser(savedUser);
       session.setToken(RandomStringUtils.randomAlphanumeric(30));
       session.setSessionStatus(SessionStatus.ACTIVE);
       session.setExpiringTimeinMiliis(System.currentTimeMillis()+60000);

       Session savedSession = sessionRepository.save(session) ;

       LoginResponseDto loginResponseDto = new LoginResponseDto() ;

       loginResponseDto.setName(savedUser.getName());
       loginResponseDto.setEmail(savedUser.getEmail());
       loginResponseDto.setToken(savedSession.getToken());
       loginResponseDto.setSessionStatus(savedSession.getSessionStatus().toString());
       loginResponseDto.setExpTime(savedSession.getExpiringTimeinMiliis());

       return loginResponseDto ;
   }

   public LogoutResponseDto logout(Long sessionId){
       Optional<Session> optionalSession = sessionRepository.findById(sessionId) ;
       System.out.println("sessionId : " + sessionId);
       if(optionalSession.isEmpty()){
           System.out.println("Invalid Session");
           return null ;
       }

       Session session = optionalSession.get() ;
       session.setSessionStatus(SessionStatus.ENDED);
       sessionRepository.save(session);

       LogoutResponseDto logoutResponseDto = new LogoutResponseDto() ;
       logoutResponseDto.setEmail(session.getUser().getEmail());
       logoutResponseDto.setName(session.getUser().getName());
       logoutResponseDto.setSessionId(session.getId());
       logoutResponseDto.setSessionStatus(session.getSessionStatus().toString());

       return logoutResponseDto ;

   }
}
