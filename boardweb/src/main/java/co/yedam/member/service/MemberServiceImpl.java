package co.yedam.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.common.DataSource;
import co.yedam.member.Member;
import co.yedam.member.mapper.MemberMapper;

// 멤버서비스 구현클래스. 여기서 mapper.
public class MemberServiceImpl implements MemberService {

	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);
	// -> mybatis에 환경등록 해줘야 사용가능함.
	
	@Override
	public Member loginCheck(Member member) {
		return mapper.selectMember(member);
	}

	@Override
	public boolean addMember(Member member) {
		return mapper.insertMember(member) == 1;
	}

	@Override
	public List<Member> memberList() {
		return mapper.memberList();
	}

}
