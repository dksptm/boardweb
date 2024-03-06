package co.yedam.board;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	// 롬복, jdcb등의 라이브러리를 WEB-INF의 lib안에 넣어주면 됨(카피)
	
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private int viewCnt;
	private Date createDate;

}
