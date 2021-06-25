package com.dskim.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Client request -> Response HTML file
// @Controller

// Client request -> Response Data
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().password("1234").username("dskim").email("a@mail.com").build();
		System.out.println(TAG+"getter: "+m.getUsername());
		m.setUsername("500");
		System.out.println(TAG+"setter: "+m.getUsername());
		return "lombok test finished";
	}
	
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get request :" + m.getId() +", "+ m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // MessageConverter
		return "post request :" + m.getId() +", "+ m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
//		return "post request :" + text;
	}
	
	// http://localhost:8080/http/get (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put request :" + m.getId() +", "+ m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8080/http/get (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete request";
	}
}
