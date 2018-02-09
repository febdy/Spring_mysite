package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;

	@RequestMapping("/form")
	public String form() {
		return "fileupload/form";
	}

	@RequestMapping("/upload")
	public String upload(@RequestParam MultipartFile file, Model model) {
		String saveName = fileUploadService.restore(file);

		String url = "upload/" + saveName;
		model.addAttribute("url", url);

		return "fileupload/result";
	}
}
