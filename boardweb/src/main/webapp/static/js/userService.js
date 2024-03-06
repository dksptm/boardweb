/**
 * userService.js
 */

document.addEventListener('DOMContentLoaded', function(e){
	console.clear();
	console.log('userService.js');
	
	// forEach() => 반환값은 없음.
	let fAry = json.filter(function(item, idx, ary){
		//return idx < 5;
		//return item.salary > 4000;
		return true; // 모든 값이 반환 return false; 아무것도 반환안함
	});
	// filter() 전체배열에서 어떠한 조건을 만족하는 것(item, idx, ary)만 추출해서 새로운 배열 생성.(매개변수로 함수)
	// 잘보면 이것도 반복문이다! 반환값이 없는 forEach와 다르게..
	// filter() => 새로운 배열 생성. A -> B
	
	// map() => 새로운 배열 생성. A -> A` (반환해주는 데이터의 구조를 바꿈)
	// + 화살표 함수도 사용
	let mAry = fAry.map((item, idx, ary) => {
		//item {id, fn, ln, email, salary, gender}
		//=> item {id, name, salary}
		let obj = {id: item.id, 
		           name: item.first_name + '-' + item.last_name, 
		           salary: item.salary}
		return obj;
	});
	// let mAry = fAry.map(item => {return {id: item.id, name: item.first_name + '-' + item.last_name, salary: item.salary}}); 
	console.log(mAry);
	
	// reduce() => 새로운 값을 생성. 매개값이 4개. acc, item, idx, ary (acc: 이전순번에서 반환해주는 값을 이번순번의 초기값으로.)
	// reduce(함수, 0) -> 초기값이 0, 초기값을 배열로 지정할수도 있다, DOM 요소도 가능하다.
	
	/**
	[1, 3, 2, 4, 5].reduce((acc, item, idx, ary) => {
		console.log(acc, item, idx);
		return item; // -> return item이라 item이 다음순번의 초기값이 된다.
	}, 0); 
	
	/**let result = [1, 3, 2, 4, 5].reduce((acc, item, idx, ary) => {
		console.log(acc, item, idx);
		acc.push(item*2); // 초기값이 배열[] 이라 push가능.
		return acc; // -> return item이라 item이 다음순번의 초기값이 된다.
	}, []);*/
	
	//json은 15명의 정보를 담은 배열.
	result = json.reduce((acc, item) => {
		if(item.gender == 'Male'){
			acc.push(item);
		}
		return acc;
	}, []); 
	
	console.log(result);
	
		
}) // end of DOMContentLoaded

// filter() 사용해서 성별리스트 다시 만들어보기
function filterList(gender = 'Male'){
	document.querySelector('tbody').innerHTML = '';
	
	// let jAry = 이렇게 변수에 안담고 바로..
	json
	  .filter(function(item, idx, ary){
		return item.gender == gender || gender == 'All';
	}).forEach(function(item){
		document.querySelector('tbody').appendChild(makeRow(item));
	})
	// 이게 가능한 이유: 필터는 반환값이 있기때문. forEach는 반환값이 없어서 forEach().~~ 안됨.
	//jAry.forEach(function ...) 해도되고
}

// reduce() 사용해서 성별리스트 다시 만들어보기
function reduceList(gender = 'Female'){
	let tbody = document.querySelector('tbody');
	tbody.innerHTML = '';
	json.reduce((acc, item) => {
		if(item.gender == gender || gender == 'All'){
			//let tr = makeRow(item);
			//acc.appendChild(tr);
			acc.appendChild(makeRow(item));
		}
		return acc;
	}, tbody)
}

