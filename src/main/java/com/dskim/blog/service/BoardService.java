package com.dskim.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dskim.blog.model.Board;
import com.dskim.blog.model.User;
import com.dskim.blog.repository.BoardRepository;

@Service // Spring component scans > register to Bean: IoC
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void post(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> postList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board viewPost(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("Fail to load post: cannot find id.");
		});
	}

	@Transactional
	public void deletePost(int id) {
		boardRepository.deleteById(id);
	}
}
