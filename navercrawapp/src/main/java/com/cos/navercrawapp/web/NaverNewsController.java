package com.cos.navercrawapp.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.navercrawapp.domain.NaverNews;
import com.cos.navercrawapp.domain.NaverNewsRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
 * parallel():  ExecutorService기반으로 단일 스레드 고정 크기(Fixed) 스레드 풀을 사용하여 병렬 작업에 적합함.
	single(): Runnable을 사용하여 지연이 적은 일회성 작업에 최적화
	elastic(): 스레드 갯수는 무한정으로 증가할 수 있고 수행시간이 오래걸리는 블로킹 작업에 대한 대안으로 사용할 수 있게 최적화 되어있다.
	boundedElastic(): 스레드 갯수가 정해져있고 elastic과 동일하게 수행시간이 오래걸리는 블로킹 작업에 대한 대안으로 사용할 수 있게 최적화 되어있다.
	immediate(): 호출자의 스레드를 즉시 실행한다.
	fromExecutorService(ExecutorService) : 새로운 Excutors 인스턴스를 생성한다.
 */

// 비동기 서버
@RequiredArgsConstructor
@RestController
public class NaverNewsController {

	private final NaverNewsRepository naverNewsRepository;
	
	@GetMapping(value = "/naverNews", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<NaverNews> home(){
		// 새로운 스레드가 만들어져서 응답을 잡고 있음.
		return naverNewsRepository.mFindAll()
				.subscribeOn(Schedulers.boundedElastic()); // Date -> Timestamp 변경해서 리턴을 못하네!!
	}
	

}
