package com.cos.navercrawapp.domain;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Data
@Document(collection = "naver_realtime")
public class NaverNews {
	@Id
	private String _id;
	
	private String company;
	private String title;
	private Date createdAt; // Timestamp 사용하지 말기
	

	
	
}
