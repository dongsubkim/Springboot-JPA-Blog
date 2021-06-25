package com.dskim.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Spring scans com.cos.blog's sub-packages and `new` class with specific annotations(IoC) on spring container and maintain
@RestController
public class BlogControllerTest {
	// http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
