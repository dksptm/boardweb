package co.yedam.member.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;
import co.yedam.common.SearchVO;
import co.yedam.member.Member;
import co.yedam.member.service.MemberService;
import co.yedam.member.service.MemberServiceImpl;

public class MemberListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		page = page == null ? "1" : page;		
		String searchCond = req.getParameter("searchCondition");
		String keyword = req.getParameter("keyword");

		SearchVO search = new SearchVO();
		search.setPage(Integer.parseInt(page));
		search.setSearchCondition(searchCond);
		search.setKeyword(keyword);
		
		MemberService svc = new MemberServiceImpl();
		List<Member> list = svc.memberList();
		req.setAttribute("list", list);
		
		String path = "member/memberList.tiles";
		// WEB-INF/view/member/ 
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
