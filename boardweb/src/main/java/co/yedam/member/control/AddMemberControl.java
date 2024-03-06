package co.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.common.Control;
import co.yedam.member.Member;
import co.yedam.member.service.MemberService;
import co.yedam.member.service.MemberServiceImpl;

public class AddMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// MultipartRequest 생성을 위한 5가지 매개변수.
		// 1)요청정보 2)저장경로 3)최대크기 4)인코딩방식 5)리네임정책(파일이름 같은경우->파일이름 새로)
		
		String savePath = req.getServletContext().getRealPath("images"); // 저장경로.
		int maxSize = 1024 * 1024 * 5; // 5mb. 최대크기.
		String enc = "utf-8"; // 인코딩방식.
		// new DefaultFileRenamePolicy() 리네임정책.
		MultipartRequest multi = 
				new MultipartRequest(req, savePath, maxSize, enc, new DefaultFileRenamePolicy());
		
		// multi에서 파라미터를 읽어들여야 함.
		String id = multi.getParameter("id");
		String pw = multi.getParameter("pw");
		String name = multi.getParameter("name");
		String img = multi.getFilesystemName("image"); // 변경된 파일의 이름.
		// cos파일이 05월 파일이어야 한다. (09는 리네임이 db에 안들어옴)
		
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setImage(img);
		
		MemberService svc = new MemberServiceImpl();
		if(svc.addMember(member)) {
			resp.sendRedirect("memberList.do"); 
		} else {
			req.setAttribute("message", "회원등록 중 에러가 발생했습니다.");
			String path = "WEB-INF/view/error.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}

}
