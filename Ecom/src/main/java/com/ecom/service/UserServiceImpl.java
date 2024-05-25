package com.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.config.JwtProvider;
import com.ecom.exception.UserException;
import com.ecom.model.User;
import com.ecom.repository.UserRepo;


@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	
	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user= userRepo.findById(userId);
		if(user.isPresent())
		{
			return user.get();
		}
		
		throw new UserException("No user Found with id :"+userId);
		
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromToken(jwt);
		
		User user=userRepo.findByEmail(email);
		if(user==null)
		{
			throw new UserException("No user found with email: "+email);
		}
		
		return user;
	}

}
