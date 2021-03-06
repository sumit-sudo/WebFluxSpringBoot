package com.reactive.programming.practice.service;

import java.time.Duration;
import java.util.List;
import java.util.Random;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class FluxAndMonoGeneratorService {
	public Flux<String> nameFlux(){//Reactive sequence
		return Flux.fromIterable(List.of("SUMIT","AMIT","RAHUL"))
				.map(s->s.toLowerCase())
				.filter(s->s.length()>4)
				.map(s->s.length()+"-"+s)// displaying name along with length
				.log();// db to remote service call
	}
	/*
	 * FlatMap transform one source element to a Flux of 1 to N
	 * "ALEX"->Flux.just("A","L","E","X");
	 * eg we might get list of id from table and then we might haver to get details corresponding to each ID
	 * (101,102,103,104) --> Flux.just((101,"SUmit",1000),(102,"AMIT",2000) so on) 
	 */
	
	public Flux<String> nameFlux_flatMap(){
		return Flux.fromIterable(List.of("SUMIT","AMIT","RAHUL"))
				.filter(s->s.length()>4)
				.flatMap(s->getStringAsChar(s))// convert from Flux<Flux<String>> to Flux<String>
				.log();// db to remote service call
				
	}
	
	// Adding delay in Async call for checking weather data is on order or not
	public Flux<String> nameFlux_flatMapAsynchronousNature(){
		return Flux.fromIterable(List.of("SUMIT","AMIT","RAHUL"))
				.filter(s->s.length()>4)
				.flatMap(s->getStringAsCharWithDelay(s))
				.log();
				/*
				 * checking Asynchronous nature of flatMap,
				 As It is asynchronous, It wont wait for completion of method getStringAsCharWithDelay, 
				 and because of this, Output is random
				 */
				 /*
				  * Instead of flatmap(), we can use concatmap() as well, basic difference between concat map and 
				  * flat map is both are Asynchronous but concatmap() preserve ordering and because of this
				  * concatmap() is slower than flatmap.
				  * 
				  */
				// db to remote service call
				
	}
	
	
	public Flux<String> getStringAsChar(String str){
		var charAaary=str.split("");
		return Flux.fromArray(charAaary);
	}
	
	public Flux<String> getStringAsCharWithDelay(String str){
		var charAaary=str.split("");
		var delay=new Random().nextInt(1000);
		return Flux.fromArray(charAaary).delayElements(Duration.ofMillis(delay));
	}
	
	// Checking Flux Immutability
	public Flux<String> chechkingFluxImmutability(){//Reactive sequence
		var immutableFlux=Flux.fromIterable(List.of("SUMIT","AMIT","RAHUL"));
		immutableFlux.map(s->s.toLowerCase());
		return immutableFlux;
		// Here, map won't work as the nature of flux is immutable,
		// In the above we have attached the map operator with the actual 
		// data source i.e Flux and that is why It was mutable
	}
	
	public Mono<String> nameMono(){
		
		return Mono.just("KAMAL")
				.map(s->s.toLowerCase());
	}
	
	/*
	 * Making Asynchronous call to Mono using flatmap()
	 */
	public Mono<List<String>> nameMonoFlatMap(){
		
		return Mono.just("KAMAL")
				.map(s->s.toLowerCase())
				.filter(s->s.length()>4)
				.flatMap(s->splitAsyncMono(s));
	}
	
	/*
	 * flatMapMany()-> It convert Mono to Flux
	 */
	public Flux<String> nameMonoFlatMapMany() {
		return Mono.just("mnvjv")
					.map(s->s.toUpperCase())
					.filter(s->s.length()>4)
					.flatMapMany(s->getStringAsChar(s)).log();
				
	}
	// Flux with concat/concatWith() operator
	public Flux<String> fluxConcat() {
		var flux1=Flux.just("A","B","C");
		var flux2=Flux.just("D","E","F");
		// Similarly we can use flux1.concatWith(flux2) instance method where as concat is static method
		return Flux.concat(flux1,flux2);
	}
	
	// Flux with concat/concatWith() operator
	public Flux<String> fluxMerge() {
		var flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(2));
		var flux2=Flux.just("D","E","F").delayElements(Duration.ofSeconds(3));
			// Similarly we can use flux1.concatWith(flux2) instance method where as concat is static method
		return Flux.merge(flux1,flux2);
	}
	
	//Flux with zip and zipWith
	public Flux<String> fluxZip() {
		var flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(2));
		var flux2=Flux.just("D","E","F").delayElements(Duration.ofSeconds(3));
			// Similarly we can use flux1.concatWith(flux2) instance method where as concat is static method
		return Flux.zip(flux1,flux2,(a,b)->a+b);
	}
	
	// Mono with mergeWith
	public Flux<String> monoMerge() {
		var mono1=Mono.just("A");
		var mono2=Mono.just("D");
			// Similarly we can use flux1.concatWith(flux2) instance method where as concat is static method
		return mono1.mergeWith(mono2);
	}

	private Mono<List<String>> splitAsyncMono(String s) {
		var charArray=s.split("");
		return Mono.just(List.of(charArray)).delayElement(Duration.ofSeconds(5));
	}
}
