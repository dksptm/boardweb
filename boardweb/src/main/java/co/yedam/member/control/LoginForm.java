package co.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;

public class LoginForm implements Control {

	// 로그인 화면 이동.
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String path = "board/loginForm.tiles";
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
