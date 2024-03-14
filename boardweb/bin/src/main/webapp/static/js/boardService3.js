/**
 * boardAjax 호출하고 기능구현.
 */

// ajax 호출위한 boardAjax.js의 service import.
import service from "./boardAjax";

// 시작.
let page = 1;

// 댓글목록 5건 출력.
service.replyList({ bno: bno, page: page }, replyListCall, errMsg)

// replyList의 successCall.
function replyListCall(result) {
	// 삭제하다가 더이상 없을때(이렇게 앞에 만들어야함 page가 --되어야함).
	if(!result.length && page > 1){
		service.replyList({ bno: bno, page: --page }, replyListCall, errMsg);
	}
	// 댓글리스트 만들기.
	let ul = $('.content>ul');
	$('.content>ul>li:gt(1)').remove();
	$(result).each(function(idx, item) {
		let clon = $('.content>ul>li').eq(0).clone();
		let delBtn = $('<button class="btn btn-outline-danger btn-sm">삭제</button>');
		// delBtn에 click이벤트.
		delBtn.click(function(e) {
			service.removeReply(item.replyNo, 
				result => {
					//console.log(result)
					if(result.retCode == 'OK') {
						alert(result.retMsg);
						service.replyList({ bno: bno, page: page }, replyListCall, errMsg);
					} else {
						alert(result.retMsg);
					}
				}, 
				errMsg);
		});
		clon.find('span:eq(0)').text(item.replyNo);
		clon.find('span:eq(1)').text(item.reply);
		clon.find('span:eq(2)').text(item.replyer);
		clon.find('span:eq(3)').html(delBtn);
		ul.append(clon);
		// btn disabled
		if(replyer != item.replyer){
			delBtn.attr('disabled', true);	
		}
	});
	// 댓글목록 만들면서 댓글 페이지목록도 같이 출력. => 이렇게하면 다른곳에서 호출안해도 된다.
	// service.replyList 안의 두번째함수안의 service.pageList
	service.pageList(bno, createPageElement, errMsg);
}

// 페이지네이션 생성.
function createPageElement(result) {
	let pagination = $('div.pagination');
	pagination.html('');

	let totalCnt = result.totalCount;
	let startPage, endPage;
	let next, prev;
	let realEnd = Math.ceil(totalCnt / 5);
	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4;
	endPage = endPage > realEnd ? realEnd : endPage;
	next = endPage < realEnd ? true : false;
	prev = startPage > 1;

	if (prev) {
		$('<a />').attr('href', '#').attr('data-pno', startPage - 1).html('&laquo;').appendTo(pagination);
	}
	for (let p = startPage; p <= endPage; p++) {
		let aTag = $('<a />').attr('href', '#').attr('data-pno', p).text(p).appendTo(pagination);
		if (p == page) aTag.addClass('active');
	}
	if (next) {
		$('<a />').attr('href', '#').attr('data-pno', endPage + 1).html('&raquo;').appendTo(pagination);
	}
}

// 페이지이동(클릭한 페이지).
// 제이쿼리에서는 이벤트위임이 가능하다 -> pagination에 이벤트 걸어놓고 그안에 a태그들에게..
$('.pagination').on('click', 'a', function(e) {
	page = $(this).data('pno'); //data(): js의 dataset.
	// 댓글목록5건 다시출력(이안에 페이지목록도 같이있음)
	service.replyList({ bno: bno, page: page }, replyListCall, errMsg);
})

// 댓글 등록
$('.addReply').on('click', function(e){
	let reply = $('#reply').val();
	// 댓글등록 조건체크.
	if(!reply){
		alert('댓글을 입력하세요');
		return;
	}
	if(!replyer){
		alert('로그인 해주세요.');
		return;
	}
	// 댓글등록 ajax호출.
	service.addReply({ bno, replyer, reply }, 
		result => {
			if(result.retCode == 'OK') {
				alert('댓글등록 성공');
				service.pageList(bno, 
					newCnt => {
						page = Math.ceil(newCnt.totalCount / 5);
						service.replyList({ bno: bno, page: page }, replyListCall, errMsg);
					}, 
					errMsg);
				$('#reply').val('');
			} else {
				alert('댓글등록 실패');
				$('#reply').val('');
			}
		}, 
		errMsg)
})

function errMsg(err){
	console.error('error => ', err);
}

/*// 댓글 페이지목록 출력.
service.pageList(bno,
	function(result) {
		console.log('pageList 결과 => ', result);
		createPageElement;
	},
	err => console.log('error => ', err)
)// end of pageList.*/


