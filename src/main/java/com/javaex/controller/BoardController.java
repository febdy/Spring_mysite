package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

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

	@RequestMapping("/board/view")
	public String view(@RequestParam int no, Model model) {
		BoardVo article = boardService.view(no);
		model.addAttribute("boardVo", article);
		model.addAttribute("no", no);

		return "board/view";
	}

	@RequestMapping("/board/modifyform")
	public String modifyform(@RequestParam int no, Model model) {
		BoardVo article = boardService.getArticle(no);
		model.addAttribute("boardVo", article);

		return "board/modify";
	}

	@RequestMapping("/board/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		boardService.modify(boardVo);

		return "redirect:/board/list";
	}

	@RequestMapping("/board/writeform")
	public String writeform() {

		return "board/write";
	}

	@RequestMapping("/board/write")
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardService.write(boardVo, authUser);

		return "redirect:/board/list";
	}

}
