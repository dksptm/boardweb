<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


  <br>
  <h3>─── 글 삭제 ───</h3>
  <br>
  <form action="removeBoard.do">
    <input type="hidden" value="${board.boardNo }" name="bno">
    <table class="table">
      <tr class="table-success">
        <th>글번호</th>
        <td>${board.boardNo }</td>
      </tr>
      <tr class="table-light">
        <th>글제목</th>
        <td>${board.title }</td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <button type="submit" class="btn btn-danger">삭제</button>
          <button type="reset" class="btn btn-secondary">취소</button>
        </td>
      </tr>
    </table>
  </form>
  
