package com.care.dbBasic;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dbBasic.dao.MemberDAO;
import com.care.dbBasic.dao.MemberMapper;

@Service
public class MemberService {
	
	@Autowired	private MemberMapper memberDao;
	@Autowired	private HttpSession session;
	
	public String login(MemberDTO member) {
		if(member.getId() == null || member.getId().isEmpty()) {
			return "아이디를 입력하세요.";
		}
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		// 추가로 아이디, 비밀번호 문자열 검증
		
		MemberDTO check = memberDao.selectId(member.getId());
		if(check != null && check.getPw().equals(member.getPw())){
			session.setAttribute("id", check.getId());
			session.setAttribute("name", check.getName());
			session.setAttribute("email", check.getEmail());
			return "로그인 되었습니다.";
		}
		return "로그인을 재시도 하세요.";
	}

	public String register(MemberDTO member, String confirmPw) {
		if(member.getId() == null || member.getId().isEmpty()) {
			return "아이디를 입력하세요.";
		}
	
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		if(member.getPw().equals(confirmPw) == false){
			return "두 비밀번호를 동일하게 입력하세요.";
		}
		
		// 아이디 중복 확인
		MemberDTO check = memberDao.selectId(member.getId());
		if(check == null){
			memberDao.register(member);
			return "회원 정보가 등록되었습니다.";
		}
		return "이미 가입된 아이디 입니다.";
	}
	
	public String update(MemberDTO member, String confirmPw) {
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		if(member.getPw().equals(confirmPw) == false){
			return "두 비밀번호를 동일하게 입력하세요.";
		}
		
		// 이름 형식, 이메일의 형식, 비밀번호 형식이 맞는지 확인하는 코드를 추가로 할 수 있음.
		
		member.setId((String)session.getAttribute("id"));
		memberDao.update(member);
		return "회원 정보가 수정되었습니다.";
	}

	public String delete(String pw, String confirmPw) {
		if(pw == null || pw.isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		if(pw.equals(confirmPw) == false){
			return "두 비밀번호를 동일하게 입력하세요.";
		}
		
		String sessionId = (String)session.getAttribute("id");
		MemberDTO check = memberDao.selectId(sessionId);
		if(check.getPw().equals(pw)) {
			memberDao.delete(sessionId);
			return "회원 정보가 삭제되었습니다.";
		}
		
		return "비밀번호를 다시 입력하세요";
	}

	public void list(String cp, String search, String select, Model model) {
		if(select == null){
			select = "";
		}
		
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 3; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
	
		ArrayList<MemberDTO> members = memberDao.selectAll(begin, end, select, search);
		int totalCount = memberDao.count(select, search);
		String url = "list?select="+select+"&search="+search+"&currentPage=";
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("members", members);
		model.addAttribute("result", result);
	}
}













