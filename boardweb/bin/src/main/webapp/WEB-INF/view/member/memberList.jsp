<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.center {
  text-align: center;
  width: 60%;
  margin: auto;
}
</style>

<br>
<h3 class="center">─── 회원목록 ─── </h3>
<br>
<table class="table center">
  <thead>
    <tr>
      <th>아이디</th>
      <th>이름</th>
      <th>권한</th>
      <th>이미지이름</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${list }" var="member">
    <tr>
      <td>${member.id }</td>
      <td>${member.name }</td>
      <td>${member.auth }</td>
      <td>${member.image }</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
