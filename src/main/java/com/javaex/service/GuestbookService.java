package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestVo> getList() {
		return guestbookDao.getList();
	}

	public void add(GuestVo guestVo) {
		guestbookDao.add(guestVo);
		String date = guestbookDao.getDate(guestVo);
		guestVo.setDate(date);
	}

	public int delete(int no, String password) {
		return guestbookDao.delete(no, password);
	}

	public List<GuestVo> selectListByPage(int page) {
		return guestbookDao.selectListByPage(page);
	}
}
