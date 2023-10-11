package org.zerock.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.zerock.domain.BnoVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.domain.Total;
import org.zerock.service.BoardService;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@RestController
@Log4j
@AllArgsConstructor
public class MyAPIController {
	
	BoardService boardService; //생성자를 만들어야지 자동을 으로주입됨
	ReplyService replyService;
	
	
	@GetMapping("/myapi/replybnocount")
	public String myapi() {
		log.info("replybnocount 신호확인");
		return boardService.bnoCount()+"";
	}
	
	@PutMapping(value="/myapi/bestbno",produces=MediaType.APPLICATION_JSON_VALUE)
	public BnoVO bestbno() {
		log.info("bestbno 신호확인");
		BnoVO bnoVO = new BnoVO();
		bnoVO.setBno(boardService.replyMaxCountNumber());
		return bnoVO;
	}
	
	@GetMapping(value="myapi/{num1}/{num2}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public Total myapiNum1Num2(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
		log.info(num1);
		log.info("totalvo 신호들엉옴");
		int sum=0;
		for(int i=num1; i<=num2; i++) {
			sum+=i;
		}
		Total totalvo = new Total();
		totalvo.setCount((long)(sum));
		
		
		return totalvo;
	}	
	@GetMapping(value="/myapi/board/{page}/{amount}" , produces=MediaType.APPLICATION_JSON_VALUE)
	public  List<BoardVO> boardVO(@PathVariable int page, @PathVariable int amount) {
		Criteria cri = new Criteria(page,amount);
		return boardService.getList(cri);
	}
	
	@PostMapping(value="/myapi/board/{page}/{amount}" , produces=MediaType.APPLICATION_JSON_VALUE)
	public  List<BoardVO> boardVO(@PathVariable("page") int page,
			@PathVariable("amount") int amount, @RequestBody Criteria cri){
		log.info("신호확인boardVO");	
		return boardService.getList(cri);
	}
	
	@GetMapping(value="/myapi/board/newReply1" , produces=MediaType.APPLICATION_JSON_VALUE)
	public  List<ReplyVO> todaylist(){ //시간순서대로 모든리플내용을 보여주는것		
		return replyService.todaylist();
	}
	@GetMapping(value="/myapi/board/newReply2" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public  int repltcount(){ //리플 총개수 몇개인지 나오개		
		return replyService.repltcount();
	}
}
