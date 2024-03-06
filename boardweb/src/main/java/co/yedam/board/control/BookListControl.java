package co.yedam.board.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Book;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;
import co.yedam.common.Control;

// jsp는 페이지 만들어서(page생성) 웹브라우저에 보여줌(반환).
// Ajax. -> (페이지는 정해져있고 그 페이지안에서) json값을 반환. 
//컨텐트타입 기본값은 텍스트.(텍스트로 보여줌) -> html등으로 지정해주면 된다 -> setCon..
public class BookListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8"); //json 문자열로 지정.
		// => {"bookCode":"B001", "bookName":"이것이자바다"...} text는 이런 형태로 값이 넘어가야 한다. 그래야 JSON.parse 할수있다.
		// [{}, {}] // 규칙을 잘 지켜야 한다(마지막에 콤마있으면 안된다)
		// 나중에는 json 라이브러리 사용.
		
		BoardService svc = new BoardServiceImpl();
		List<Book> list = svc.bookList();
		
		// list를 json문자열로 만들어보기.
		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json += "{\"bookCode\":\"" + list.get(i).getBookCode() + "\","; // 이스케이프\ 뒤에 ". 
			json += "\"bookName\":\"" + list.get(i).getBookName() + "\",";   
			json += "\"author\":\"" + list.get(i).getAuthor() + "\",";
			json += "\"press\":\"" + list.get(i).getPress() + "\",";
			json += "\"price\":" + list.get(i).getPrice() + "}";
			if (i != list.size()-1) {
				json += ",";
			}
		}
		json += "]";
		
		resp.getWriter().print(json);
	}

}
