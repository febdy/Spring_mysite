package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList(int start, int end) {
		Map<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);

		return sqlSession.selectList("board.getList", map);
	}

	public int getMaxPageNum() {
		return sqlSession.selectOne("board.getMaxPageNum");
	}

	public BoardVo getArticle(int no) {
		return sqlSession.selectOne("board.getArticle", no);
	}

	public void updateHit(int no) {
		sqlSession.update("board.updateHit", no);
	}

	public void modify(BoardVo boardVo) {
		sqlSession.update("board.modify", boardVo);
	}

	public void write(BoardVo boardVo) {
		sqlSession.insert("board.write", boardVo);
	}

	public void delete(int no) {
		sqlSession.delete("board.delete", no);
	}

	public List<BoardVo> search(String kwd) {
		return sqlSession.selectList("board.search", "%" + kwd + "%");
	}

}
