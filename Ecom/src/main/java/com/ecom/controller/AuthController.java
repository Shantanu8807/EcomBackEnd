package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.config.JwtProvider;
import com.ecom.exception.UserException;
import com.ecom.model.User;
import com.ecom.repository.UserRepo;
import com.ecom.request.LoginRequest;
import com.ecom.response.AuthResponse;
import com.ecom.service.CustomUserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserServiceImpl customUserService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException
	{
		String email=user.getEmail();
		String password=user.getPassword();
		String firstname=user.getFirstName();
		String lastname=user.getLastName();
		 
		
		User isEmailExists=userRepo.findByEmail(email);
		
		if(isEmailExists!=null)
		{
			throw new UserException("Email is already used with another account");
		}
		
		User createdUser= new User();
		createdUser.setEmail(email);
		createdUser.setFirstName(firstname);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setLastName(lastname);
		
		userRepo.save(createdUser);
		Authentication authentication= new UsernamePasswordAuthenticationToken(createdUser.getEmail(), createdUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);;
		
		String token=jwtProvider.generateToken(authentication);
		AuthResponse authResponse= new AuthResponse(token,"Signup Success");
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest)
	{
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=jwtProvider.generateToken(authentication);
		AuthResponse authResponse= new AuthResponse(token,"Signin Success");
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
		
		
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails =customUserService.loadUserByUsername(username);
		if(userDetails==null)
		{
			throw new BadCredentialsException("Invalid Username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword()))
		{
			throw new BadCredentialsException("Invalid Password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
	
	
	
	
	

}
