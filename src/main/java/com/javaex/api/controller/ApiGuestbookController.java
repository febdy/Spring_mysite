package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@ResponseBody
	@RequestMapping(value = "/guestbook/api/list", method = RequestMethod.POST)
	public List<GuestVo> apiList(@RequestParam int page) {
		List<GuestVo> gList = guestbookService.selectListByPage(page);

		return gList;
	}

}
