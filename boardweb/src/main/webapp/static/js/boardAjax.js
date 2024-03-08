/**
 * Ajax 함수.
 */

// 서비스 객체 선언.
const service = {
	// 댓글목록(5개씩 보여주는) 메소드(객체안의 함수).
	// param={}은 bno와 page.
	// successCall, errorCall 은 boardService3에서 구현할 함수(함수이름이라는거 아님).
	replyList(param={bno:1, page:1}, successCall, errorCall) {
		$.ajax({
			url: 'replyList.do', // 앞에 / 하면 절대경로! 주의할것.
			method: 'get',
			data: param, 
			dataType: 'json'
		})
		.done(successCall)
		.fail(errorCall)
	}, // 쉼표!
	
	// 댓글페이징 메소드.
	pageList(bno = 1, successCall, errorCall) {
		$.ajax({
			url: 'getTotal.do?bno=' + bno, // 앞에 / 하면 절대경로! 주의할것.
			method: 'get',
			dataType: 'json'
		})
		.done(successCall) // 앞의 성공결과를 ()안의 함수의 매개값으로 넘겨준다.
		.fail(errorCall) // 
	},
	
	// 댓글삭제 메소드.
	removeReply(rno = 1, successCall, errorCall) {
		$.ajax({
			url: 'removeReply.do',
			method: 'post',
			data: { rno },
			dataType: 'json'
		})
		.done(successCall)
		.fail(errorCall)
	},
	
	// 댓글등록 메소드.
	addReply(param={bno:1, replyer: null, reply: null}, successCall, errorCall){
		$.ajax({
			url: 'addReply.do',
			method: 'post',
			data: param,
			dataType: 'json'
		})
		.done(successCall)
		.fail(errorCall)
	}	
}// end of service 객체.

// 이 서비스객체를 export => 다른곳에서 import해서 사용가능.
export default service;

/*export {service};
import {service} from ~~*/
