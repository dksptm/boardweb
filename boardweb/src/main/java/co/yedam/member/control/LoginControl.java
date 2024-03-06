package co.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.common.Control;
import co.yedam.member.Member;
import co.yedam.member.service.MemberService;
import co.yedam.member.service.MemberServiceImpl;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		
		MemberService svc = new MemberServiceImpl();
		member = svc.loginCheck(member); // 만약 없으면 null.
		
		if (member != null) { // 아이디, 비번 => 정상.
			// 세션객체 생성.
			HttpSession session = req.getSession(); // 사용자별로 다른 세션값.
			session.setAttribute("logid", id); // session의 attribute를 활용.
			// 로그아웃하기전까지 페이지마다 값을 공유할 수 있음. 밑의 req.setAttribute와 비교..
			session.setAttribute("logName", member.getName()); // 이름가져오기.
			session.setAttribute("auth", member.getAuth());
			resp.sendRedirect("boardList.do"); 
		} else {
			req.setAttribute("message", "아이디와 비밀번호를 확인하세요."); // 이 경우는 요청값에 매번 값을 넣는것.
			String path = "WEB-INF/view/member/loginForm.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
	}

}
