package org.zerock.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Criteria {
	//페이지처리하기 윈한 정보 +검색장보
	//페이지 번호(pageNum)
	//한페이지에 보여줄개수 (amount)
	//검색선택 (type) -C (내용 ) T(타이틀) W(작성어)
	//검색어(keyword)
	
	private int pageNum=1;
	private int amount=10; //기본값
	private String type;
	private String keyword;
	
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public Criteria() { //기본생성자
		this(1,10); 
	}
	
	//type 글자를 한글자씩 짜르는 메소드
	public String[] getTypeArr() { //typeArr에 대응한 ㅡ게터 #{typeArr}쓰면  이getter 호출
		if(type!=null) {
			return type.split(""); //한글자씩 잘른다
		}else {
			return new String[] {}; //빈배열을 만들어 리턴해준다
		}
		
		//return (type!=null) ? type.split("") : String[] {};
	}
	
	
	

}
