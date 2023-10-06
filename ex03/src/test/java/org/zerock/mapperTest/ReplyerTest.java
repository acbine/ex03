package org.zerock.mapperTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyerTest {
	
	@Autowired //주입
	private ReplyMapper mapper;
	
	@Test
	public void testInsert() {//완료
		ReplyVO vo = new ReplyVO();
		vo.setBno(2346469L);
		vo.setReply("이클립스테스트 등록5");
		vo.setReplyer("이클립스테스트유저5");
		mapper.insert(vo);
	}
	
	@Test
	public void testUpdate() {//완료
		ReplyVO vo = new ReplyVO();
		vo.setRno(7L);
		vo.setReply("디벨로퍼는 커밋안하면 수정안됨");
		mapper.update(vo);
	}
	
	@Test
	public void testDelete() { //완료
		ReplyVO vo = new ReplyVO();
		vo.setRno(6L);
		mapper.delete(vo.getRno());
	}
	
	@Test
	public void testgetList() {
		mapper.getList(2346469L).forEach(x->log.info("2346469글에 달린 댓글내용확인"+x));
	}
	
	@Test
	public void Testoneserch() {
		log.info( "댓글 상세보기 테스트용 코드 "+mapper.oneserch(3L));
	}

}
