package co.yedam.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.board.Board;
import co.yedam.board.Book;
import co.yedam.board.mapper.BoardMapper;
import co.yedam.common.DataSource;
import co.yedam.common.SearchVO;

// Impl 구현클래스라는 뜻..
// 업무로직을 담고있는 프로세스.
// 데이터처리는 mapper 기능.
public class BoardServiceImpl implements BoardService {
	
	// mybatis 기능쓰기위한, 인스턴스 생성.
	// BoardDAO bdao = new BoardDAO(); 와 같다고 생각하면 된다.
	SqlSession session = DataSource.getInstance().openSession(true); // true 자동커밋, false는 커밋 일일이 해줘야.
	BoardMapper mapper = session.getMapper(BoardMapper.class);
	// BoardMapper.class = 프로그램실행시점에 BoardMapper 인스턴스를 생성하는것. 정보를 읽어와서 객체를 만들어주는
	
	@Override
	public List<Board> boardList(SearchVO search) {
//		session.selectOne("mapper에 있는 name.. co.y.m.BoardMapper.boardList");  옛날방식.
		return mapper.boardList(search); //bdao.boardList(search);
	}
	
	@Override
	public int boardToatalCnt(SearchVO search) {
		return mapper.getTotalCnt(search);
	}

	@Override
	public Board getBoard(int bno) {
		mapper.updateCount(bno); //가져오면서 조회수도 같이증가.
		return mapper.selectBoard(bno);
		//mapper.selectBoard(bno)는 <select id="selectBoard" 쿼리실행되고, 그 결과값을 받아옴.
		
	}

	@Override
	public boolean modifyBoard(Board board) {
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public boolean removeBoard(int bno) {
		return mapper.deleteBoard(bno) == 1 ;
	}

	@Override
	public boolean addBoard(Board board) {
		return mapper.insertBoard(board) == 1;
	}

	@Override
	public List<Book> bookList() {
		return mapper.bookList();
	}

	@Override
	public boolean addBook(Book book) {
		return mapper.insertBook(book) == 1;
	}

	@Override
	public boolean removeBook(String bcode) {
		return mapper.deleteBook(bcode) == 1;
	}
	
	

}
