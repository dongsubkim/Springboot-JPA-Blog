package com.dskim.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dskim.blog.dto.ReplySaveRequestDto;
import com.dskim.blog.model.Board;
import com.dskim.blog.model.User;
import com.dskim.blog.repository.BoardRepository;
import com.dskim.blog.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service // Spring component scans > register to Bean: IoC
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;

//	public BoardService(BoardRepository bRepo, ReplyRepository rRepo) {
//		this.boardRepository = bRepo;
//		this.replyRepository = rRepo;
//	}

//	@Autowired
//	private BoardRepository boardRepository;
//
//	@Autowired
//	private ReplyRepository replyRepository;

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

	@Transactional
	public void update(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("Fail to update post: cannot find id.");
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}

	@Transactional
	public void postReply(ReplySaveRequestDto replySaveRequestDto) {
//		User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
//			return new IllegalArgumentException("Fail to post reply: cannot find board id.");
//		});
//
//		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> {
//			return new IllegalArgumentException("Fail to post reply: cannot find board id.");
//		});
//
//		Reply reply = Reply.builder().user(user).board(board).content(replySaveRequestDto.getContent()).build();

//		Reply reply = new Reply();
//		reply.update(user, board, replySaveRequestDto.getContent());
//		replyRepository.save(reply);
		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(),
				replySaveRequestDto.getContent());
	}
}
