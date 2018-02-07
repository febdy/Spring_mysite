package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String deleteform(Model model, @RequestParam int no) {
		model.addAttribute("no", no);

		return "guestbook/deleteform";
	}

	@RequestMapping("/guestbook/delete")
	public String delete(@RequestParam int no, @RequestParam String password) {
		guestbookService.delete(no, password);

		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/guestbook/listajax")
	public String listajax(HttpSession session) {

		return "guestbook/listajax";
	}

}
