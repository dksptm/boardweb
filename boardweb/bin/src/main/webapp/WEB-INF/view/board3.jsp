<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<style>
div.reply div {
  margin: auto;
}
div.reply span {
  display: inline-block;
}
div.reply ul {
  list-style-type: none;
  margin-top: 10px;
}
div.reply li {
  padding-top: 1px;
  padding-bottom: 1px;
}

.center {
  text-align: center;
  width: 60%;
  margin: auto;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>

	<br>
	<h3>─── 상세조회 ───</h3>
	<br>
	<form action="updateForm.do">
	  <input type="hidden" value="${board.boardNo }" name="bno">
	  <table class="table">
	    <tr>
	      <td colspan="4">
	      <c:choose>
	        <c:when test="${board.writer eq logid }">
	          <button type="submit" class="btn btn-primary">수정</button>
	          <button type="button" class="btn btn-warning" onclick="removeFunc()">삭제</button>
	        </c:when>
	        <c:otherwise>
	      	  <button type="submit" class="btn btn-primary" disabled>수정</button>
	          <button type="button" class="btn btn-warning" onclick="removeFunc()" disabled>삭제</button>
	        </c:otherwise>
	      </c:choose>
	      </td>
	    </tr>
	    <tr class="table-light">
	      <th>글번호</th>
	      <td>${board.boardNo }</td>
	      <th>조회수</th>
	      <td>${board.viewCnt }</td>
	    </tr>
	    <tr>
	      <th colspan="2">글제목</th>
	      <td colspan="2">${board.title }</td>
	    </tr>
	    <tr>
	      <td colspan="4">${board.content }</td>
	    </tr>
	    <tr class="table-light">
	      <th>작성자</th>
	      <td>${board.writer }</td>   
	      <th>작성일시</th>
	      <td><fmt:formatDate value="${board.createDate }" pattern="yy/MM/dd HH:mm:ss"/>   </td>
	    </tr>
	  </table>
	</form>
	
<!-- 댓글영역 -->	
<div class="container reply" style="width:80%;">

  <div class="header">
    <input class="col-sm-8" type="text" name="reply" id="reply">
    <button class="col-sm-2 btn btn-outline-success btn-sm addReply">댓글등록</button>
  </div>
  
  <!-- 댓글목록. -->
  <div class="content">  
    <ul>
      <li>
        <span class="col-sm-2">댓글번호</span>
        <span class="col-sm-5">내용</span>
        <span class="col-sm-2">작성자</span>
        <span class="col-sm-2">삭제</span>
      </li>
      <li><hr></li>
      
    </ul>
  </div>
	
  <!-- 댓글페이지. -->
  <div class="footer">
    <div class="center">
      <div class="pagination">
        <a href="">1</a>
        <a href="">2</a>
        <a href="" class="active">3</a>
      </div>
    </div>
  </div>
  
</div> <!-- div.container.reply -->

<script>
  const bno = "${board.boardNo }";
  console.log(bno);
  
  const replyer = "${logid }"; <!--loginControl의 setAttribute("logid", id).-->
  console.log(replyer);

  function removeFunc() {
	  let form = document.querySelector('form');
	  console.log(form.action);
	  form.action = 'removeForm.do';
	  form.submit();
	  form.action = 'updateForm.do';
  }
</script>

<!-- <script src="static/js/boardService.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- <script src="static/js/boardAjax.js"></script> -->
<script type="module" src="static/js/boardService3.js"></script>
