package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserVo login(String email, String password) {

		return userDao.getUser(email, password);
	}
	
	public UserVo getUser(int no) {
		
		return userDao.getUser(no);
	}

	public void modify(UserVo authUser, UserVo newUserVo) {
		if(authUser.getNo() == newUserVo.getNo()) {
			UserVo userVo = userDao.getUser(authUser.getNo());
			
			userVo.setName(newUserVo.getName());
			if(newUserVo.getPassword() != "")
				userVo.setPassword(newUserVo.getPassword());
			userVo.setGender(newUserVo.getGender());
			
			userDao.modify(userVo);
		}
	}
}
