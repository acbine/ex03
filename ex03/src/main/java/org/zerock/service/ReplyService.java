package org.zerock.service;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyService {
	
	//댓글등록
	 int register(ReplyVO vo);
	
	//해당글에대한 댓글보기
	 List<ReplyVO> getList(Long bno);
	//댓글삭제
	 int remove(Long rno);
	//댓들수정
	 int modify(ReplyVO vo);
	//댓글 한게보기 
	 ReplyVO get(Long rno); //get /replise/2
	 // 댓글 갯수보기
	
	 
	
}	
