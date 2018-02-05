package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public UserVo getUser(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);

		return sqlSession.selectOne("user.selectUserByEmailPw", map);
	}
	
	public UserVo getUser(int no) {
		return sqlSession.selectOne("user.selectUserByNo", no);
	}
	
	public UserVo getUser(String email) {
		return sqlSession.selectOne("user.selectUserByEmail", email);
	}
	
	public void modify(UserVo userVo) {
		sqlSession.update("user.modify", userVo);
	}
	
	public int join(UserVo userVo) {
		return sqlSession.insert("user.join", userVo);
	}
	
}
