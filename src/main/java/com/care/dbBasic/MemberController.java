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
		if(result.equals("회원 정보가 등록되었습니다.")) {
			//가입성공
			ra.addFlashAttribute("msg", result);
			return "redirect:index";
		}
		//가입실패
		model.addAttribute("msg", result);
		return "member/register";
	}
	
	@RequestMapping("list")
	public String list() {
		
		return "member/list";
	}
	
	@GetMapping("update")
	public String update() {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null || sessionId.isEmpty()) {
			return "redirect:login";
		}
		return "member/update";
	}
	
	@PostMapping("update")
	public String update(MemberDTO member, String confirmPw, Model model, RedirectAttributes ra) {
		
		String result = service.update(member, confirmPw);
		if(result.equals("회원 정보가 수정되었습니다.")) {
			ra.addFlashAttribute("msg", result);
			session.invalidate();
			return "redirect:index";
		}
		
		model.addAttribute("msg", result);
		return "member/update";
	}
	
	@GetMapping("delete")
	public String delete() {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null || sessionId.isEmpty()) {
			return "redirect:login";
		}
		return "member/delete";
	}
	
	@PostMapping("delete") // <form action="delete" method="post" id="f"> post의 action과 같아야한다.
	public String delete(String pw, String confirmPw, Model model, RedirectAttributes ra) {
		
		/*
		 * 사용자가 바로 주소를 쳐서 들어오면 안되기 때문에 
		 * 로그인을 해야지만 들어올 수 있게 하기 위함.
		 */
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null || sessionId.isEmpty()) {
			return "redirect:login";
		}
		
		String result = service.delete(pw, confirmPw);
		if(result.equals("회원 정보가 삭제되었습니다.")) {
			ra.addFlashAttribute("msg", result);
			session.invalidate();
			return "redirect:index";
		}
		
		model.addAttribute("msg", result);
		return "member/delete";
	}

}

