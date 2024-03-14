<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

  
	<br>
	<h3>─── 글 수정 ───</h3>
	<br>
	<form action="modifyBoard.do">
	<input type="hidden" value="${board.boardNo }" name="bno">
	  <table class="table">
	    <tr>
	      <th>글번호</th>
	      <td>${board.boardNo }</td>
	      <th>조회수</th>
	      <td>${board.viewCnt }</td>
	    </tr>
	    <tr>
	      <th>글제목</th>
	      <td colspan="3"><input class="form-control" type="text" name="title" value="${board.title }"></td>
	    </tr>
	    <tr>
	      <th>글내용</th>
	      <td colspan="4"><textarea class="form-control" name="content">${board.content }</textarea></td>
	    </tr>
	    <tr>
	      <th>작성자</th>
	      <td>${board.writer }</td>   
	      <th>작성일시</th>
	      <td><fmt:formatDate value="${board.createDate }" pattern="yy/MM/dd HH:mm:ss" /> </td>
	    </tr>
	    <tr>
	      <td colspan="4" align="center">
	        <button type="submit" class="btn btn-primary" ${board.writer eq logid ? '' : 'disabled' }>저장</button>
	        <button type="reset" class="btn btn-secondary" ${board.writer eq logid ? '' : 'disabled' }>취소</button>
	      </td>
	    </tr>
	  </table>
	</form>

