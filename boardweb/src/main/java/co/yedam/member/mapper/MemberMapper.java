package co.yedam.member.mapper;

import java.util.List;

import co.yedam.common.SearchVO;
import co.yedam.member.Member;

// mapper 인터페이스. -> mapper xml에서 구현.
public interface MemberMapper {	
	Member selectMember(Member member);
	int insertMember(Member member);
	List<Member> memberList();
}
