package domain;

import lombok.Data;

@Data 
public class Member {
	//lombok패키지를 이용한 Getter와 Setter 자동생성
	private int member_idx;
	private String id;
	private String pass;
	private String name;
	private String email;
	private String regdate;
		
}
