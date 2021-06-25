package com.dskim.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dskim.blog.model.User;
import com.dskim.blog.repository.UserRepository;

@Service // Spring component scans > register to Bean: IoC
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void join(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly=true) // on SELECT, transaction starts; transaction exits when service finish (DB Consistency) 
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	
}
