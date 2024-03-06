<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
.center {
  text-align: center;
  width: 40%;
}
</style>

<h3>UserList page</h3>

<!-- <script src="static/js/userList.js"></script> -->
<!-- <script src="static/js/userService.js"></script> -->
<script src="static/js/userAjax.js"></script>

<input type="text" id="name">
<!-- js가 실행되는 시점에는 input을 읽을 수 없다.
	 그래서 전체 html 다 로딩 후 js실행하도록 
	 document에 이벤트 등록(리스너) DOMContentLoaded
	 (돔에 이벤트 핸들러 한다!)
 -->

<!--  
<select id="genderList" class="form-control-lg">
  <option value="All">전체</option>
  <option value="Male">남성</option>
  <option value="Female">여성</option>
</select>-->

<br><br><br>
<table class="table center">
  <tr>
    <th>도서코드</th>
    <td><input type="text" id="bcode" value="B005"></td>
  </tr>
  <tr>
    <th>도서명</th>
    <td><input type="text" id="bname" value="Ajax 배우기"></td>
  </tr>
  <tr>
    <th>저자명</th>
    <td><input type="text" id="bauthor" value="김저자"></td>
  </tr>
  <tr>
    <th>출판사</th>
    <td><input type="text" id="bpress" value="행복출판사"></td>
  </tr>
  <tr>
    <th>가격</th>
    <td><input type="number" id="bprice" value="25000"></td>
  <tr>
    <td colspan="2" align="center">
      <button id="addBtn" class="btn btn-primary">등록</button>
    </td>
  </tr>
</table>
  	
<br><br>
<div id="show">
  <table class="table" id="tableList">
    <thead>
      <!-- 
      <tr>
        <th>id</th>
        <th>first_name</th>
        <th>last_name</th>
        <th>email</th>
        <th>salary</th>
      </tr>
       -->
    </thead>
    <tbody>
      <!--
      <tr>
        <td>101</td>
        <td>효주</td>
        <td>권</td>
        <td>kwon@com</td>
        <td>234</td>   
      </tr>
      -->
    </tbody>
  </table>
</div>