package co.yedam.board.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Book;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;
import co.yedam.common.Control;

public class AddBookControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		
		// userAjax에서 등록버튼 눌렀을때 이벤트를 발생하고(JSP는 새로운페이지연결하면서 파라미터 전달)
		// addAjax.open에서 get방식으로 addBook.do?bcode= 이런식으로 하나하나 파라미터 전달한다.
		String bcode = req.getParameter("bcode");
		String bname = req.getParameter("bname");
		String bauthor = req.getParameter("bauthor");
		String bpress = req.getParameter("bpress");
		String bprice = req.getParameter("bprice");
		
		Book book = new Book();
		book.setBookCode(bcode);
		book.setBookName(bname);
		book.setAuthor(bauthor);
		book.setPress(bpress);
		book.setPrice(Integer.parseInt(bprice));
		
		BoardService svc = new BoardServiceImpl();
		
		// 반환값을 json 형태로 넘기기.
		try {
			if (svc.addBook(book)) {
				//{"retCode":"OK"}
				resp.getWriter().print("{\"retCode\":\"OK\"}");		
			}
		} catch (Exception e){
			//{"retCode":"NG"}
			resp.getWriter().print("{\"retCode\":\"NG\"}");	
		}
		
		
	}

}
