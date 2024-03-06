package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResumeForm implements Control {
	
	// database계층에 데이터를 처리해주기위한 적절한 프레임워크.---> mybatis

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "WEB-INF/resume.html";
		req.getRequestDispatcher(path).forward(req, resp); // WEB-INF/resume.html로 재지정
		// 여전히 url은 resume.do ->http://192.168.0.26:8080/boardweb/resume.html 해도 못들어감.
		

	}

}
