package com.dskim.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dskim.blog.model.RoleType;
import com.dskim.blog.model.User;
import com.dskim.blog.repository.UserRepository;

@Service // Spring component scans > register to Bean: IoC
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); // hashed password
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);

		userRepository.save(user);
	}

	@Transactional
	public void updateUserInfo(User requestUser) {
		User user = userRepository.findById(requestUser.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("Fail to find user.");
		});
		String rawPassword = requestUser.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setEmail(requestUser.getEmail());

	}
}
