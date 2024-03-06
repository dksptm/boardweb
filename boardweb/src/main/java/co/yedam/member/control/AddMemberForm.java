package co.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;

public class AddMemberForm implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "member/addMemberForm.tiles";
		// /WEB-INF/view/member/{1}.jsp 를 찾겠다는 의미
		// * => /WEB-INF/view/member/~~.jsp
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
