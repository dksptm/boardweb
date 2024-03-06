package co.yedam.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Maincontrol implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// java파일: src/main/java 이클립스가 관리하기위한 목적.
		// web파일 : src/main/webapp. webapp폴더가 가장상위 폴더임.
		String path = "board/main.tiles";
		RequestDispatcher dispatch = req.getRequestDispatcher(path);
		dispatch.forward(req, resp); //main.do -> main.jsp 페이지 재지정.
		
	}

}
