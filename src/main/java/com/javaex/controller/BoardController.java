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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/board/list")
	public String list(@RequestParam(value = "page", required = false, defaultValue = "-1") int page, Model model) {
		if (page == -1)
			page = 1;

		List<BoardVo> bList = boardService.getList(page);

		int maxPageNum = boardService.getMaxPageNum();

		model.addAttribute("bList", bList);
		model.addAttribute("maxPageNum", maxPageNum);
		model.addAttribute("page", page);
		model.addAttribute("type", "list");

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
	public String modifyform(@RequestParam int no, Model model, HttpSession session) {
		BoardVo article = boardService.getArticle(no);
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if ((authUser != null) && (authUser.getNo() == article.getUserNo())) {
			model.addAttribute("boardVo", article);

			return "board/modify";
		} else
			return "redirect:/board/list";
	}

	@RequestMapping("/board/modify")
	public String modify(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if ((authUser != null) && (authUser.getNo() == boardVo.getUserNo()))
			boardService.modify(boardVo);

		return "redirect:/board/list";
	}

	@RequestMapping("/board/writeform")
	public String writeform(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null)
			return "redirect:/board/list";

		return "board/write";
	}

	@RequestMapping("/board/write")
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null)
			return "redirect:/board/list";

		boardService.write(boardVo, authUser);

		return "redirect:/board/list";
	}

	@RequestMapping("/board/delete")
	public String delete(@RequestParam int no, @RequestParam int userNo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if ((authUser != null) && (authUser.getNo() == userNo))
			boardService.delete(no);

		return "redirect:/board/list";
	}

	@RequestMapping("/board/search")
	public String search(@RequestParam String kwd, Model model) {
		List<BoardVo> bList = boardService.search(kwd);
		model.addAttribute("bList", bList);

		return "board/list";
	}

}
