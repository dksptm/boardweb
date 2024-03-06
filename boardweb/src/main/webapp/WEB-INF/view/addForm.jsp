<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<br>
<h3>─── 등록화면 ─── </h3>
<br>
<form action="addBoard.do" method="post">
  <table class="table">
    <tr class="table-light">
      <th>제목</th>
      <td><input class="form-control" type="text" name="title" placeholder="제목을 입력하세요." required></td>
    </tr>
    <tr>
      <th>내용</th>
      <td><textarea class="form-control" name="content" required>내용을 입력하세요.</textarea></td>
    </tr>
    <tr>
      <th>작성자</th>
      <td><input class="form-control" type="text" name="writer" value="${logid }" readonly required></td>
    </tr>
    <tr>
      <td align="center" colspan="2">
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="reset" class="btn btn-secondary">취소</button>
      </td>
    </tr>
  </table>  
</form>
  