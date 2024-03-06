package co.yedam.board.mapper;

import java.util.List;

import co.yedam.board.Board;
import co.yedam.board.Book;
import co.yedam.common.SearchVO;

public interface BoardMapper {
	
	List<Board> boardList(SearchVO search); 
	// BoardMapper 인터페이스를 구현하는 BoardMapper.xml -> 메소드는 id다. resultType은 반환값
	
	int getTotalCnt(SearchVO search);
	// 얘도 서치컨디션에 따라 달라져야하니깐.
	
	// 상세화면에 사용될 데이터.
	Board selectBoard(int bno);
	
	// 조회수 증가
	int updateCount(int bno);
	// 중요!! insert, update, delete는 적용된 건수가 반환된다!
	// 우리가 jdbc했을때 int r과 같은것!
	
	// 글 수정(그런데 조회수는 빼야함..)
	int updateBoard(Board board);
	
	// 글 삭제
	int deleteBoard(int bno);
	
	// 글 등록
	int insertBoard(Board board);
	
	// Ajax 도서목록, 도서등록, 도서삭제
	List<Book> bookList();
	int insertBook(Book book);
	int deleteBook(String bcode);

}
