package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class ApiUserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/user/api/emailcheck", method = RequestMethod.POST)
	public boolean emailCheck(@RequestBody UserVo userVo) {
		boolean result = userService.emailCheck(userVo.getEmail());

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/user/api/jsontest", method = RequestMethod.GET)
	public boolean jsontest(@RequestBody UserVo userVo) {

		return true;// result;
	}
}
