package co.yedam.member;

import lombok.Data;

// Member 정보 클래스.
@Data
public class Member {
	private String id;
	private String pw;
	private String name;
	private String auth;
	private String image;
}
