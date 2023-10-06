package org.zerocl.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.serviceTest.BoardsServiceTest;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration //컨트롤러 테스트를 위해
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"
	 ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //컨트롤러 서블릿주소 콤마 주의
@Log4j
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext ctx; //mockMvc 객체를 만들떄 필요
	
	private MockMvc mockMvc; //이 객체를 이용해서 컨트롤러는 테스트 해야함
							// 객체를 직접 생성해서 상용해야함
	
	@Before //junit으로 테스트 할떄 먼저 수햏ㅇ 하는 메소드
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
						
		
	log.info("url리스트 요청결과 모델 데이터"+	mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
	
	}
	
	
	@Test
	public void testregister() throws Exception {		
			log.info("등록처리 요청결과"+	
			mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
					.param("title", "junit으로")
					.param("content", "mockMVC를 이용해서")
					.param("writer", "tester1"))
			.andReturn().getModelAndView().getViewName());	
	}
	
	@Test
	public void testget() throws Exception {		
			log.info("url 상세보기 모델데이터 "+	
			mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
					.param("bno", "11"))
			.andReturn().getModelAndView().getModelMap());	
	}
	
	@Test
	public void testremove() throws Exception {		
			log.info("삭제처리 요청결과"+	
			mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
					.param("bno", "23"))
			.andReturn().getModelAndView().getViewName());	
	}
	
	@Test
	public void testmodify() throws Exception {		
			log.info("수정처리 요청결과"+	
			mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
					.param("content", "0918 수정내요2")
					.param("title", "0918 수정제목2")
					.param("bno", "18"))
					
			.andReturn().getModelAndView().getViewName());	
	}
	
	
	@Test
	public void testgetregister() throws Exception {		
			log.info("등록하면 요청 요청결과"+	
			mockMvc.perform(MockMvcRequestBuilders.get("/board/register"))				
			.andReturn().getModelAndView().getViewName());	
	}
	
	@Test
	public void testgetremove() throws Exception {		
			log.info("등록하면 요청 요청결과"+	
			mockMvc.perform(MockMvcRequestBuilders.get("/board/remove"))				
			.andReturn().getModelAndView().getViewName());	
	}
	
	
	
	
	

}
