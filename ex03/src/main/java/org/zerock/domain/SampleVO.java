package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //생정자 생성
@NoArgsConstructor //생성자를 생성하므로 기본생성자가 없어서 기본생성자를 생성해줌
public class SampleVO {
	private Integer mno;
	private String firstName;
	private String lastName;
}
