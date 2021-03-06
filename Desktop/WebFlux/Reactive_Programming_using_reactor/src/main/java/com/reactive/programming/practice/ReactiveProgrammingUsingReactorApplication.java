package com.reactive.programming.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reactive.programming.practice.service.FluxAndMonoGeneratorService;

@SpringBootApplication
public class ReactiveProgrammingUsingReactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveProgrammingUsingReactorApplication.class, args);
		FluxAndMonoGeneratorService obj=new FluxAndMonoGeneratorService();
		//obj.chechkingFluxImmutability().subscribe(name->{
//		obj.nameFlux().subscribe(name->{
//			System.out.println("Flux name is "+name);
//		});
//		
//		obj.nameFlux_flatMap().subscribe(name->{
//			System.out.println("Flux name is "+name);
//		});
//		
//		obj.nameMonoFlatMap().subscribe(name->{
//			System.out.println("Mono Name "+name);
//		});
//		
//		obj.nameMono().subscribe(name->{
//			System.out.println("Mono Name "+name);
//		});
//		
//		obj.nameMonoFlatMapMany().subscribe(name->{
//			System.out.println("converting MONO to FLUX  Name "+name);
//		});
//		
//		obj.fluxConcat().subscribe(name->{
//			System.out.println("flux concat "+name);
//		});
//		obj.fluxMerge().subscribe(name->{
//			System.out.println("Flux Merge "+name);
//		});
		obj.monoMerge().subscribe(name->{
			System.out.println("Mono Merge "+name);
		});
		obj.fluxZip().subscribe(name->{
			System.out.println("Mono Merge "+name);
		});
	}

}
