package co.yedam.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Board;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;
import co.yedam.common.Control;

public class AddBoard implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post 방식으로 넘겼기때문에, 인코딩방식을 지정해줘야 함.
		req.setCharacterEncoding("UTF-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		BoardService svc = new BoardServiceImpl();
		if (svc.addBoard(board)) {
			resp.sendRedirect("boardList.do"); 
		} else {
			req.setAttribute("message", "등록 중 에러가 발생했습니다.");
			String path = "board/error.tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
	}

}
