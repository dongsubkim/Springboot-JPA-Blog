package com.dskim.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dskim.blog.model.User;

// DAO
// automatically registered to bean (on memory) (IoC)
// @Repository // can omit
public interface UserRepository extends JpaRepository<User, Integer> {
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}


// JPA Naming strategy
// SELECT * FROM user WHERE username = ?1 AND password = ?2;
//User findByUsernameAndPassword(String username, String password);

////@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery=true)
////User login(String username, String password);
