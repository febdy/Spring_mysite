package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping("/guestbook/list")
	public String list(HttpSession session) {
		List<GuestVo> gList = guestbookService.getList();
		session.setAttribute("gList", gList);

		return "guestbook/list";
	}

	@RequestMapping("/guestbook/add")
	public String add(@ModelAttribute GuestVo guestVo) {
		guestbookService.add(guestVo);

		return "redirect:/guestbook/list";
	}

	@RequestMapping("/guestbook/deleteform")
	public String deleteform() {

		return "guestbook/deleteform";
	}

	@RequestMapping("/guestbook/delete")
	public String delete() {

		return "redirect:/guestbook/list";
	}
}
