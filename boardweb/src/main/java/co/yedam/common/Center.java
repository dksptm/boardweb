package co.yedam.common;

import lombok.Data;

@Data
public class Center {
	// 필드는 모두 입력해야한다. 하나라도 없으면 매칭시 에러.
	private int id;
	private String centerType;
	private String centerName;
	private String address;
	private String createdAt;
	private String facilityName;
	private String lat;
	private String lng;
	private String org;
	private String phoneNumber;
	private String sido;
	private String sigungu;
	private String updatedAt;
	private String zipCode;

}
