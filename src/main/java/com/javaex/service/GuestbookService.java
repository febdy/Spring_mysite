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

	public List<GuestVo> getList(){
		List<GuestVo> gList = guestbookDao.getList();
		
		return gList;
	}
	
	public void add(GuestVo guestVo) {
		guestbookDao.add(guestVo);
	}
}