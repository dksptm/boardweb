package co.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.common.Control;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션객체 얻어오기.
		HttpSession session = req.getSession();
		session.invalidate(); // 세션값을 초기화.(attribute 담아놓았던 값들이 초기화)
		
		String path = "board/loginForm.tiles";
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
