package com.care.dbBasic;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller //Spring에 등록
public class MemberController {
	
	
	@Autowired private MemberService service; //여러 곳에서 쓰일 것 이기 때문에 멤버 변수로 만듦.
	@Autowired private HttpSession session;
	
	@RequestMapping("index")
	public String index() {
		return "member/index";
	}
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(MemberDTO member, Model model, RedirectAttributes ra) {
		String result = service.login(member);
		if(result.equals("")) {
			// 로그인 성공
			ra.addFlashAttribute("msg", result);
			return "redirect:index";
		}
		// 로그인 실패
		model.addAttribute("msg", result);
		return "member/login";
	}
	
	@GetMapping("logout")
	public String logout(Model model) {
		session.invalidate();
		model.addAttribute("msg", "로그 아웃");
		return "member/index";
	}
	
	@GetMapping("register")
	public String register() {
		
		return "member/register";
	}
	@PostMapping("register")
	public String register(MemberDTO member, String confirmPw, Model model, RedirectAttributes ra) {
		String result = service.register(member, confirmPw);
		if(result.equals("")) {
			//가입성공
			ra.addFlashAttribute("msg", result);
			return "redirect:index";
		}
		//가입실패
		model.addAttribute("msg", result);
		return "member/register";
	}
	
	@GetMapping("list")
	public String list() {
		return "member/list";
	}
	
	@GetMapping("update")
	public String update() {
		return "member/update";
	}
	
	@GetMapping("delete")
	public String delete() {
		return "member/delete";
	}

}

