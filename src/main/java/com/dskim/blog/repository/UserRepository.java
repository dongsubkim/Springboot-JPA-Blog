package com.dskim.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dskim.blog.model.User;

// automatically registered to bean (on memory) (IoC)
// @Repository // can omit
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
