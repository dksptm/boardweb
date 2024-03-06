package co.yedam.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Board;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;
import co.yedam.common.Control;

public class ModifyBoard implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		// bno, title, content 파라미터 넘어올것.(또는 넘어와야 함)
		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		// 편하게 Board 인스턴스 하나 생성해서, set.
		Board board = new Board();
		board.setBoardNo(Integer.parseInt(bno));
		board.setTitle(title);
		board.setContent(content);
		
		BoardService svc = new BoardServiceImpl();
		if (svc.modifyBoard(board)) {
			resp.sendRedirect("boardList.do"); 
//			resp.sendRedirect("www.daum.net"); 
			// forward 아닌 리다이렉트.
			// forward 는 req, resp 넘길수 있다 리다이렉트는 안된다.
			// 리다이렉트는 서버외의 페이지(다음페이지같은..) 가능!!
		} else {
			req.setAttribute("message", "수정 중 에러가 발생했습니다.");
			String path = "board/error.tiles";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
	}

}
