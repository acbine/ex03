package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	//1.쓰고
	int insert(ReplyVO vo);
	//2.일고
	List<ReplyVO> getList(Long bno);
	//3.수정
	int update(ReplyVO vo);
	//4.지우고
	int delete(Long rno);
	//5. 개인 댓글 하난 보여주기윈한것
	ReplyVO oneserch(Long rno);
}
