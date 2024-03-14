/**
 * boardService.js
 */

// 댓글 목록.

// .pagination>a click이벤트.
let page = 1;//page를 전역변수로.

function pagingFunc(){
	document.querySelectorAll('.pagination>a') // NodeList로 반환.
		    .forEach(item => { 
				//console.log(item); // item: a태그
				item.addEventListener('click', function(e){
					e.preventDefault(); // 링크의 기능 차단.(a태그는 무조건 링크라서)
					page = item.dataset.pno; // page로 사용. <a>1</a> <a>2</a>.. // 전역변수 page.
					/*
					const rlistHtp = new XMLHttpRequest();
					rlistHtp.open('get', 'replyList.do?bno=' + bno + '&page=' + page);
					rlistHtp.send();
					rlistHtp.onload = function (e) {
						let result = JSON.parse(rlistHtp.responseText);
						console.log(result);
					}
					*/ // 대신 replyList(page)
					replyList(page);
					pageList(); // active적용..
				})
			});	
}

// 등록이벤트.
document.querySelector('.addReply').addEventListener('click', addReplyFnc);
function addReplyFnc(e) {
	let reply = document.querySelector('input[name="reply"]').value; //name 속성이 reply
	//let replyer = "${logid}"; // loginControl의 setAttribute("logid", id).
	//console.log(bno, reply, replyer);
	if(!reply) { //댓글내용이 없을때.(없다,공백->js에서는 false)
		alert('댓글을 입력하세요');
		return;
	}
	if(!replyer){
		alert('로그인 후 댓글등록 가능합니다.');
		return;
	}
	const addHtp = new XMLHttpRequest();
	addHtp.open('post', 'addReply.do');
	addHtp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	addHtp.send('bno=' + bno + '&reply=' + reply + '&replyer=' + replyer);
	addHtp.onload = function(e) {
		let result = JSON.parse(addHtp.responseText);
		if(result.retCode == 'OK') {
			alert("댓글등록 성공.");
			//document.querySelector('.reply ul').appendChild(makeRow2(result.retVal)); // 이제 아래에서 페이지재정렬하기때문에 필요없음.
			document.querySelector('#reply').value = '';
			//page = document.querySelector('.pagination>a.active').dataset.pno; // 필요없음!!실수.
			//page = 1; //어느페이지에서 등록했든 1페이지로 이동
			const cntHtp = new XMLHttpRequest();
			cntHtp.open('get', 'getTotal.do?bno=' + bno);
			cntHtp.send();
			cntHtp.onload = function(e) {
				let totalResult = JSON.parse(cntHtp.responseText);
				page = Math.ceil(totalResult.totalCount / 5);
				replyList(page);
				pageList();
			}
		} else if(result.retCode == 'NG') {
			alert("댓글등록 실패(NG)");
		} else {
			alert("댓글등록 실패(ERROR)");
		}
	}
}

// li 만들기.
function makeRow(obj = {}) {
	let fields = ['replyNo', 'reply', 'replyer']; // forEach의 obj[prop] 위해.
	let liTag = document.createElement('li');
	liTag.setAttribute('data-rno', obj.replyNo);
	fields.forEach(prop => {
		let span = document.createElement('span');
		span.innerText = obj[prop];
		if (prop == 'reply') {
			span.className = 'col-sm-5'; //setAttribute에서 클래스 지정과 같음.
		} else {
			span.className = 'col-sm-2';
		}
		liTag.appendChild(span);
	});
	let span = document.createElement('span');
	span.className = 'col-sm-2';
	let btn = document.createElement('button');
	btn.addEventListener('click', deleteRow);
	btn.className = 'btn btn-outline-danger btn-sm';
	btn.innerText = '삭제';
	span.appendChild(btn);
	liTag.appendChild(span);
	return liTag;
}
// li 만들기 더 쉽게.(클론노드)
function makeRow2(obj = {}) {
	let clon = document.querySelector('.content>ul>li:nth-of-type(1)').cloneNode(true); //true: 하위 span들까지 가져옴
	//console.log(clon);
	// 댓글 데이터담기.
	clon.setAttribute('data-rno', obj.replyNo);
	clon.querySelector('span:nth-of-type(1)').innerText = obj.replyNo;
	clon.querySelector('span:nth-of-type(2)').innerText = obj.reply;
	clon.querySelector('span:nth-of-type(3)').innerText = obj.replyer;
	// 삭제버튼.
	let btn = document.createElement('button');
	btn.addEventListener('click', deleteRow);
	btn.className = 'btn btn-outline-danger btn-sm';
	/*
	if(obj.replyer == replyer){
		btn.disabled = false;
	} else {
		btn.disabled = true;
	}
	*/
	btn.innerText = '삭제';
	clon.querySelector('span:nth-of-type(4)').innerText = '';
	clon.querySelector('span:nth-of-type(4)').appendChild(btn);
	return clon;
}

