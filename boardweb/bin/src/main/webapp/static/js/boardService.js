/**
 * boardService.js 를 fetch로!
 */

let page = 1;

// pagination.
function pagingFunc(){
	document.querySelectorAll('.pagination>a') // NodeList로 반환.
		    .forEach(item => { 
				item.addEventListener('click', function(e){
					e.preventDefault(); 
					page = item.dataset.pno; 
					replyList(page);
					pageList(); 
				})
			});	
}

// 등록이벤트.(fetch로는 안됨. async/await 필요)
document.querySelector('.addReply').addEventListener('click', addReplyFnc);
async function addReplyFnc(e) {
	let reply = document.querySelector('input[name="reply"]').value;
	if(!reply) {
		alert('댓글을 입력하세요');
		return;
	}
	if(!replyer){
		alert('로그인 후 댓글등록 가능합니다.');
		return;
	}
	// ajax 호출.
	const option = {
		method: 'post',
		headers: {
			'Content-type': 'application/x-www-form-urlencoded'
		},
		body: 'bno=' + bno + '&reply=' + reply + '&replyer=' + replyer
	};
	try {
		let resolve = await fetch('addReply.do', option);
		let result = await resolve.json();
		if (result.retCode == 'OK'){
			alert("댓글등록 성공.");
			document.querySelector('#reply').value = '';
			resolve = await fetch('getTotal.do?bno=' + bno);
			result = await resolve.json();
			page = Math.ceil(result.totalCount / 5);
			replyList(page);
			pageList();
		} else {
			alert("댓글등록 실패!");
		}
	} catch(err) {
		console.log(err);
	} // end of ajax호출.	
}

/** async/await 없이 그냥 fetch중복으로 만든경우.
function addReplyFnc(e) {
	let reply = document.querySelector('input[name="reply"]').value;
	if(!reply) {
		alert('댓글을 입력하세요');
		return;
	}
	if(!replyer){
		alert('로그인 후 댓글등록 가능합니다.');
		return;
	}
	// ajax 호출.
	fetch('addReply.do', {
		method: 'post',
		headers: {
			'Content-type': 'application/x-www-form-urlencoded'
		},
		body: 'bno=' + bno + '&reply=' + reply + '&replyer=' + replyer
	})
	.then(resolve => resolve.json())
	.then(result => {
		if(result.retCode == 'OK') {
			alert("댓글등록 성공.");
			document.querySelector('#reply').value = '';
			// ajax 추가호출.
			fetch('getTotal.do?bno=' + bno)
			.then(resolve => resolve.json())
			.then(result => {
				page = Math.ceil(result.totalCount / 5);
				replyList(page);
				pageList();
			})
			.catch(err => console.log(err))
		} else if (result.retCode == 'NG') {
			alert("댓글등록 실패!");
		}
	})
	.catch(err => console.log(err))
}
**/

// li 만들기(클론노드)
function makeRow(obj = {}) {
	let clon = document.querySelector('.content>ul>li:nth-of-type(1)').cloneNode(true);
	// 댓글 데이터담기.
	clon.setAttribute('data-rno', obj.replyNo);
	clon.querySelector('span:nth-of-type(1)').innerText = obj.replyNo;
	clon.querySelector('span:nth-of-type(2)').innerText = obj.reply;
	clon.querySelector('span:nth-of-type(3)').innerText = obj.replyer;
	// 삭제버튼.
	let btn = document.createElement('button');
	btn.addEventListener('click', deleteRow);
	btn.className = 'btn btn-outline-danger btn-sm';
	if(obj.replyer == replyer){
		btn.disabled = false;
	} else {
		btn.disabled = true;
	}
	btn.innerText = '삭제';
	clon.querySelector('span:nth-of-type(4)').innerText = '';
	clon.querySelector('span:nth-of-type(4)').appendChild(btn);
	return clon;
}

// 댓글1개 삭제.
function deleteRow(e) {
	let rno = this.parentElement.parentElement.dataset.rno;
	/*
	const success = function(result) {
		if (result.retCode == 'OK') {
			alert(result.retMsg);
			replyList(page);
			pageList();
		} else {
			alert(result.retMsg);
		}
	}
	*/
	fetch('removeReply.do', {
		method: 'post',
		headers: {
			'Content-type':'application/x-www-form-urlencoded'
		},
		body: 'rno=' + rno
	})
	.then(resolve => resolve.json())
	.then(result => {
		if (result.retCode == 'OK') {
			alert(result.retMsg);
			replyList(page);
			pageList();
		} else {
			alert(result.retMsg);
		}
	})
	.catch(err => console.log(err));	
}

// 댓글 목록.
function replyList(rpage = 1) {
	fetch('replyList.do?bno=' + bno + '&page=' + rpage)
	.then(resolve => resolve.json())
	.then(result => {
		document.querySelectorAll('li[data-rno]').forEach(item => item.remove());
		result.forEach(item => {
			document.querySelector('.reply ul').appendChild(makeRow(item));
		})
		if(!result.length && page > 1) {
			page--;
			replyList(page);
			pageList();
		}
	})
	.catch(err => console.log(err));
}
replyList();

// 댓글 페이징.
function pageList(){
	// ajax 호출.
	fetch('getTotal.do?bno=' + bno)
	.then(resolve => resolve.json())
	.then(createPageElement)
	.catch(err => console.log(err));
	// 함수안의 함수선언.
	function createPageElement(result) {
				document.querySelector('div.pagination').innerHTML = '';
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
			let aTag = document.createElement('a');
			aTag.innerHTML = '&laquo;'
			aTag.setAttribute("data-pno", startPage - 1);
			aTag.href = "#";
			document.querySelector('div.pagination').appendChild(aTag);
		}
		for (let p = startPage; p <= endPage; p++) {
			let aTag = document.createElement('a');
			aTag.innerText = p;
			aTag.setAttribute("data-pno", p);
			aTag.href = "#";
		    if (p == page) {
				aTag.className = 'active';
			}
			document.querySelector('div.pagination').appendChild(aTag);
		}
		if (next) {
			let aTag = document.createElement('a');
			aTag.innerHTML = '&raquo;'
			aTag.setAttribute("data-pno", endPage + 1);
			aTag.href = "#";
			document.querySelector('div.pagination').appendChild(aTag);
		}
		pagingFunc(); // 각각의 페이지에 이벤트 적용	
	}
}
pageList();


