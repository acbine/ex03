package org.zerock.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Log4j
@Service //스프링서 서비스 객체로 인식시키ㅣ기 위해서 (현재로는 @Componet 와 동일)
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("목록보기 서비스 요청");
		return mapper.getListWithPaging(cri);
		
	}

	@Override
	public void register(BoardVO vo) {
		log.info("글등록");
		mapper.insertSelectKey(vo);

	}

	@Override
	public boolean modify(BoardVO vo) {
		log.info("글수정 서비스");
		/*int count=mapper.update(vo);
		if(count==1) {
			return true;
		}else {
			return false;
		}
		*/
		
		return mapper.update(vo)==1 ? true : false;
	}

	@Override
	public boolean remove(Long bno) {
		return  mapper.delete(bno)==1 ?  true : false;
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("글상세보기");
		return mapper.read(bno);
	}

	@Override
	public Long totalcount(Criteria cri) {		
		return mapper.count(cri);
		
	}

	@Override
	public void good(Long bno) {
		mapper.good(bno);
		log.info("bno값확인"+bno);
		
	}

	@Override
	public String JAVATIME() {
		LocalTime LT = LocalTime.now();
		DateTimeFormatter FM = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
		String FT = LT.format(FM);
		return FT;
		
	}

	
}
