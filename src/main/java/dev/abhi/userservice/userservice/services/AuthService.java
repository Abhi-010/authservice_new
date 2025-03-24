package dev.abhi.userservice.userservice.services;

import dev.abhi.userservice.userservice.dtos.LoginResponseDto;
import dev.abhi.userservice.userservice.dtos.LogoutResponseDto;
import dev.abhi.userservice.userservice.dtos.TokenValidResponseDto;
import dev.abhi.userservice.userservice.models.Session;
import dev.abhi.userservice.userservice.models.SessionStatus;
import dev.abhi.userservice.userservice.models.User;
import dev.abhi.userservice.userservice.repo.SessionRepository;
import dev.abhi.userservice.userservice.repo.UserRepository;
import dev.abhi.userservice.userservice.dtos.UserResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
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

   // login :
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

       // Create a test key suitable for the desired HMAC-SHA algorithm:
       MacAlgorithm alg = Jwts.SIG.HS512; //or HS384 or HS256
       SecretKey key = alg.key().build();

       //String message = "Hello World!";
       byte[] content = password.getBytes(StandardCharsets.UTF_8);
// Create the compact JWS:
       String jws = Jwts.builder().content(content, "text/plain").signWith(key, alg).compact();

// Parse the compact JWS:
       //content = Jwts.parser().verifyWith(key).build().parseSignedContent(jws).getPayload();
       //assert message.equals(new String(content, StandardCharsets.UTF_8));


       Session session = new Session();
       session.setUser(savedUser);
       //session.setToken(RandomStringUtils.randomAlphanumeric(30));
       session.setToken(jws);
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
       //System.out.println("sessionId : " + sessionId);
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

   public TokenValidResponseDto validateToken(String token,Long userId){

       Optional<Session> optionalSession =
               sessionRepository.findByTokenAndUserId(token,userId) ;

       if(optionalSession.isEmpty()){
           System.out.println("No token with this user");
           return null  ;
       }

       Session session = optionalSession.get();
       TokenValidResponseDto tokenValidResponseDto = new TokenValidResponseDto() ;
       if(session.getSessionStatus() == SessionStatus.ACTIVE &&
       System.currentTimeMillis() < session.getExpiringTimeinMiliis()){
           tokenValidResponseDto.setMessage("Valid Token");
           return tokenValidResponseDto ;
       }


//       List<Session> sessionList = sessionRepository.findAll() ;
//       TokenValidResponseDto tokenValidResponseDto = new TokenValidResponseDto() ;
//       for(Session session : sessionList){
//           if(session.getToken().equals(token) && session.getSessionStatus() == SessionStatus.ACTIVE &&
//                   System.currentTimeMillis() < session.getExpiringTimeinMiliis() ){
//               tokenValidResponseDto.setMessage("Valid Token");
//               return tokenValidResponseDto ;
//           }
//       }
       tokenValidResponseDto.setMessage("Invalid token");
       return tokenValidResponseDto ;
   }
}
