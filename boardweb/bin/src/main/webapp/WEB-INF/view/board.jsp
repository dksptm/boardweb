<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="//cdn.datatables.net/2.0.2/css/dataTables.dataTables.min.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="//cdn.datatables.net/2.0.2/js/dataTables.min.js"></script>

<!-- board -->
<br>
<h3>─── 상세조회 ───</h3>
<br>
<form action="updateForm.do">
	<input type="hidden" value="${board.boardNo }" name="bno">
	<table class="table">
		<tr>
			<td colspan="4"><c:choose>
					<c:when test="${board.writer eq logid }">
						<button type="submit" class="btn btn-primary">수정</button>
						<button type="button" class="btn btn-warning"
							onclick="removeFunc()">삭제</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-primary" disabled>수정</button>
						<button type="button" class="btn btn-warning"
							onclick="removeFunc()" disabled>삭제</button>
					</c:otherwise>
				</c:choose></td>
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
			<td><fmt:formatDate value="${board.createDate }"
					pattern="yy/MM/dd HH:mm:ss" /></td>
		</tr>
	</table>
</form>

<!-- 댓글영역 -->
<div class="container reply" style="width: 80%;">
	<!-- 댓글등록. -->
	<div class="header">
		<input class="col-sm-8" type="text" name="reply" id="reply">
		<button class="col-sm-2 btn btn-outline-success btn-sm addReply">댓글등록</button>
	</div>
	<!-- 댓글목록. -->
	<div class="footer">
		<!-- datatable 사용해보기. -->
		<table id="example" class="display" style="width: 100%">
 			<thead>
				<tr>
					<th>댓글번호</th>
					<th>댓글내용</th>
					<th>작성자</th>
					<th>작성일시</th>
				</tr>
			</thead>
			<!-- <tfoot>
				<tr>
					<th>댓글번호</th>
					<th>댓글내용</th>
					<th>작성자</th>
					<th>작성일시</th>
				</tr>
			</tfoot> -->
		</table>
		<!-- datatable -->
	</div>
</div><!-- 댓글영역 -->

<script>
	const bno = "${board.boardNo }";
	const replyer = "${logid }";

	function removeFunc() {
		let form = document.querySelector('form');
		console.log(form.action);
		form.action = 'removeForm.do';
		form.submit();
		form.action = 'updateForm.do';
	}
	
	var table = $('#example').DataTable({
	    ajax: 'dataTable.do?bno=' + bno,
	    columns: [
	        { data: 'replyNo' },
	        { data: 'reply' },
	        { data: 'replyer' },
	        { data: 'replyDate' }
	    ],
	    lengthMenu: [
	    	[5, 10, 20, -1],
	    	[5, 10, 20, 'All']
	    ]
	});
	$('.addReply').on('click', function(e){
		table.row.add({'replyNo': '1', 'reply':'ㅇㅇㅇ', 'replyer':'user01', 'replyDate':'2021'}).draw(false);
	})
	
</script>
