<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.center {
  text-align: center;
  width: 40%;
}
</style>

<c:if test="${!empty message }">
  <p>${message }</p>
</c:if>

<br><br><br>
<form action="login.do" method="post" class="center">
  <table class="table">
    <tr>
      <th>아이디</th>
      <td><input type="text" name="id"></td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td><input type="password" name="pw"></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <button type="submit" class="btn btn-primary">로그인</button>
      </td>
    </tr>
  </table>
</form>
