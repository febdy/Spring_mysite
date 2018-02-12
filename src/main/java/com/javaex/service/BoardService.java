package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	private final int numByPage = 5;

	public List<BoardVo> getList(int page) {
		int start = numByPage * (page - 1);
		int end = numByPage * page;

		return boardDao.getList(start, end);
	}

	public int getMaxPageNum() {
		int maxPageNum = boardDao.getMaxPageNum();

		if (maxPageNum % numByPage == 0)
			return maxPageNum / numByPage;
		else
			return maxPageNum / numByPage + 1;
	}

	public BoardVo getArticle(int no) {
		return boardDao.getArticle(no);
	}

	@Transactional
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

	public void write(BoardVo boardVo, UserVo authUser) {
		boardVo.setUserNo(authUser.getNo());
		boardVo.setName(authUser.getName());
		boardDao.write(boardVo);
	}

	public void delete(int no) {
		boardDao.delete(no);
	}

	public List<BoardVo> search(String kwd) {
		return boardDao.search(kwd);
	}
}
