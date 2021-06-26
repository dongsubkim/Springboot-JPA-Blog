package com.dskim.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dskim.blog.model.User;
import com.dskim.blog.repository.UserRepository;

@Service // bean register
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// When Spring intercepts login process, takes two variables `username`, `password`
	// password processes automatically
	// need to check whether username is in DB
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("Cannot find user: "+username);
				});
		return new PrincipalDetail(principal); // on return, user details saves on security's session
	}
}
