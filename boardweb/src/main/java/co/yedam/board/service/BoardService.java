package co.yedam.board.service;

import java.util.List;

import co.yedam.board.Board;
import co.yedam.board.Book;
import co.yedam.common.SearchVO;

// 예) 조회, 수정 기능 ->

// 만약 서비스가 없다면 매퍼에는 
// 1.조회기능->수정기능
// 2.수정기능->조회기능 
// 결국 같은 기능을 두번만들어야.

// 1.조회기능
// 2.수정기능
// 서비스에는 1호출하고 -> 2호출 | 2호출하고 -> 1호출

public interface BoardService {
	List<Board> boardList(SearchVO search);
	int boardToatalCnt(SearchVO search);
	
	// 단건조회(내용보이는) + 조회수.
	Board getBoard(int bno);
	
	// 글수정. 조회수는 카운트 안되게.
	boolean modifyBoard(Board board);
	
	// 글삭제.
	boolean removeBoard(int bno);
	
	// 글 등록.
	boolean addBoard(Board board);
	
	// Ajax 도서목록, 도서등록.
	List<Book> bookList();
	boolean addBook(Book book);
	boolean removeBook(String bcode);
}
