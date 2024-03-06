package co.yedam.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;
import co.yedam.common.Control;

public class RemoveBoard implements Control {
	// mapper => int deleteBoard (int)
	// service => boolean removeBoard (int)
	// 정상삭제되면 목록이동, error페이지로 이동

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// bno 파라미터 넘어온다.
		String bno = req.getParameter("bno");
		
		BoardService svc = new BoardServiceImpl();
		if(svc.removeBoard(Integer.parseInt(bno))) {
			resp.sendRedirect("boardList.do"); 
		} else {
			req.setAttribute("message", "삭제 중 에러가 발생했습니다.");
			String path = "board/error.tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
	}

}
