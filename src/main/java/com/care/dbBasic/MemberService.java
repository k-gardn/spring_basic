package com.care.dbBasic;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Spring에 등록 : 인스턴스를 만들고 종료하는 것을 알아서 하겠다.
public class MemberService {
	
	@Autowired private MemberDAO memberDao;
	@Autowired private HttpSession session; // 이렇게 사용 가능
	
	public String login(MemberDTO member) { // HttpSession session이런거 매개변수로 사용 못 함
		if(member.getId() == null || member.getId().isEmpty()) {
			return "아이디를 입력하세요";
		}
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요";
		}
		
		// 추가로 아이디, 비밀번호 문자열 검증
		
		MemberDTO check = memberDao.selectId(member.getId());
		//memberDao.disConnection(); // db연결 끊기
		
		if(check != null && check.getPw().equals(member.getPw())){
			session.setAttribute("id", check.getId());
			session.setAttribute("name", check.getName());
			session.setAttribute("email", check.getEmail());
			return "로그인 되었습니다.";
			//response.sendRedirect("index.jsp"); // 컨트롤러에서 해야하기 때문에 문자열만 보내줄 예정.
		}
		return "로그인을 재시도 하세요.";
	}
	
	public String register(MemberDTO member, String confirmPw) {
		if(member.getId() == null || member.getId().isEmpty()){
			return "아이디를 입력하세요.";
		}
		
		if(member.getPw() == null || member.getPw().isEmpty()){
			return"비밀번호를 입력하세요.";
		}
		
		if(member.getPw().equals(confirmPw) == false){
			return "두 비밀번호를 동일하게 입력하세요.";
		}
		
		// 아이디 중복 확인
		MemberDTO check = memberDao.selectId(member.getId());
		if(check == null){
			// 아이디가 중복이 아니면 테이블에 데이터 저장
			memberDao.register(member);
			return "회원 정보가 등록되었습니다.";
		}
		// 아이디 중복
		return "이미 가입된 아이디 입니다.";
	}
}
