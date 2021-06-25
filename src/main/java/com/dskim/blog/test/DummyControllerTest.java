package com.dskim.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dskim.blog.model.RoleType;
import com.dskim.blog.model.User;
import com.dskim.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // Dependency Injection
	private UserRepository userRepository;
		
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "Fail to delete a user, cannot find user with id :" + id; 
		}
		
		return "User Deleted id: "+id;
	}
	
	// email, password
	// json data in request body => converted to java object by MessageConvertor
	@Transactional // don't need .save();, auto commit when method finish(dirty checking)
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { 
		System.out.println("id:" + id);
		System.out.println("password: "+requestUser.getPassword());
		System.out.println("email: "+requestUser. getEmail());
		 
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("Fail to update.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// save without id > insert
		// save with id && entity exists > update
		// save with id && entity doesn't exist > insert
		//userRepository.save(requestUser);
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// returns 2 user per page
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	// {id} address > receive as parameter
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4 > if fails,  returns null > might cause problem
		// so wrap User with Optional, then able to judge if it is null or not 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("No user found. id: "+id);
			}
		});
		// request client: web-browser
		// user : java object
		// springboot = MessageConverter converts java object to json using 'Jackson' library on return
		return user;
		
		// Lambda Expression
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("No user found. id: "+id);
//		}); 
//		return user;
		
	}
	
	// http://localhost:8000/blog/dummy/join (request)
	// http body contains username, password, email
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value
		System.out.println("Id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "Successfully registered";
	}
}
