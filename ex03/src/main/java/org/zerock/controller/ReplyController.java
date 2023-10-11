package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.BoardService;
import org.zerock.service.ReplyService;
import org.zerock.service.ReplyServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {
	
	private ReplyService service;
	//consumes=MediaType.APPLICATION_JSON_UTF8_VALUE -> 통해 보내온 데이터 타입확인(현재 사용한것은 json type) 틀리면 415 HttpStatus.UNSUPPORTED_MEDIA_TYPE
	@PostMapping(value="/new",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> urlNew(@RequestBody ReplyVO vo) { 
		log.info("등록해야할 댓글 정보:"+vo);
		int count=service.register(vo);
		if(count==1){
			return new ResponseEntity<String>("success",HttpStatus.OK);
			//댓글이 등록되면 sucess 문자를 보내고 상태는 200(정상)
		}else{// 등록된 갯수를 리턴
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}//실패하면 아무것도 안보내고 500서버 에러= server_error
		 //400에러 HttpStatus.BAD_REQUEST
	}
	
	//ex /replies/3
	@DeleteMapping(value="/{rno}",produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> urlDelete(@PathVariable("rno")Long rno) {
		int count=service.remove(rno);
		if(count==1){
			return new ResponseEntity<String>("success",HttpStatus.OK);
			//댓글이 삭제되면 sucess 문자를 보내고 상태는 200(정상)
		}else{// 삭제된 갯수를 리턴
			return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
		}//실패하면 아무것도 안보내고 500서버 에러= server_error
		 //400에러 HttpStatus.BAD_REQUEST
	}
	
	
	@PutMapping("/{rno}") //수정
	public ResponseEntity<String> urlput(@PathVariable("rno")Long rno, @RequestBody ReplyVO vo) {
		vo.setRno(rno);
		service.modify(vo);
		return (service.modify(vo)==1)
				? new ResponseEntity<String>("success",HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
	}
	//ex/replies/pages/2346469        produces=보내는 데이터의 형태변경 xml ,json등
	@GetMapping(value="/pages/{bno}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<ReplyVO> urlList(@PathVariable("bno") Long bno){
		return service.getList(bno);
	}
	
	@GetMapping(value="/{rno}" , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyVO> urlget(@PathVariable("rno") Long rno ) {
		log.info("getmappling 요청확인 요청한 넘버"+rno);
		service.get(rno);
		return new ResponseEntity<ReplyVO>(service.get(rno),HttpStatus.OK);		
		
	}
	
	
}
