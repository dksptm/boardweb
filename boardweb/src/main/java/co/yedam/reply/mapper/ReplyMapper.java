package co.yedam.reply.mapper;

import java.util.List;
import java.util.Map;

import co.yedam.common.Center;
import co.yedam.common.SearchVO;
import co.yedam.reply.Reply;

public interface ReplyMapper {
	// 댓글 목록, 등록, 삭제
	
	// 파라미터를 여러개넘겨줄때  
	// List<Reply> selectList(@Param("bno"), @Param("page"), ...); 
	// 대신 아예 클래스로!(SearchVO)
	List<Reply> selectList(SearchVO search); 
	int insertReply(Reply reply);
	int deleteReply(int rno);
	// 페이지계산위한 전체건수.
	int selectCount(int bno);
	
	// api활용계속.
	int insertCenter(Center[] array);
	int deleteCenter(Center[] array);
	
	// chart.
	List<Map<String, Object>> countPerSido();
}
