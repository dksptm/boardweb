package co.yedam.common;

/// 서블릿은 이거 하나다!!

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.control.AddBoard;
import co.yedam.board.control.AddBookControl;
import co.yedam.board.control.AddForm;
import co.yedam.board.control.AddReplyControl;
import co.yedam.board.control.BoardControl;
import co.yedam.board.control.BoardListControl;
import co.yedam.board.control.BookListControl;
import co.yedam.board.control.ModifyBoard;
import co.yedam.board.control.RemoveBoard;
import co.yedam.board.control.RemoveBookControl;
import co.yedam.board.control.RemoveForm;
import co.yedam.board.control.UpdateForm;
import co.yedam.member.control.AddMemberControl;
import co.yedam.member.control.AddMemberForm;
import co.yedam.member.control.LoginControl;
import co.yedam.member.control.LoginForm;
import co.yedam.member.control.LogoutControl;
import co.yedam.member.control.MemberListControl;
import co.yedam.reply.control.RemoveReplyControl;
import co.yedam.reply.control.ReplyListControl;
import co.yedam.reply.control.ReplyTotalCount;

// 생명주기: init > service > destroy.
// 이 프로젝트를 톰캣이 실행하면 제일처름 web.xml에서 front에서 프론트컨트롤러실행되면서 init, service, 이후 .do 요청시마다 service반복
// *.do 로 끝나면 무조건 이 서블릿이 실행되도록 할 것 -> 관리하기 편하다.
public class FrontController extends HttpServlet{	
	
	// Map 타입으로 url 과 실행할 클래스.
	Map<String, Control> controls;
	
	public FrontController(){
		controls = new HashMap<>();
	}
	
	// init. 최초한번 실행.
	@Override
	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
		System.out.println("init 실행.");
//		controls.put( "/a.do", new Acontrol()); 
//		controls.put( "/b.do", new Bcontrol()); 
		controls.put( "/resume.do", new ResumeForm()); 
		controls.put( "/main.do", new Maincontrol());
		// 게시글목록 이동컨트롤.
		controls.put("/boardList.do", new BoardListControl());
		// 단건조회(상세화면) 이동컨트롤.
		controls.put("/board.do", new BoardControl());
		controls.put("/updateForm.do", new UpdateForm());// 수정페이지로 이동.
		controls.put("/modifyBoard.do", new ModifyBoard());// 실제 수정기능.
		controls.put("/removeForm.do", new RemoveForm());// 삭제페이지로 이동.
		controls.put("/removeBoard.do", new RemoveBoard());// 실제 삭제기능.
		controls.put("/addForm.do", new AddForm());// 등록화면.
		controls.put("/addBoard.do", new AddBoard());// 실제 등록처리.
		
		// 회원관련.
		controls.put("/loginForm.do", new LoginForm());// 로그인 화면.
		controls.put("/login.do", new LoginControl());// 로그인 기능.
		controls.put("/logout.do", new LogoutControl());// 로그아웃 기능.

		// 회원등록.
		controls.put("/addMemberForm.do", new AddMemberForm());// 회원등록 기능.
		controls.put("/addMember.do", new AddMemberControl());// 회원등록 화면.
		
		// 회원목록.
		controls.put("/memberList.do", new MemberListControl());// 회원목록.
		
		// 기타.
		controls.put("/productList.do", new ProductListControl());// 상품목록.
		controls.put("/cartList.do", new CartListControl());// 장바구니.
		
		// 자바스크립트 연습.(페이지 열어주는 목적)
		controls.put("/userList.do", new UserListControl());
		
		// Ajax 연습(Book)
		controls.put("/bookList.do", new BookListControl());
		controls.put("/addBook.do", new AddBookControl());
		controls.put("/removeBook.do", new RemoveBookControl());
		
		// Ajax 와 jsp의 차이: jsp는 페이지를 열어주고, Ajax는 데이터를 넘긴다.

		// 댓글관련.
		controls.put("/replyList.do", new ReplyListControl());
		controls.put("/removeReply.do", new RemoveReplyControl());
		controls.put("/addReply.do", new AddReplyControl());
		controls.put("/getTotal.do", new ReplyTotalCount());
		
		// api이용
		controls.put("/registerCenter.do", new RegisterCenter());
		
		// chart.
		controls.put("/getSidoInfo.do", new SidoInfoControl());
		
		
	}
	
	// service. 호출할때마다 실행(요청때마다 실행).
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
//		super.service(req, resp);
		System.out.println("service 실행.");
		String uri = req.getRequestURI(); // 현재 페이지의 url.
		//System.out.println("uri: " + uri); // uri: /boardweb/b.do
		String context = req.getContextPath(); // 어플리케이션 이름.
		//System.out.println("context: " + context); //context: /boardweb
		String path = uri.substring(context.length());
		System.out.println("path: " + path); //path: /b.do
		
		Control control = controls.get(path); // new BoardListControl()의 인스턴스가 하나 반환됨.
		control.exec(req, resp); // 요청 url에 따른 실행컨트롤을 호출. 
	}
	
	// destroy.

}
