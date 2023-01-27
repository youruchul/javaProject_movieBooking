package service;

import java.util.List;

import dto.Movie;
import repository.MovieRepository;


public class MovieServiceImpl implements MovieService{
	private static MovieRepository movieRepository;
	private static MovieServiceImpl service = new MovieServiceImpl();
	public static MovieServiceImpl getInstance() {
		return service;
	}
	
	public MovieServiceImpl( ) {
		this.movieRepository = MovieRepository.getInstance();
	}
	
	@Override
	public List<Movie> findAllMovie() {
		List<Movie> movies = movieRepository.findALLMovie();
		return movies;
	}

}
