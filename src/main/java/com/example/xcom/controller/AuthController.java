package com.example.xcom.controller;

import com.example.xcom.authentication.JwtUtil;
import com.example.xcom.authentication.requests.*;
import com.example.xcom.common.ApiResponse;
import com.example.xcom.model.Role;
import com.example.xcom.model.User;
import com.example.xcom.repository.JwtUserRepository;
import com.example.xcom.service.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/hello")
    public String hello(){
        return  "Sourav here";
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
//        Authentication authenticate = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//        );
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//
//    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {


        if(userRepository.findUserByEmail(signUpRequest.getEmail()) != null) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User jwtUser = new User();
        jwtUser.setEmail(signUpRequest.getEmail());
        jwtUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(jwtUser);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        User user =  userRepository.findUserByEmail(userDetails.getUsername());
        long id = user.getUserId();
        System.out.println("ID ::::::::::::: " + id);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,id));
    }

    //TODO need to improve this method. Should be able to throw the exceptions
    @GetMapping("/checkUser")
    public ResponseEntity<UserResponse> checkUser() throws ExpiredJwtException {
        String s = "";
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            User user = userRepository.findByEmail(email);
            long id = user.getUserId();
            return new ResponseEntity<UserResponse>(new UserResponse(email,id,true),HttpStatus.OK);
        }catch (ExpiredJwtException e){
            s = e.getMessage();
        }
        return new ResponseEntity<UserResponse>(new UserResponse("", 222L,false),HttpStatus.OK);

    }


}
