package co.yedam.common;

import lombok.Data;

// 검색조건의 파라미터를 담기위한 클래스. -> 이렇게하면 파라미터 넣기 편하다.
// value object

@Data
public class SearchVO {
	// 게시글관련.
	private int page;
	private String searchCondition;
	private String keyword;
	
	// 댓글관련.
	private int bno;
	private int rpage;
}
