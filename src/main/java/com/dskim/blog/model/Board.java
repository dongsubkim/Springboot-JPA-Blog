package com.dskim.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // large data
	private String content; // summernote library (includes html tags)

	private int count; // view-count

	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One
	@JoinColumn(name = "userId")
	private User user; // DB cannot save object, use FK. Java can save object

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy means `I am not FK, do not create column`
	@JsonIgnoreProperties({ "board" }) // 무한 참조 방지
	private List<Reply> replies;

	@CreationTimestamp
	private Timestamp createDate;
}
