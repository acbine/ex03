package org.zerock.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;
import org.zerock.service.ReplyServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTest {
	
	@Autowired//필드주입
	ReplyService service;
	
	@Test
	public void test() {
		log.info("테스트");
	}
	
	@Test
	public void testregister() {//완료
		ReplyVO vo = new ReplyVO();
		vo.setBno(2346469L);
		vo.setReply("서비스에서 등록5");
		vo.setReplyer("서비스에서 유저5");
		service.register(vo);
	}
	
	@Test
	public void testmodify() {//완료
		ReplyVO vo = new ReplyVO();
		vo.setRno(11L);
		vo.setReply("서비스에서 수정");
		service.modify(vo);
	}
	
	@Test
	public void testremove() { //완료
		ReplyVO vo = new ReplyVO();
		vo.setRno(10L);
		service.remove(vo.getRno());
	}
	
	@Test
	public void testgetList() {
		service.getList(2346469L).forEach(x->log.info("2346469글에 달린 댓글내용확인"+x));
	}
	
	@Test
	public void Testget() {
		log.info("서비스에서 댓글1개 테스트 한 내용"+service.get(3L));
	}
	

	

}
