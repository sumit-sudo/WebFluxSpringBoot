package com.movie.info;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.movie.info.dao.MovieInfoRepo;
import com.movie.info.model.MovieInfo;

import reactor.test.StepVerifier;

@DataCassandraTest
@SpringBootTest

public class MovieInfoRepoTest {

	private String movieInfoId;
	private String name;
	private Integer yer;
	private List<String> cast;
	private LocalDate releaseDate;
	
	@Autowired
	MovieInfoRepo movieInfoRepo;
	
	@BeforeEach
	public void setUp() {
		
		var movieList=List.of(
				new MovieInfo("A101", "Avenger", 2004, List.of("Sumit","Rahul"), LocalDate.parse("2020-08-02")),
				new MovieInfo("B01", "Badshah", 2010, List.of("Ambar","Kamal"), LocalDate.parse("2020-08-02")));
						
			movieInfoRepo.saveAll(movieList).blockLast();
	}
	
	@AfterEach
	public void tierDown() {
	//	movieInfoRepo.deleteAll().block();
	}
	
	@Test
	public void findAll() {
		
		var movieList=movieInfoRepo.findAll().log();
		
		StepVerifier.create(movieList)
					.expectNextCount(2)
					.verifyComplete();
	}
	
	@Test
	public void FindById() {
		
		var mono=movieInfoRepo.findById("B01");
		StepVerifier.create(mono)
					.assertNext(s->{
							//assertEquals("Badshah",s.getName())
					})
					.verifyComplete();
		
					
					
	}
	
}
