<%@page import="co.yedam.board.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"></jsp:include>

  <%
	Board board = (Board) request.getAttribute("board");
  %>
  
	<br>
	<h3> --- 상세조회 --- </h3>
	<br>
	<form action="updateForm.do">
	<input type="hidden" value="<%=board.getBoardNo() %>" name="bno">
	  <table class="table">
	    <tr>
	      <th>글번호</th>
	      <td><%=board.getBoardNo() %></td>
	      <th>조회수</th>
	      <td><%=board.getViewCnt() %></td>
	    </tr>
	    <tr>
	      <th colspan="2">글제목</th>
	      <td colspan="2"><%=board.getTitle() %></td>
	    </tr>
	    <tr>
	      <td colspan="4"><%=board.getContent() %></td>
	    </tr>
	    <tr>
	      <th>작성자</th>
	      <td><%=board.getWriter() %></td>   
	      <th>작성일시</th>
	      <td><%=board.getCreateDate() %></td>
	    </tr>
	    <tr>
	      <td colspan="4" align="center">
	        <button type="submit" class="btn btn-primary">수정</button>
	        <button type="button" class="btn btn-warning" onclick="removeFunc()">삭제</button>
	      </td>
	    </tr>
	  </table>
	</form>


<jsp:include page="../includes/footer.jsp"></jsp:include>

<script>
  function removeFunc() {
	  let form = document.querySelector('form');
	  console.log(form.action);
	  form.action = 'removeForm.do';
	  form.submit();
	  form.action = 'updateForm.do';
  }
</script>