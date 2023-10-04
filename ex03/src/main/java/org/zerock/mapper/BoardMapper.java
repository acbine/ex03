package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.RankVO;
import org.zerock.domain.TimeVO;

public  interface BoardMapper {
	
	//@Select("select *from tbl_Board") //XML로 명령어를 함 
	//게시글 목록보기
	List<BoardVO> getList();
	
	//페이지처리 -현제는 무조건 2페이지 출력 (한페이지당 10개)
	List<BoardVO> getListWithPaging(Criteria cri);

	//게시글 상세보기
	BoardVO read(long bno);
	
	
	//게시글 작성
	void insert(BoardVO vo); //void로 적을시 
	
	//게시글 작성 (작성된글번호확인) 즉 게시글을 작성한고 콘솔로 작성 번호를 확인
	void insertSelectKey(BoardVO vo);
	
	//게시글 좋앙요
	void good(Long bno);
	
	//게시글 수정
	int update(BoardVO vo);
	

	//게시글 삭제
	int delete(long bno);
	
	//보너스(작설글 랭킬 5등까지만 작성자 작성글 개수)
	List<RankVO> Rank();
	
	//보너스 (업데이트 일자 기준으로 최근 갱신된 글 5개)
	List<BoardVO> lastBoard(); //BoardVO 타입의 리스트를 생성후 lastBoard() 메소드를 만든다
	
	// 보너스 전체 글개수 (검색에도 대응) 가져오기
	Long count(Criteria cri);
	
	//보너스 (시간대별 글개수 통계 작성일 기준)  ex) 00:시 5개 01시  1개/ 23 11
	List<TimeVO> timecount();
	
	//게시글 페이지 처리(뒤에)
	
	//게시글 검색(뒤에)
	

}
