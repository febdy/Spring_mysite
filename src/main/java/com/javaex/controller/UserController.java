package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
			System.out.println(authUser.toString());
			session.setAttribute("authUser", authUser);
			return "main/index";
		} else {
			return "redirect:/user/loginform?result=fail";
		}

	}
}
