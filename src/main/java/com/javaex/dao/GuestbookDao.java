package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	public List<GuestVo> getList() {
		return sqlSession.selectList("guestbook.getList");
	}
	
	public void add(GuestVo guestVo) {
		sqlSession.insert("guestbook.add", guestVo);
	}
}
