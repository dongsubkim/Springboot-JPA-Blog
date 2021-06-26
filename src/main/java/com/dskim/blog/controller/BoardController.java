package com.dskim.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dskim.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("Login username: "+principal.getUsername());
		return "index";
	}
}
