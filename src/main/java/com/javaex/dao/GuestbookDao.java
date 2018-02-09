package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public int delete(int no, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);

		return sqlSession.delete("guestbook.delete", map);
	}

	public List<GuestVo> selectListByPage(int page) {
		return sqlSession.selectList("guestbook.selectListByPage", page);
	}

	public String getDate(GuestVo guestVo) {
		return sqlSession.selectOne("guestbook.getDate", guestVo.getNo());
	}
}
