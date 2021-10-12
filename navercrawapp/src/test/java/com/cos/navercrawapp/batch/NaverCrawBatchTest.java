package com.cos.navercrawapp.batch;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import com.cos.navercrawapp.domain.NaverNews;

public class NaverCrawBatchTest {
	
	// 8Byte
	long aid = 277493;
	
	// 2021.10.12
	// 2021.10.20
	@Test
	public void 뉴스수집_테스트() {
		System.out.println("배치 프로그램 시작======================");
		List<NaverNews> naverNewsList = new ArrayList<>();
		int errorCount = 0;
		int successCount = 0;
		int crawCount = 0;
		while(true) {
			String aidStr = String.format("%010d", aid);
			System.out.println("aidStr : "+aidStr);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid="+aidStr;
			
			try {
				Document doc =  Jsoup.connect(url).get();
				// title, company, createdAt
				String title = doc.selectFirst("#articleTitle").text();
				String company = doc.selectFirst(".press_logo img").attr("alt");
				String createdAt = doc.selectFirst(".t11").text();
				
				//System.out.println("title : "+title);
				//System.out.println("company : "+company);
				//System.out.println("createdAt : "+createdAt);
				
				LocalDate today = LocalDate.now();
				LocalDate yesterday = today.minusDays(1);
				//System.out.println("yesterday : "+yesterday);
				
				createdAt = createdAt.substring(0, 10);
				createdAt = createdAt.replace(".", "-");
				//System.out.println(createdAt);
				
				if(today.toString().equals(createdAt)) {
					System.out.println("createdAt : "+createdAt);
					break; // while문 빠져나가고 중지!!
				}
				
				if(yesterday.toString().equals(createdAt)) { // List 컬렉션에 모았다가 DB에 save() 하기
					System.out.println("어제 기사입니다. 크롤링 잘 됨");
					
					naverNewsList.add(NaverNews.builder()
							.title(title)
							.company(company)
							.createdAt(Timestamp.valueOf(LocalDateTime.now().minusDays(1)))
							.build()
					);
					
					crawCount++;
				}
				
				successCount ++;
			} catch (Exception e) {
				System.out.println("해당 주소에 페이지를 찾을 수 없습니다 :  "+e.getMessage());
				errorCount ++;
			} 
			aid++;
			
		}	// end of while
		System.out.println("배치 프로그램 종료======================");
		System.out.println("성공횟수 : "+successCount);
		System.out.println("실패횟수 : "+errorCount);
		System.out.println("크롤링 성공횟수 : "+crawCount);
		System.out.println("마지막 aid 값 : "+aid);
	}
	
	//@Test
	public void 로컬_문자열날짜_테스트() {
		LocalDate today = LocalDate.now();
		String createdAt = "2021-10-12";
		System.out.println(today);
		System.out.println(createdAt);
		if(today.toString().equals(createdAt)) {
			System.out.println("같은 날입니다.");
		}
	}
}
