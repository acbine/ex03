package org.zerock.domain;

import java.util.Date;

import lombok.Data;


@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	private int good; //오라클에있는데이터를 받아드릴댸 필요
}
