package co.yedam.board.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Board;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;
import co.yedam.common.Control;
import co.yedam.common.PageDTO;
import co.yedam.common.SearchVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// req에서 파라미터를 읽어온다.
		// page파라미터
		String page = req.getParameter("page");
		page = page == null ? "1" : page;		
		// search파라미터
		String searchCond = req.getParameter("searchCondition");
		String keyword = req.getParameter("keyword");
		
		// 검색조건.(조회조건).
		SearchVO search = new SearchVO();
		search.setPage(Integer.parseInt(page));
		search.setSearchCondition(searchCond);
		search.setKeyword(keyword);
		
		BoardService svc = new BoardServiceImpl();
		List<Board> list = svc.boardList(search);
		
		// 페이지정보 넘겨주기.
		PageDTO pageDTO = new PageDTO(Integer.parseInt(page), svc.boardToatalCnt(search));
		
		// boardList.do 페이지에서 boardList.jsp로 데이터 전달.
		// list를 요청정보 req에 담기. -> jsp에서 나와줘야.
		req.setAttribute("list", list);
		req.setAttribute("page", pageDTO);
		//페이지 넘어가도 계속 searchCondition=W&keyword=호두&page=3이렇게.. -> jsp도 수정.(어트리뷰트 불러와서 변수담기)
		req.setAttribute("searchCondition", searchCond);
		req.setAttribute("keyword", keyword);
		
		// 페이지 재지정.
//		String path = "WEB-INF/view/boardList.jsp";
		// ~.do 는 프론트컨트롤러와 컨트롤에서. 다만 타일즈에서 jsp를 보여줌.
		String path = "board/boardList.tiles";		
		req.getRequestDispatcher(path).forward(req, resp); // forward(req, resp)-->파라미터도 같이넘어감-->jsp에서 request

	}

}
