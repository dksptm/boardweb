/**
 * userList.js
 */

console.log('userList.js');

let str = `[{"id":1,"first_name":"Bowie","last_name":"Commin","email":"bcommin0@google.es","gender":"Male","salary":4630},
{"id":2,"first_name":"Huntington","last_name":"Champneys","email":"hchampneys1@etsy.com","gender":"Male","salary":4172},
{"id":3,"first_name":"Darbee","last_name":"Voff","email":"dvoff2@google.com.br","gender":"Male","salary":3590},
{"id":4,"first_name":"Brad","last_name":"Lidyard","email":"blidyard3@ftc.gov","gender":"Male","salary":3685},
{"id":5,"first_name":"Candace","last_name":"Burgoyne","email":"cburgoyne4@blogspot.com","gender":"Female","salary":2052},
{"id":6,"first_name":"Lacey","last_name":"Rolley","email":"lrolley5@comcast.net","gender":"Female","salary":4784},
{"id":7,"first_name":"Becka","last_name":"Johnikin","email":"bjohnikin6@flickr.com","gender":"Female","salary":4964},
{"id":8,"first_name":"Ellette","last_name":"Dotterill","email":"edotterill7@slate.com","gender":"Female","salary":4902},
{"id":9,"first_name":"Piggy","last_name":"Higbin","email":"phigbin8@latimes.com","gender":"Male","salary":4241},
{"id":10,"first_name":"Vladamir","last_name":"Cicchillo","email":"vcicchillo9@storify.com","gender":"Male","salary":4999},
{"id":11,"first_name":"Gard","last_name":"Piscot","email":"gpiscota@slashdot.org","gender":"Male","salary":4248},
{"id":12,"first_name":"Phylys","last_name":"Thomazet","email":"pthomazetb@umn.edu","gender":"Female","salary":4257},
{"id":13,"first_name":"Kirsten","last_name":"Trappe","email":"ktrappec@ebay.com","gender":"Female","salary":2747},
{"id":14,"first_name":"Julietta","last_name":"Hassewell","email":"jhassewelld@nyu.edu","gender":"Female","salary":2232},
{"id":15,"first_name":"Bailie","last_name":"Winterton","email":"bwintertone@joomla.org","gender":"Male","salary":3792}]`;
// 지금 str은 그냥 문자열이다. -> 값을 읽어오는게 거의 불가능.
// js의 객체타입으로 바꿔주는 함수필요. -> JSON.parse(str);

let json = JSON.parse(str); // 문자열 -> object로 변환.
console.log(json);

document.addEventListener('DOMContentLoaded', function(e) {
	document.querySelector('#name').value = '홍길동';
	// thead 생성.
	let title = json[0];
	let tr = document.createElement('tr');
	for (let prop in title) {
		// console.log(title); 
		let th = document.createElement('th');
		th.innerText = prop;
		tr.appendChild(th);
	}
	document.querySelector('#tableList thead').appendChild(tr);
	// tbody 영역.
	// 배열에서 많이 사용하는 메소드 -> forEach(). (배열에서만 사용할수 있다)
	//json.forEach(function(item, idx, ary){
	//	console.log(item, idx, ary)
	
	/**
	function fullList() {
		json.forEach(function(item, idx) {
			let tr = document.createElement('tr');
			for (let prop in item) {
				let td = document.createElement('td');
				td.innerText = item[prop];
				tr.appendChild(td);
			}
			document.querySelector('#tableList tbody').appendChild(tr);
		});
	}
	fullList();
	 */

	// tbody영역
	json.forEach(function(item, idx){
		if(idx < 10){
			let tr = makeRow(item);
			document.querySelector('#tableList tbody').appendChild(tr);
		}
	});
	

	//document.querySelector('#genderList').addEventListener('change', genderList);
	document.querySelector('#genderList').addEventListener('change', function(e){
		//showList(this.value); //this.value: change되는 값
		//filterList(this.value);
		reduceList(this.value);
	});

	/**
	function showList() {
		document.querySelector('#tableList tbody').innerHTML = '';
		let gen = document.querySelector('#genderList').value;
		if(gen == 'every'){
			fullList();
			return;
		}
		//console.log(gen);
		json.forEach(function(item, idx){
			let tr = document.createElement('tr');
			for (let prop in item) {
				//console.log(item['gender']);
				if(item.gender == gen){
					let td = document.createElement('td');
					td.innerText = item[prop];
					tr.appendChild(td);
				}
			}
			document.querySelector('#tableList tbody').appendChild(tr);
		});		
	}
	 */


}); // end of DOMContentLoaded

function makeRow(obj = {}){ // 만약 위에서 item이 안넘어오면 초기값을 obj = {}
	let tr = document.createElement('tr');
	for(let prop in obj){
		let td = document.createElement('td');
		td.innerText = obj[prop];
		tr.appendChild(td);
	}
	return tr;
}
	
function showList(gender = 'Male'){ // 초기값을 주어서 undifined가 값으로 안오도록하기위함
// js에서는 메소드에 매개변수 선언했는데, 매개변수값이 안넘어오더라도, 오류가 아님
	document.querySelector('#tableList tbody').innerHTML = '';
	json.forEach(function(item){
		if(item.gender == gender || gender == 'All'){
			document.querySelector('tbody').appendChild(makeRow(item))
		}
	})
}