// 댓글1개 삭제.
function deleteRow(e) {
	let rno = this.parentElement.parentElement.dataset.rno; // btn의 부모 sapn의 부모 li의 data-... 의 rno속성.
	//console.log(rno);
	let li = this.parentElement.parentElement;
	//console.dir(this.parentElement); previousElementSibling부분 확인할것.
	//li.querySelector('span:nth-of-type(3)').innerText; // 이것도 가능하다.
	
	// 작성자와 로그인비교. 
	let writer = this.parentElement.previousElementSibling.innerText;
	if(replyer != writer){
		alert('삭제권한이 없습니다.');
		return;
	}
	
	// 삭제권한있으면 아작스 진행.
	const delHtp = new XMLHttpRequest();
	delHtp.open('post', 'removeReply.do');
	delHtp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	delHtp.send('rno=' + rno);
	delHtp.onload = function(e) { // 처리된 결과를 받아온 이후 처리할 코드를 onload에.
		//console.log(delHtp);
		// 여기에서 this는 delHtp. 만약 위의 this를 사용하고 싶다면 화살표 함수. 아니면 li 변수만들기.
		const result = JSON.parse(delHtp.responseText);
		if (result.retCode == 'OK') {
			alert(result.retMsg);
			//li.remove(); 삭제도 굳이 필요없음
			//page = document.querySelector('.pagination>a.active').dataset.pno; 필요없음!
			replyList(page); // 링크를 클릭할때마다 목록을 새롭게.
			pageList(); // 페이지목록을 새롭게.
		} else {
			alert(result.retMsg);
		}
	}
}

// 목록함수.
function replyList(rpage = 1) {
	const xhtp = new XMLHttpRequest();
	xhtp.open('get','replyList.do?bno=' + bno + '&page=' + rpage);
	xhtp.send();
	xhtp.onload = function(e) {
		//console.log(xhtp.responseText); // replyList.do?bno=bno 의 결과 텍스트.
		const data = JSON.parse(xhtp.responseText);
		//console.log(data); //JSON 객체 배열로.
		
		// 기존5개 삭제.
		document.querySelectorAll('li[data-rno]').forEach(item => item.remove());
		
		// 목록.
		data.forEach(item => {
			document.querySelector('.reply ul').appendChild(makeRow2(item));
		});
		
		// 5개를 모두 삭제했을때(페이지에 하나도 없을때)
		if(!data.length){
			page--;
			replyList(page);
			pageList();
		}
	}
}
replyList();

// 댓글 페이징 목록.
function pageList(){
	const plistHtp = new XMLHttpRequest();
	plistHtp.open('get', 'getTotal.do?bno=' + bno);
	plistHtp.send();
	plistHtp.onload = function(e) {
		// 기존에 있던 페이지 초기화
		document.querySelector('div.pagination').innerHTML = '';
		let result = JSON.parse(plistHtp.responseText);
		let totalCnt = result.totalCount;
		let startPage, endPage; // 1~5, 6~10
		let next, prev;
		let realEnd = Math.ceil(totalCnt / 5);
		endPage = Math.ceil(page/5) * 5; // 전역변수 page.
		startPage = endPage - 4;
		
		endPage = endPage > realEnd ? realEnd : endPage;
		next = endPage < realEnd ? true : false;
		prev = startPage > 1;
		
		if (prev) {
			let aTag = document.createElement('a');
			//aTag.innerText = startPage - 1;
			aTag.innerHTML = '&laquo;'
			aTag.setAttribute("data-pno", startPage - 1);
			aTag.href = "#";
			document.querySelector('div.pagination').appendChild(aTag);
		}
		for (let p = startPage; p <= endPage; p++) {
			let aTag = document.createElement('a');
			aTag.innerText = p;
			aTag.setAttribute("data-pno", p);
			aTag.href = "#"; // 그냥 href 속성만 넣어주기.
		    if (p == page) {
				aTag.className = 'active';
			}
			document.querySelector('div.pagination').appendChild(aTag);
		}
		if (next) {
			let aTag = document.createElement('a');
			//aTag.innerText = endPage + 1;
			aTag.innerHTML = '&raquo;'
			aTag.setAttribute("data-pno", endPage + 1);
			aTag.href = "#";
			document.querySelector('div.pagination').appendChild(aTag);
		}
		pagingFunc(); // 각각의 페이지에 이벤트 적용
	}
}
pageList();
