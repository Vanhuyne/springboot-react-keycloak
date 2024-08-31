package com.movierecommendation.movie_review_service;

import com.movierecommendation.movie_review_service.dto.MovieRequest;
import com.movierecommendation.movie_review_service.model.Movie;
import com.movierecommendation.movie_review_service.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MovieReviewServiceApplication {

	@Autowired
	private MovieService movieService;
	public static void main(String[] args) {
		SpringApplication.run(MovieReviewServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
//			List<Movie> movies = Arrays.asList(
//					Movie.builder().title("Inception").genre("Sci-Fi").releaseYear(2010).director("Christopher Nolan").description("A thief enters dreams to steal secrets.").poster("inception.jpg").build(),
//					Movie.builder().title("The Shawshank Redemption").genre("Drama").releaseYear(1994).director("Frank Darabont").description("Two imprisoned men bond over a number of years.").poster("shawshank.jpg").build(),
//					Movie.builder().title("The Godfather").genre("Crime").releaseYear(1972).director("Francis Ford Coppola").description("The aging patriarch of an organized crime dynasty transfers control to his son.").poster("godfather.jpg").build(),
//					Movie.builder().title("Pulp Fiction").genre("Crime").releaseYear(1994).director("Quentin Tarantino").description("The lives of two mob hitmen, a boxer, a gangster and his wife intertwine.").poster("pulp_fiction.jpg").build(),
//					Movie.builder().title("The Dark Knight").genre("Action").releaseYear(2008).director("Christopher Nolan").description("Batman faces the Joker in Gotham City.").poster("dark_knight.jpg").build(),
//					Movie.builder().title("Schindler's List").genre("Biography").releaseYear(1993).director("Steven Spielberg").description("A businessman saves the lives of over a thousand Jewish refugees during the Holocaust.").poster("schindlers_list.jpg").build(),
//					Movie.builder().title("Forrest Gump").genre("Drama").releaseYear(1994).director("Robert Zemeckis").description("The presidencies of Kennedy and Johnson, the Vietnam War, and other historical events unfold through the perspective of an Alabama man.").poster("forrest_gump.jpg").build(),
//					Movie.builder().title("The Matrix").genre("Sci-Fi").releaseYear(1999).director("The Wachowskis").description("A computer programmer discovers a dystopian world inside a simulation.").poster("matrix.jpg").build(),
//					Movie.builder().title("Goodfellas").genre("Crime").releaseYear(1990).director("Martin Scorsese").description("The story of Henry Hill and his life in the mob.").poster("goodfellas.jpg").build(),
//					Movie.builder().title("The Silence of the Lambs").genre("Thriller").releaseYear(1991).director("Jonathan Demme").description("An FBI cadet must receive the help of an incarcerated cannibal killer to catch another serial killer.").poster("silence_of_the_lambs.jpg").build()
//			);


//			movies.forEach(
//					movie -> {
//						MovieRequest movieRequest = movieService.convertToMovieRequest(movie);
//						movieService.createMovie(movieRequest);
//						System.out.println("Movie created: " + movie.getTitle());
//					}
//			);


		};
	}
}
