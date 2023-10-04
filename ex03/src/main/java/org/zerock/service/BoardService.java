package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	//*사용자 관점의 서비스
	// *1.게시글 목록보기
	 List<BoardVO> getList(Criteria cri);
	//*2.게시글작성
	 void register(BoardVO vo); //작성하기위해서는 BoardVO객체를 받아 넣어야한다
	//*3.게시글수정
	 boolean modify(BoardVO vo);  //수정하기위해서는 BoardVO객체를 받아 넣어야한다
	//* 4.게시글삭제
	 boolean remove (Long bno);
	//* 5.게시글상세보기
	 BoardVO get (Long bno);
	
	 //*6.게시글 총개수보기 
	 Long totalcount(Criteria cri);
	 
	 //*7 좋아요 객수
	 void good(Long bno);
	 
	 String JAVATIME();

	 

}
