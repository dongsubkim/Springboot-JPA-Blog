package com.dskim.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> JAVA Object : table mapping
@Entity // MySQL table creates User table based on this class
// @DynamicInsert // removes fields that have null value on insert statement
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // use strategy of DB connected to this project.
	private int id; // sequence, auto_increment

	@Column(nullable = false, length = 100, unique = true)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	// @ColumnDefault("'user'")
	// DB has no RoleType
	@Enumerated(EnumType.STRING)
	private RoleType role; // should be enum; // ADMIN, USER

	private String oauth; // kakao, google, facebook

	@CreationTimestamp // auto insert timestamp
	private Timestamp createDate;
}
