package org.zerock.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Log4j
@RequestMapping("/board/**")
@AllArgsConstructor
public class BoardController {
	
	BoardService service;
	
	@GetMapping("/list")
	public void list(Model model,Criteria cri ,String SearchPageNum) { //데이터를 수집하기 위해서는  홈페이지에서 데이터를 보내고  list(데이터를 받는곳야함 )
		log.info("list url 요청");
		model.addAttribute("list",service.getList(cri)); // 글목록
		model.addAttribute("pageMaker",new PageDTO(cri, service.totalcount(cri))); //페이지바 처리
		log.info("ㅋㅋㅋㅋㅋㅋ"+new PageDTO(cri, service.totalcount(cri)));
		log.info("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ"+new PageDTO(cri, 0));
		log.info("String 타입ㅇ으"+SearchPageNum);
		
		
		
		model.addAttribute("time" , service.JAVATIME() );
		model.addAttribute("count", service.totalcount(cri));
		
		
		
		// -> board/list/jsp
	}
	
	//등록처리 (모든 항목 -BoardVO) board/register(post) <- 입력화면 (get)
	@PostMapping("/register")
	public String register(BoardVO vo, RedirectAttributes rttr) { //RedirectAttributes rttr 한번만 모내짐
		log.info("register url 요청");
		service.register(vo);
		rttr.addFlashAttribute("oper","create");
		rttr.addFlashAttribute("result",vo.getBno()); // 데이터 한번만 전송
		return "redirect:/board/list"; //왜  리다이렉트 사용? redirect가 없으면 jsp 이고
										   //redirect 가 있으면 요청
	}
	
	@GetMapping("/register") //url이 배열도 들어갈수있따
	public void resgiser() {
		
	}
	
	@GetMapping("/remove") //비번 화면 요청
	public void remove(Long bno,Model model) {
		model.addAttribute("bno", bno);
	}
	
	
	@GetMapping("modify")
	public void modify(Long bno, Model model ,Criteria cri) {
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);
	}
	
	
	
	
	//조회 (글번호-bno) board/get(get)
	@GetMapping("/get")
	public void  get (Long bno ,Model model ,Criteria cri ) {
		log.info("글 상세보기 요청");
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);
		log.info("굿이라는 데이터를 가져옴");
		
		//-> board.get.jsp void는 자기자신페이지로
	}
	//삭제(글번호 - bno) board/remove (post) <- 입력화면 (get)
	@PostMapping("/remove")
	public String remove(Long bno , String password, RedirectAttributes rttr) {
		log.info("삭제 url 요청");
		log.info("------ 포스트 요청삭제할번호확인-----------"+bno);
		log.info("------passward확인-----------"+password);
		
		if(password.equals("1234")) {
			if(service.remove(bno)) { //이상없으면 result 이름으로 success 라는 문자 전송
				rttr.addFlashAttribute("oper", "remove");
				rttr.addFlashAttribute("result", bno); 
			}
			return "redirect:/board/list";	
			
		}else{
			rttr.addFlashAttribute("flag", "fail");
			return "redirect:/board/remove?bno="+bno;			
		}
		
		
	}
	
	/*@GetMapping("/remove")
	public void remove() {
		
	}
	*/
	//수정 (수정글 BoardV) board/modify (post ) <- 입력화면(get)
	@PostMapping("/modify")
	public String modify(BoardVO vo ,RedirectAttributes rttr, Criteria cri) {
		log.info("수정 url 요청");
		if(service.modify(vo)) {
			rttr.addFlashAttribute("oper", "modify");		
			rttr.addFlashAttribute("result", vo.getBno());			
		}	
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
		
	}
	
	/*@GetMapping("/modify")
	public void modify() {
		
	}
	*/
	
	//좋아요처리
	@GetMapping("/good")
	public String good(Long bno) {
		service.good(bno);
		return"redirect:/board/list";
	}
	
	@PostMapping("/password")
	public void password(Long bno ,Model model) {
		log.info("---------삭제할번호----------------"+bno);
		model.addAttribute("board",service.get(bno));
		log.info("---------삭제할번호를 모델로보내준것----------------"+bno);
		
		
	}
	

}
