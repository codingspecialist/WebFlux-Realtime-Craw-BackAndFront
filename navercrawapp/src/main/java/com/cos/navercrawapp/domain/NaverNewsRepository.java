package com.cos.navercrawapp.domain;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import reactor.core.publisher.Flux;

public interface NaverNewsRepository extends ReactiveMongoRepository<NaverNews, String>{
	
	// db.runCommand( { convertToCapped: 'naver_realtime', size: 8192 } ) 
	@Tailable
	@Query("{}")
	Flux<NaverNews> mFindAll();
}
