<%@page import="co.yedam.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- jsp/removeBoard.jsp "< % @   % >"안의 내용들은 jsp, 없으면 그냥 html이다.-->
	<!-- init, service, doget, dopost 없는데 왜 가능?-->
	<!-- C:\Dev\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\boardweb\org\apache\jsp\jsp-->
	<!-- 우리가 jsp파일을 만들면 톰캣이 java, class파일을 만든다. -->
	<!-- 내부적으로는 서블릿클래스가 만들어져서 그것이 실행되는것. -> jsp를 만들어도 톰캣이 실행하는것은 서블릿이다. -->
	<h3>게시글 삭제기능.</h3>
	<%
		// 자바영역. 자바코드 사용
		
		// import -> ctrl shift 안됨 -> DAO끝에 ctrl space
		BoardDAO dao = new BoardDAO();
		String bno = request.getParameter("bno");
		
		if (dao.deleteBoard(Integer.parseInt(bno))) {
	%>
			<p>삭제되었습니다.</p>	
	<%
		} else {	
	%>
			<p>삭제되었습니다.</p>	
	<%			
		}	
	%>
	
	<!-- jsp와 서블릿 : jsp는 html을 쓰기 편하다. 주로 화면 확인하는 용도. 서블릿은 실제 처리하기위한 용도로 사용한다. -->
	<!-- 이제는 템플릿으로 만들어보자.. -->
	
	
</body>
</html>