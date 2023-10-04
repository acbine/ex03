package org.zerock.domain;

import org.zerock.service.BoardService;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	//화면에서 페이지바를 표시하기 위해 필요한 정보
	private int startPage ,endPage , totalEndPage, totalStartPage; //엔드페이지 전체글개수에서 페지당 글개수를 나눈후 올림처리
	private boolean prev,next ;
	
	private Criteria cri; //현재페이지 ,페이지당 글개수
	private long total; //전체글개수

	public PageDTO(Criteria cri , long total) {
		this.cri=cri;
		this.total=total;
		
		//페이지바의 끝페이지 = (올림(현재페이지/10))*10
		endPage =  (int)((Math.ceil(cri.getPageNum()/10.0))*10);
		
		//페이지바의 시가페이지 = 끝페이지-9
		startPage=endPage-9;
		
		totalStartPage=1;
		
		totalEndPage=(int) Math.ceil(total/10.0);
		
		
			//진짜마지막페이지		//소숫점 올림 (전체글/한페이지당 개수)
		int realEnd=(int)Math.ceil((total*1.0)/cri.getAmount());
		//페이지바의 끝페이가 진짜 마지막페이지보다 크면 진짜로 변경
		
		if(endPage>realEnd) {
			endPage=realEnd;
		}
		//endPage 끝페이잘면 next가 true
		next = (endPage != realEnd);
		
		//시작페이지가 1이 아닐때 prev가 true;
		prev =(startPage !=1);
		
		
		
		
		
		
		
	}
	
	

}
