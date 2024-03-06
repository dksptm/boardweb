package co.yedam.common;

import java.util.List;

import co.yedam.board.Board;
import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardServiceImpl;

public class MainExe {
	public static void main(String[] args) {
//		SqlSessionFactory factory = DataSource.getInstance();
//		SqlSession session = factory.openSession();
		
//		List<Board> list = session.selectList("co.yedam.board.mapper.BoardMapper.boardList"); 이전방식
//		BoardMapper mapper = session.getMapper(BoardMapper.class);
//		List<Board> list = mapper.boardList();
		
		// 이제 mapper호출이 아닌, 업무서비스 로직기능을 통해 호출.
		
		SearchVO search = new SearchVO();
		search.setPage(2);
		search.setSearchCondition("T");
		search.setKeyword("홍");
		
		BoardService svc = new BoardServiceImpl();
		List<Board> list = svc.boardList(search);
		
		for(Board board : list) {
			System.out.println(board.toString());
		}
	}

}
