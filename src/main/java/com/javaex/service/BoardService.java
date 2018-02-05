package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {
		return boardDao.getList();
	}

	public BoardVo getArticle(int no) {
		return boardDao.getArticle(no);
	}
	
	public BoardVo view(int no) {
		BoardVo boardVo = getArticle(no);
		boardDao.updateHit(no);
		
		return boardVo;
	}

	public void modify(BoardVo newBoardVo) {
		BoardVo boardVo = getArticle(newBoardVo.getNo());
		boardVo.setTitle(newBoardVo.getTitle());
		String newContent = newBoardVo.getContent().replace("\r\n", "<br>");
		boardVo.setContent(newContent);

		boardDao.modify(boardVo);
	}
}
