package co.yedam.reply;

import java.util.Date;

import lombok.Data;

@Data
public class Reply { 
	private int replyNo; //오라클의 열이름을 그대로 카멜로 변경해야 함.
	private int boardNo;
	private String reply;
	private String replyer;
	private Date replyDate;
}
