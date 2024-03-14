/**
 * userAjax.js
 */
console.clear();
console.log('userAjax.js');

// MVC M:모델(?) V:jsp C:프론트컨트롤러.

document.addEventListener('DOMContentLoaded', function(e) {
	// Asynchronous JAvaScript and XML : AJAX
	
	// 등록이벤트
	document.getElementById('addBtn').addEventListener('click', function(e){
		// 서블릿호출, 화면제어.
		
		// 파라미터로 전달할 값을 가져와서 변수에 담기.
		let bookCode = document.getElementById('bcode').value; // 삭제와 다르게해줘야함. 아니면 추가하고 바로삭제 안됨. 
		let bname = document.getElementById('bname').value;
		let bauthor = document.getElementById('bauthor').value;
		let bpress = document.getElementById('bpress').value;
		let bprice = document.getElementById('bprice').value;
		let obj = {bookCode, bname, bauthor, bpress, bprice}
		
		const addAjax = new XMLHttpRequest();
		/*
		addAjax.open('get', 'addBook.do?bcode='+bcode+'&bname='+bname
						+'&bauthor='+bauthor+'&bpress='+bpress+'&bprice='+bprice); // get방식, ?뒤에 파라미터
		*/
		addAjax.open('post', 'addBook.do'); // post방식, 파라미터는 send로
		addAjax.setRequestHeader('Content-type','application/x-www-form-urlencoded'); //send로 넘길때는 컨텐트 방식을 지정해줘야함
		addAjax.send('bcode=' + bookCode + '&bname=' + bname
						+ '&bauthor=' + bauthor + '&bpress=' + bpress + '&bprice=' + bprice);
		addAjax.onload = function(e){
			//console.log(addAjax)
			let result = JSON.parse(addAjax.responseText);
			if (result.retCode == 'OK'){
				document.querySelector('#show tbody').appendChild(makeRow(obj));
			} else if (result.retCode == 'NG') {
				alert('처리중 에러!');
			}
		}
	})// 등록이벤트.

	let json = "";
	// 서버상의 페이지를 읽어올수있는 객체, 비동기처리해주는 객체.. XMLHttpRequest, 하나 새로만들기 new~()
	const xhtp = new XMLHttpRequest();
	//xhtp.open('get', 'static/data/MOCK_DATA.json'); // 읽어들일 위치.
	xhtp.open('get', 'bookList.do');
	xhtp.send(); // 페이지요청.
	// 받아온 데이터를 언제가져오냐? 이벤트발생하면.
	// => onload : load 이벤트가 on 발생하면. = 뒤의 함수실행
	xhtp.onload = function(e) {
		// console.log(xhtp); // response or responseText 찾아보기.
		// console.log(xhtp.responseText); // 문자열
		json = JSON.parse(xhtp.responseText); // 문자열 -> 객체배열로 변환.
		console.log('onload에서 ', json);

		// 타이틀.
		let title = ['도서코드', '도서명', '저자명', '출판사', '가격'];
		let tr = document.createElement('tr');
		for (let prop of title) {
			let th = document.createElement('th');
			th.innerText = prop;
			tr.appendChild(th);
		}
		let th = document.createElement('th');
		th.innerText = '삭제';
		tr.appendChild(th);
		document.querySelector('#tableList thead').appendChild(tr);

		// 데이터.
		json.reduce((acc, item) => {
			acc.appendChild(makeRow(item));
			return acc;
		}, document.querySelector('#show tbody'));
	}
	console.log('out ', json); // 먼저실행된다. 왜?
	// open과 onload사이에 갭이 있는데, onload 이벤트실행되는동안 
	// 이벤트 끝날때까지 기다리지않고 그다음 코드 진행됨
	// => 이 방식을 비동기 방식이라고 한다.	

}); // end of DOMContentLoaded 

// tr 생성.
function makeRow(obj = {}) { // 만약 위에서 item이 안넘어오면 초기값을 obj = {}
	let tr = document.createElement('tr');
	tr.setAttribute('id', 'book_' + obj.bookCode); // 이렇게 primary key를 담아놓으면 두고두고 편하다.
	tr.setAttribute('data-code', obj.bookCode); // 이렇게 primary key를 담아놓으면 두고두고 편하다. data-code는 꼭 중복안되야하는건 아님.
	for (let prop in obj) {
		let td = document.createElement('td');
		td.innerText = obj[prop];
		tr.appendChild(td);
	}
	// 삭제버튼 추가.
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.addEventListener('click', deleteRow, false); // 바로이벤트걸기.
	btn.innerText = '삭제'; 
	td.appendChild(btn);
	tr.appendChild(td);
	btn.setAttribute('class', 'btn btn-outline-danger btn-sm');
	
	return tr;
}

// 삭제함수.
function deleteRow(e){
	let tr = this.parentElement.parentElement;
	//console.log(tr.getAttribute('id'), tr.getAttribute('data-code'));
	//console.log(tr.dataset.code); // data-ooo 이런 속성들은 이렇게도 가져올수있다.
	let bcode = tr.dataset.code;
	
	const removeAjax = new XMLHttpRequest();
	removeAjax.open('post', 'removeBook.do');
	removeAjax.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	removeAjax.send('bcode=' + bcode);
	removeAjax.onload = function(e){
		let result = JSON.parse(removeAjax.responseText);
		if (result.retCode == 'OK'){
			alert('삭제성공!');
			// console.log('결과 ', tr);
			tr.remove();
		} else if (result.retCode == 'NG') {
			alert('처리중 에러');
		} else if (result.retCode == 'ERROR') {
			alert('알수없는 에러');
		}
	}	
}


