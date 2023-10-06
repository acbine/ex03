package org.zerock.serviceTest;

import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardsServiceTest {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void TestgetList() {
		Criteria cri = new Criteria(2,4);
		log.info("리스트 서비스"+service.getList(cri));
		
	}
	
	@Test
	public void Testregister() {
		BoardVO vo = new BoardVO();
		vo.setWriter("작성자1");
		vo.setTitle("제목2");
		vo.setContent("내용2");
		service.register(vo);
		
	}
	
	@Test
	public void Testmodify() {
		BoardVO vo = new BoardVO();
		vo.setBno(3L);
		vo.setTitle("수정된 제목4");
		vo.setContent("수정된내용4");
		service.modify(vo);
		
	}
	
	@Test
	public void Testremove() {
		log.info("삭제 서비스"+ service.remove(17L));
	}
	
	@Test
	public void get() {
		service.get(18L);
	}
	
	@Test
	public void Testtotalcount() {
		Criteria cri = new Criteria();
		cri.setType("C");
		cri.setKeyword("0918");
		log.info("검색을했들떄 갯수"+service.totalcount(cri));
		
	}
	
	@Test
	public void Testgoodservice() {
		service.good(2346472L);
		
	}
	
	@Test
	public void TestJAVA() {
		service.JAVATIME();
		log.info(service.JAVATIME());
	}
	

}
