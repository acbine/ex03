package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController //date로 응답하겠다.
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping(value="getText",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //sample/getText라고오면 처리할것 ,변환을UTF-8로 해줌
	public String getText() {
		log.info("MIME TYPE:"+MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요"; //안녕하세요라는 문자를 돌려준다 -> 깨짐 즉데이터가 iso 되있음 즉인식을못함
	}
	
	@GetMapping(value="/getSample" ,produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) //배열로 둘다 보면 둘다사용가능
	public SampleVO getgetSample() {
		SampleVO vo =new SampleVO(1, "오늘은","수요일");
		return vo;
		
		//json 변환 라이브러리만 있으면 json으로
		//xml 변환 라이브러리만 있으면 xml으로
		//xml json 둘다 있으면 xml으로 간다 (우선순위 xml이 더높음)
		//url뒤에 /json으로하면 json으로 .xml하면 xml 로 처리된다.
	}
	
	@GetMapping("/getList")
	public List<SampleVO> getList(){
//		List<SampleVO> list =new ArrayList<SampleVO>();
//		for(int i=0; i<=10; i++) {
//			SampleVO vo = new SampleVO(i,"성"+i,"이름"+i);
//			list.add(vo);			
//		}
	return  IntStream.range(0,10).mapToObj(i -> new SampleVO(i,"성"+i,"이름"+i)).collect(Collectors.toList());
		
	}
	
	@GetMapping("/getMap")
	public Map<String,SampleVO> getMap(){
		Map<String,SampleVO> map = new HashMap<String, SampleVO>();
		map.put("나는키값", new SampleVO(1, "오늘은","수요일"));
		return map;
		
	}
	// /sample/check?height=140&weight=50
	//특정키값이 존재하게 제한 (파라미터 없으면 400에러 처리)
	@GetMapping(value="/check",params= {"height","weight"})
	public ResponseEntity<SampleVO> check(int height , int weight){ //상태코드 변경 데이터는잘가는 데 상태코드만 변경
		SampleVO vo =new SampleVO(1, height+"", weight+"");
		
		ResponseEntity<SampleVO> result=null;
		
		if(height<150) 
			result= ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		else 
			result= ResponseEntity.status(HttpStatus.OK).body(vo);
		
		return result;
	}
	
	//p366 url에 있는 값 읽는법
	
	//sample/product/원하는글자/원하는숫자
	//sample/product/bags/123
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		return new String[] {"cat값"+cat,"pid값"+pid};
		
	}
	
	//보낸 데이터를 읽는법
	@PostMapping("/ticket")
	public SampleVO convert(@RequestBody SampleVO vo) {//파라미터가 아닌 json과 xml 등의 데이터를 수집할떄 사용
		log.info("수집이잘되나?"+vo);
		return vo;
	}
	

}
