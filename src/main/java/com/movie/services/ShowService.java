package com.movie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dto.ShowDTO;
import com.movie.entity.Booking;
import com.movie.entity.Movie;
import com.movie.entity.Show;
import com.movie.entity.Theater;
import com.movie.repository.MovieRepository;
import com.movie.repository.ShowRepository;
import com.movie.repository.TheaterRepository;

@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	public Show createShow(ShowDTO showDTO) {
		Movie movie = movieRepository.findById(showDTO.getMovieId())
				.orElseThrow(() -> new RuntimeException("Movie not found"));

		Theater theater = theaterRepository.findById(showDTO.getTheaterId())
				.orElseThrow(() -> new RuntimeException("Theater not found"));

		Show show = new Show();

		show.setShowTime(showDTO.getShowTime());
		show.setPrice(showDTO.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepository.save(show);
	}

	public List<Show> getAllShows() {
		return showRepository.findAll();
	}

	public List<Show> getShowsByMovie(Long movieid) {
		
		Optional<List<Show>> showListBox=showRepository.findByMovieId(movieid);
		
		if(showListBox.isPresent()) {
			return showListBox.get();
		}
		else throw new RuntimeException("No shows available for movie");
	}

	public List<Show> getShowsByTheater(Long theaterid) {
		Optional<List<Show>> showListBox=showRepository.findByTheaterId(theaterid);
		
		if(showListBox.isPresent()) {
			return showListBox.get();
		}
		else throw new RuntimeException("No shows available in this theater");		
	}

	public Show updateShow(Long id, ShowDTO showDTO) {
		Show show=showRepository.findById(id).orElseThrow(() -> new RuntimeException("No show found"));
		
		Movie movie = movieRepository.findById(showDTO.getMovieId())
				.orElseThrow(() -> new RuntimeException("Movie not found"));

		Theater theater = theaterRepository.findById(showDTO.getTheaterId())
				.orElseThrow(() -> new RuntimeException("Theater not found"));

		

		show.setShowTime(showDTO.getShowTime());
		show.setPrice(showDTO.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepository.save(show);
	}

	public void deleteShow(Long id) {
		
		if(!showRepository.existsById(id)) {
			throw new RuntimeException("No show available for the id "+id);
		}
		
		List<Booking> bookings=showRepository.findById(id).get().getBookings();
		if(!bookings.isEmpty()) {
			throw new RuntimeException("Can't delete show with existing bookings");
			
		}
		
		showRepository.deleteById(id);

	}

}
