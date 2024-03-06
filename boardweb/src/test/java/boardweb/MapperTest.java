package boardweb;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import co.yedam.common.DataSource;
import co.yedam.reply.mapper.ReplyMapper;

public class MapperTest { 
	// mapper가 제대로 되는지 테스트.
	public static void main(String[] args) {
//		SqlSession session = DataSource.getInstance().openSession(true);
//		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		
//		Reply rep = new Reply();
//		rep.setBoardNo(218);
//		rep.setReply("218번 댓글입니다..");
//		rep.setReplyer("newbie");
//		mapper.insertReply(rep);
//		
//		System.out.println("목록전에"+rep.getReplyNo());
//		
//		mapper.insertReply(rep);
//		mapper.deleteReply(7);
//		
//		mapper.selectList(218) // => List<Reply>
//		      .forEach(reply -> System.out.println(reply.toString()));
		
//		SearchVO search = new SearchVO();
//		search.setBno(212);
//		search.setRpage(3);
//		
//		mapper.selectList(search).forEach(reply -> System.out.println(reply.toString()));
		
//		Center c1 = new Center();
//		c1.setId(1);
//		c1.setAddress("test address");
//		c1.setPhoneNumber("053-111-1111");
//		c1.setCenterName("testcenter1");
//		c1.setSido("sido1");
//		
//		Center c2 = new Center();
//		c2.setId(2);
//		c2.setAddress("test address");
//		c2.setPhoneNumber("053-222-1111");
//		c2.setCenterName("testcenter2");
//		c2.setSido("sido2");
//		
//		Center[] list = {c1, c2};
//		
		ReplyMapper mapper = DataSource.getInstance().openSession(true).getMapper(ReplyMapper.class);
//		
//		//System.out.println(mapper.insertCenter(list));
//		System.out.println(mapper.deleteCenter(list));
		
		List<Map<String, Object>> list = mapper.countPerSido();
		for(Map<String, Object> map : list) {
			Set<Entry<String, Object>> set = map.entrySet();
			for(Entry<String, Object> ent : set) {
				System.out.print(ent.getKey());
				System.out.println(" : " + ent.getValue());
			}
			System.out.println("==============");
		}
		
		
		
	}
}
