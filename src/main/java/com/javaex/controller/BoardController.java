package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/list")
	public String list(Model model) {
		List<BoardVo> bList = boardService.getList();
		model.addAttribute("bList", bList);
		
		return "board/list";
	}

}
