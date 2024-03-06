package co.yedam.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Board;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;
import co.yedam.common.Control;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");
		
		BoardService svc = new BoardServiceImpl();
		Board board = svc.getBoard(Integer.parseInt(bno));
		// 파라미터 bno를 가지고 getBoard->mapper->mapper는 session->
		//sessiont은 DataSource의 getInstance ->"config/mybatis-config.xml" 연결
		//->selectBoard쿼리실행.

		req.setAttribute("board", board);
		// => jsp에서 사용한다. 이렇게! Board board = (Board) request.getAttribute("board");
		
		// jsp를 사용하고 싶어서 아래의 작업을 한다.(html은 페이지만들기 힘들다).
		// board.do ->  board.jsp 하면서
		// req.setAttribute("board", board)으로 jsp에 넘겨준다.
		String path = "board/board.tiles";
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
