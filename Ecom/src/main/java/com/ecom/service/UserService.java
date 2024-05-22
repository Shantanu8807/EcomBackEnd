package com.ecom.service;

import org.springframework.stereotype.Service;

import com.ecom.exception.UserException;
import com.ecom.model.User;


@Service
public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	

	

}
