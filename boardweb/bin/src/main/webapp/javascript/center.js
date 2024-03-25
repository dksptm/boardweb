/**
 * center.js
 */
console.log('center.js')

let url = 'https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=mE1CN7xzr3aXt4%2BDH2dN8SIwZlnXMkO3hHulmWVclpgFGD9DrLJvJIssH1o99H0zFgMgRBdEp86aqj%2F38zqfJw%3D%3D';

let showFields = ['id', 'centerName', 'phoneNumber', 'sido'];
let tbody = document.querySelector('#list');
let centerList = [];
let searchSido = document.querySelector('#searchSido');
let optionSido = [];

// 전체목록 + select태그의 option채우기.
fetch(url)
	.then(resolve => resolve.json())
  	.then(result => {
		  centerList = result.data;
		  //console.log(centerList);
		  //console.log(JSON.stringify(centerList)); //객체->문자열
		  centerList.forEach(center => { 
			  tbody.appendChild(makeTr(center));
			  if (optionSido.indexOf(center.sido) == -1){
				  optionSido.push(center.sido);				  
			  }			  
		  })
		  optionSido.forEach(item => { 
			  let opt = document.createElement('option');
			  opt.innerText = item;
			  searchSido.append(opt);			  
		  })		     
	  })
	  .catch(err => console.log(err));

// 시도별 체인지이벤트 처리.  
document.querySelector('#searchSido').addEventListener('change', function(e) {
	//let searchSido = document.getElementById('searchSido').value;
	let searchSido = this.value;
	tbody.innerHTML = '';
	/**
	centerList.forEach(center => {
		if (center.sido == searchSido || searchSido == 'all') {
			let tr = makeTr(center);
			tbody.appendChild(tr);
		}
	})
	**/
	centerList.filter(center => center.sido == searchSido || searchSido == 'all')
			  .forEach(center => tbody.appendChild(makeTr(center)));
});

// 조회이벤트 처리.
document.querySelector('#searchBtn').addEventListener('click', searchBtnFunc)
document.querySelector('#keyword').addEventListener('change', searchBtnFunc)
function searchBtnFunc(e) {
	//console.log(this); //버튼태그.
	let keyword = document.querySelector('#keyword').value;
	tbody.innerHTML = '';
	searchSido.options[0].selected = 'ture';
	/**
	centerList.forEach(center => {
		if(center.sido.indexOf(keyword) != -1) {
			let tr = makeTr(center);
			tbody.appendChild(tr);
		}
	})
	**/
	// 필터메소드 사용.
	centerList.filter(center => center.sido.includes(keyword))
			  .forEach(center => tbody.append(makeTr(center)));
	document.querySelector('#keyword').value = '';
}

//

// tr만들기.
function makeTr(center = {}) {
	let tr = document.createElement('tr');
	// 이벤트넣기.
	tr.addEventListener('click', function(e){
		//location.href = 'map.jsp?lat=' + center.lat + '&lng=' + center.lng;
		window.open('map.jsp?lat=' + center.lat + '&lng=' + center.lng + '&name=' + center.centerName);
	})
	
	showFields.forEach(field => {
		let td = document.createElement('td');
		if (field == 'centerName') {
		td.innerText = center[field].substring('코로나19'.length);
		} else {
		td.innerText = center[field];				
		}
		tr.appendChild(td);
	})
	return tr;
}

// JSON타입으로 데이터를 전송하고, DB에 입력하는 기능.
document.getElementById('registerData').addEventListener('click', function(e) {
	fetch('../registerCenter.do', {
		method: 'post',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(centerList)
	})
	.then(resolve => resolve.text())
	.then(result => {
		if(result > 0){
			alert(result + '건 테이블 등록');
		} else {
			alert('등록실패');
		}
	})
	.catch(err => console.log(err))
})



