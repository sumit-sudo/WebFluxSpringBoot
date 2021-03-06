package com.movie.info;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.movie.info.dao.MovieInfoRepo;
import com.movie.info.model.MovieInfo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class IntegrationTesting {

	@Autowired
	WebTestClient webTestClient;
	@Autowired
	MovieInfoRepo movieInfoRepo;
	@Test
	public void postTest() {
		
		var movie=
				new MovieInfo("1211", "Avenger", 2004, List.of("Sumit","Rahul"), LocalDate.parse("2020-08-02"));
		
		webTestClient.post()
					.uri("/v1/saveMovies")
					.bodyValue(movie)
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(MovieInfo.class)
					.consumeWith(result ->{
						
						var savedMovie=result.getResponseBody();
						System.out.println("savedMovie.getName() "+savedMovie.getName());
					
						//assert savedMovie.getName()!=null;
					});
		
		
	}

}
