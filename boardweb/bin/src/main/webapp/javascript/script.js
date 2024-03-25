/**
 * script.js
 */

console.log("script.js");
console.log(document); // 오브젝트 타입(키와 밸류) 이벤트 등의 여러 키들이 있다.
console.dir(document);

//document.querySelector
document.querySelector('button.btn').addEventListener('click', clickFnc);
// 'button.btn'에 접근해서 addEventListener 이벤트를 만든다. 이벤트 핸들러?
function clickFnc(event) {
	console.log(event);
	let userValue = document.querySelector('input#name').value; // input태그의 id값.
	let liTag = document.createElement('li'); // => <li></li>
	let bt = document.createElement('button');
	bt.innerText = '삭제';
	liTag.innerText = userValue + ' '; // => <li>홍길동</li>
	liTag.appendChild(bt);
	document.querySelector('#list').append(liTag); // #list의 하위요소에 liTag 붙이겠다.
	document.querySelector('#name').value = '';
}

document.querySelector('#addBtn').addEventListener('click', addRow);
// 여기서 addRow는 콜백함수이다!

function addRow() {
	let sno, sname, score;
	// 자바스크립트에서는 값이 없으면 (null) -> false다
	sno = document.querySelector('#sno').value;
	sname = document.querySelector('#sname').value;
	score = document.querySelector('#score').value;
	if(!sno || !sname || !score){
		alert('빈값을 채워주세요.');
		return; // 함수종료
	}
	let obj = {sno, sname, score};
	// {sno: sno, sname: sname, score: score} 이렇게 속성과 값이 같은이름이면 가능.
	document.querySelector('#studList').appendChild(makeRow(obj));
	// 오류 document.querySelectorAll('table:nth-of-type(1) input').value = '';
	document.querySelectorAll('table:nth-of-type(1) input')
	        .forEach(function(item, idx, ary){
		item.value = '';
	});
}

function makeRow(student={sno:1, sname:'test', score:90}) {
	let tr = document.createElement('tr');
	tr.addEventListener('click', displayRow, false); // 버블링방식으로 이벤트 전파하겠다.(디폴트라 안해도 됨)
	for (let prop in student) {
		let td = document.createElement('td');
		td.innerText = student[prop];
		tr.appendChild(td);
	}
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.addEventListener('click', deleteRow, false); // element만들면서 이벤트걸수있다. 
	// ()안한다! -> ()하면 웹브라우저가 읽어들일때 실행된다!
	btn.innerText = '삭제'; 
	td.appendChild(btn);
	tr.appendChild(td);
	return tr; // js에서는 함수에 리턴타입 알려줄필요 없다.(함수호출한곳에 tr리턴함)
}

// 삭제 이벤트.
function deleteRow(e) {
	e.stopPropagation(); // 하->상위 이벤트 전파 차단(또는 하->상위 이벤트 전파 차단)
	//console.log(e.target);
	e.target.parentElement.parentElement.remove();
}

// 상세보기 이벤트.
function displayRow(e){
	//e.stopPropagation(); 
	//console.log(e.target, this);
	// this->tr, this.children -> td*4
	// console.log(this.children[1].innerText);
	document.querySelector('#sno').value = this.children[0].innerText;
	document.querySelector('#sname').value = this.children[1].innerText;
	document.querySelector('#score').value = this.children[2].innerText;
}

document.querySelector('#editBtn').addEventListener('click', editRow);

function editRow(e) {
	let studAry = document.querySelectorAll('#studList tr')
	for(let stud of studAry){
		if(stud.children[0].innerText == document.querySelector('#sno').value){
			stud.children[1].innerText = document.querySelector('#sname').value;
			stud.children[2].innerText = document.querySelector('#score').value;
		}
	}
}

function makeTr() {
	for (let student of str) {
		// tr 생성.
		let trTag = makeRow(student);
		// tr을 tbody.appendChild
		document.querySelector('#studList').appendChild(trTag);
	}
}
// 함수실행하도록 호출.
makeTr();

// str에 값을 활요해서 화면출력.
/** 
 function makeTr() {
 	for (let student of str) {
 		// tr 생성.
 		let trTag = document.createElement('tr');
 		// td * 3 생성.
		for (let prod in student) {
			let tdTag = document.createElement('td');
			tdTag.innerText = student[prod];
			trTag.appendChild(tdTag);
		}
		// tr을 tbody.appendChild
		document.querySelector('#studList').appendChild(trTag);
	}
}
 */









