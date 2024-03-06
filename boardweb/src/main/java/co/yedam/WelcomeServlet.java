package co.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.Board;
import co.yedam.board.BoardDAO;

// 서블릿 실행 : http:192.168.0.26:8080/boardweb/welcome.do라는 요청이 들어오면 실행되도록 하겠다.
// http:192.168.0.26:8080/boardweb/welcome.do 요청이 들어오면 실행하도록 하는..기능을 만들어야하는데 
// 이미 만들어진 기능을 상속받음.(HttpServlet 클래스에 그런 기능이 있음)

// 프로젝트의 환경파일 web.xml에 등록. -> 톰캣이 web.xml을 읽어들임.(WEB-INF안에 있음)
// 모든 처리를 톰캣이 함 >  톰캣이 알아들을수 있게.

// html파일과 서블릿의 차이: 서블릿은 톰캣이라는 웹 어플리케이션 서버가 처리. 웹서버와는 조금 다름. 동적인 페이지.
// 어떤 요청이 들어오면 어떤서블릿을 실행할지 -> 서블릿컨테이너. 서블릿들을 관리.
// 서블릿컨테이너, jsp컨테이너.. 다르다. 구분필요.

// 서블릿 컨테이너 : 서블릿의 생명주기(init -> service -> destroy)를 관리해줌. 규칙이 정해져있음(내가한다고 마음대로X)
// (최초요청-메모리로딩-서버종료되면 서블릿도 메모리에서 제거) 각 주기에 맞는 메소드들을 통해 처리해줌.

// init() 	 : 최초 요청때 한번 실행.
// service() : 사용자의 요청이 있을때마다 실행.
// destroy() :  서비스 종료.. (서버종료할때..?)


public class WelcomeServlet extends HttpServlet{

	public WelcomeServlet() {
		System.out.println("WelcomeServlet 실행.joo.");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init실행.joo.");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8"); // 어떻게 보여줄지. 웹브라우저에 전송되는 컨텐츠타입을 알려줌.(이런방식으로 해석하시오)
		
		System.out.println("service실행.joo.");
		
		String html = "<h3>여기는 servlet</h3>";
		html += "<a href='index.html'> 인덱스로 이동 </a><br>";
		
		// 글목록 출력추가.
		BoardDAO dao = new BoardDAO();
		List<Board> list = dao.boardList();
		
		html += "<table border=1><thead><tr><th>글번호</th><th>제목</th><th>작성자</th></tr></thead>";
		html += "<tbody>";
		for (Board board : list) {
			html += "<tr><td>"+board.getBoardNo()
					+"</td><td>"+board.getTitle()
					+"</td><td>"+board.getWriter()
					+"</td></tr>";
		}
		html += "</tbody></table>";
		
		PrintWriter out = resp.getWriter(); // 클라이언트 쪽으로 출력스트림 만들어줌.
		out.print(html); // 
		// 위의 html만들기에는 불편. -> jsp를 통해.
		
	}
	
	public void destroy() {
		System.out.println("distroy실행.joo.");
	}
	
}
