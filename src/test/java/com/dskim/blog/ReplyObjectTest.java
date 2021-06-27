package com.dskim.blog;

import org.junit.Test;

import com.dskim.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void toStringTest() {
		Reply reply = Reply.builder().id(1).user(null).board(null).content("hello").build();
		System.out.println(reply);
	}
}
