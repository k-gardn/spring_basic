package com.care.dbBasic;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired	private MemberDAO memberDao;
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
		
		if(pw == null || pw.isEmpty()){
			return "비밀번호를 입력하세요.";
		}
		
		if(pw.equals(confirmPw) == false){
			return "두 비밀번호를 동일하게 입력하세요.";
		}
		
		String sessionId = (String)session.getAttribute("id");
		MemberDTO check = memberDao.selectId(sessionId);
		
		if(pw.equals(check.getPw())){
			memberDao.delete(sessionId);
			return "회원 정보가 삭제되었습니다.";
		}
		return "비밀번호가 잘 못 되었습니다.";
	}
	
	
}













