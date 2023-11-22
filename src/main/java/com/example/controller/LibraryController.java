package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Library;
import com.example.service.LibraryService;
import com.example.service.LoginUser;
import com.example.service.LogsService;

@Controller
@RequestMapping("library")
public class LibraryController {
	private final LibraryService libraryService;
	private final LogsService logService;
	
	@Autowired
	public LibraryController(LibraryService libraryService,LogsService logService) {
		this.libraryService = libraryService;
		this.logService = logService;
		
	}

	@GetMapping()
	public String index(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		List<Library> libraries = this.libraryService.findAll();
		model.addAttribute("libraries", libraries);

		model.addAttribute("name", loginUser.getUsers().getName());

		return "library/index";
	}

	@GetMapping("borrow")
	public String borrowingForm(@RequestParam("id") Integer id, Model model) {
		// 書籍IDに該当する書籍情報を取得
		Library library = libraryService.findById(id);

		// 書籍情報をテンプレートに渡す
		model.addAttribute("library", library);

		return "library/borrowingForm";
	}

	@PostMapping("borrow")
	public String borrow(@RequestParam("id") Integer id,
			@RequestParam("return_due_date") String returnDueDate,
			@AuthenticationPrincipal LoginUser loginUser) {

		Library library = libraryService.findById(id);
		library.setUserId(loginUser.getUsers().getId());


		libraryService.save(library);
		
		
		
		logService.createLog(library.getId(), loginUser.getUsers().getId(), LocalDateTime.parse(returnDueDate));
		return "redirect:/library";
	}
	// http://localhost:8080/library/borrow?id=1
	/* 	-- user1のパスワード → pass_user1
		-- user2のパスワード → pass_user2
	*/

}