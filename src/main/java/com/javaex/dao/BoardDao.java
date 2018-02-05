package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList() {
		return sqlSession.selectList("board.getList");
	}

	public BoardVo getArticle(int no) {
		return sqlSession.selectOne("board.getArticle", no);
	}

	public void modify(BoardVo boardVo) {
		sqlSession.update("board.modify", boardVo);
	}

}
