package com.movie.info.controller;


import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class FluxAndMonoController  {

	@GetMapping("/flux")
	public Flux<Integer> getFlux(){
		return Flux.just(1,2,3,4).log();
	}
	
	@GetMapping("/mono")
	public Mono<String> getMono(){
		return Mono.just("Hello-world").log();
	}
	
	@GetMapping(value="/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Long> getStreamData(){
		/*
		 * This example also display how FLUX and Mono work Ascyn.
		 * ctor-http-nio-3 -> Thread1
		 * parallel-1 -> Thred2
			[ctor-http-nio-3] reactor.Flux.Interval.4                  : request(1)
			[     parallel-1] reactor.Flux.Interval.4                  : onNext(0)
			[ctor-http-nio-3] reactor.Flux.Interval.4                  : request(31)
			[     parallel-1] reactor.Flux.Interval.4                  : onNext(1)
		 * 
		 */
		return Flux.interval(Duration.ofSeconds(1)).log();
	}
	
}
