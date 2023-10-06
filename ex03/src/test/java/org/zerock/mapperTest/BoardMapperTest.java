package org.zerock.mapperTest;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	
	@Autowired
	private BoardMapper boardMapper;
	
	
	@Test
	public void testgetlist() {
		boardMapper.getList().forEach(x->log.info(x));
	}
	
	@Test
	public void TestGetListWithPaging() {
		Criteria cri = new Criteria();
		cri.setType("TCW");
		cri.setKeyword("알리");
		boardMapper.getListWithPaging(cri).forEach(x->log.info("결과확인"+x));
	}
	
	@Test
	public void TestRead() {
		log.info("2번 글읽기" +boardMapper.read(2));
		
	}
	@Test
	public void Testinsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("제목4");
		vo.setContent("내용4");
		vo.setWriter("테스터4");
		boardMapper.insert(vo);
	}
	
	@Test
	public void Testupdate() {
		BoardVO vo = new BoardVO();
		vo.setBno((long) 20);
		vo.setTitle("수정이다");
		vo.setContent("수정작성자");
		
		log.info("수정결과:"+boardMapper.update(vo));
		
	}
	
	@Test
	public void testGood() {
		boardMapper.good(2346472L);		
	}
	
	
	
	@Test
	public void testDelete() {
		log.info("삭제:"+boardMapper.delete(9L));
	}
	
	@Test
	public void TestInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("mapper테스트3");
		vo.setContent("잘되겠지3");
		vo.setWriter("테스터3");
		boardMapper.insertSelectKey(vo);
		log.info("입력된 글번호는:"+vo.getBno());
	}
	
	@Test
	public void insertDeta() {
		BoardVO vo = new BoardVO();
		
		for(int i=0; i<5; i++) {
			vo.setTitle("제목"+i);
			vo.setContent("내용"+i);
			vo.setWriter("테스터"+i);
			boardMapper.insert(vo);
		}
		
		
	}
	
	
	@Test
	public void TestRank() {
		boardMapper.Rank().forEach(x->log.info(x));	
	}
	
	@Test
	public void Testcount() {
		Criteria cri = new Criteria();
		log.info("전체글 개수:"+boardMapper.count(cri));
		cri.setType("W");
		cri.setKeyword("0918");
		log.info("검색글 개수:"+boardMapper.count(cri));
		
		
	}
	
	@Test
	public void TestlastBoard() {
		boardMapper.lastBoard().forEach(x->log.info(x));
		
	}
	
	@Test
	public void Testtimecount() {
		log.info("시간대별 개숫는 "+boardMapper.timecount());
	}
	
	

}
