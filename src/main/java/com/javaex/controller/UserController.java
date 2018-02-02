package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/user/loginform")
	public String loginform() {

		return "user/loginform";
	}

	@RequestMapping("/user/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		UserVo authUser = userService.login(email, password);

		if (authUser != null) {
			System.out.println("Login :: " + authUser.toString());
			session.setAttribute("authUser", authUser);
			return "main/index";
		} else {
			return "redirect:/user/loginform?result=fail";
		}

	}

	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");

		return "redirect:/main";
	}

	@RequestMapping("/user/modifyform")
	public String modifyform(Model model, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		return "user/modifyform";
	}

	@RequestMapping("/user/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		userService.modify(authUser, userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/main";
	}
}
