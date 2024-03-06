package co.yedam.member.service;

import java.util.List;

import co.yedam.common.SearchVO;
import co.yedam.member.Member;

// 멤버서비스 인터페이스. -> MemberServiceImpl에서 구현.
public interface MemberService {
	Member loginCheck(Member member);
	boolean addMember(Member member);
	List<Member> memberList();
}
