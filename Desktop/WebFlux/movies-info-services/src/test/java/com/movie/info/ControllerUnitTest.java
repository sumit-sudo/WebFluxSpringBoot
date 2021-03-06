package com.movie.info;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.movie.info.controller.MovieInfoController;
import com.movie.info.dao.MovieInfoRepo;
import com.movie.info.model.MovieInfo;

import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@WebFluxTest(controllers = MovieInfoController.class)
public class ControllerUnitTest {
	
	@Autowired
	WebTestClient webTestClient;
	@MockBean
	MovieInfoRepo movieInfoRepo;
	
	//@Test
	public void testAllMovieList() {
		
		var movie=new MovieInfo("A101", "Avenger", 2004, List.of("Sumit","Rahul"), LocalDate.parse("2020-08-02"));
		
		when(movieInfoRepo.findById("A101")).thenReturn(Mono.just(movie));
		
		webTestClient.get()
					.uri("/v1/movielist/A101")
					.exchange()
					.expectStatus()
					.is2xxSuccessful()
					.expectBody(MovieInfo.class)
					.consumeWith(result->{
						var fetchedMovie=result.getResponseBody();
						assert fetchedMovie.getName()!=null;
						System.out.println("id "+fetchedMovie.getMovieInfoId());
					});
					
				/*
				 * If we had to test findAll(), then we could have used  expectedBody().hassize()
				 * 
				 */
	}
	@Test
	public void postMthodUnitTest() {
	var movie=new MovieInfo("A0045", "Kabir Singh", 2004, List.of("Sumit","Rahul"), LocalDate.parse("2020-08-02"));
	when(movieInfoRepo.save(null).thenReturn(Mono.just(movie)));
		
		
		webTestClient.post()
					.uri("/v1/saveMovies")
					.bodyValue(movie)
					.exchange()
					.expectStatus()
					.isCreated()
					.expectBody(MovieInfo.class)
					.consumeWith(result->{
						var savedMovie=result.getResponseBody();
						System.out.println(savedMovie.getName());
						
					});
					
		
	}

}
