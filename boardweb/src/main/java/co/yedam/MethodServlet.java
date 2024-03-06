package co.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Board;
import co.yedam.board.BoardDAO;

/**
 * Servlet implementation class MethodServlet
 */
@WebServlet(
		name = "methodServ", 
		urlPatterns = { 
				"/methodServ", 
				"/method"
		})
public class MethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * init, service, destroy 없다.
     * 만약 서비스 있으면? 무조건 이것을 한다. 겟이든 포스트든. 두겟두포스트있어도.
     *           없으면? 두겟,두포스트있어야함.
     * 
     * service가 없으면
     * 요청방식 : get방식요청(주소표시줄 엔터, url직접입력, 링크) ->doGet..
     *          post방식요청 form태그의 method="post"하면 그냥 post요청 ->페이지 직접 만들어야 함
     * 
     * 우리가 인터넷url을 입력하고 엔터하면 우리가 모르는사이에 우리의 ip정보, 브라우저 버전정보 등등이 
     * 서버로 넘어감 이 값들이 안넘어가면 서버에서 클라이언트에 응답을 할 수 없음.---이러한 처리들을 서블릿클래스가 해주는것.
     *          
     * @see HttpServlet#HttpServlet()
     */
    public MethodServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 두겟 요청 > 요청헤더에 들어감 > 다볼수있음
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// HttpServletRequest request -> 사용자의 입력하는값, 정보들을 담은 객체.

		// 등록기능: 제목, 내용, 작성자
		String title = request.getParameter("title"); //요청정보의 값중에서 title에 저장된 값을 읽음
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		// (~b/methodServ?title=테스트&content=테스트내용입니다&writer=admin)
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		BoardDAO dao = new BoardDAO();
		if (dao.insertBoard(board)) {
			response.getWriter().print("OK-등록"); //웹브라우저에 OK출력
		} else {
			response.getWriter().print("NG-등록"); //웹브라우저에 NG출력
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 포스트 요청 > 요청몸체에 들어감 > 인코딩, 디코딩방식도 지정해줘야.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title"); 
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String bno = request.getParameter("bno");
		
		// 게시글번호의 값을 변경하는 기능.
		Board board = new Board();
		board.setBoardNo(Integer.parseInt(bno));
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		BoardDAO dao = new BoardDAO();
		if (dao.updateBoard(board)) {
			response.getWriter().print("OK-update"); //웹브라우저에 OK출력
		} else {
			response.getWriter().print("NG-update"); //웹브라우저에 NG출력
		}
		
		
		
		
		
	}

}
