package com.movie.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dto.TheaterDTO;
import com.movie.entity.Theater;
import com.movie.repository.TheaterRepository;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	public Theater addTheater(TheaterDTO theaterDTO) {

		Theater theater = new Theater();

		theater.setTheaterName(theaterDTO.getTheaterName());
		theater.setTheaterLocation(theaterDTO.getTheaterLocation());
		theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
		theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
		return theaterRepository.save(theater);
	}

	public List<Theater> getTheaterByLocation(String location) {
	    List<Theater> theaters = theaterRepository.findByTheaterLocation(location);
	    if (theaters.isEmpty()) {
	        throw new RuntimeException("No theater found for the entered location: " + location);
	    }
	    return theaters;
	}

	public Theater updateTheater(Long id, TheaterDTO theaterDTO) {
		Theater theater = theaterRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No theater found for id " + id));

		theater.setTheaterName(theaterDTO.getTheaterName());
		theater.setTheaterLocation(theaterDTO.getTheaterLocation());
		theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
		theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
		return theaterRepository.save(theater);
	}

	public void deleteTheater(Long id) {
		theaterRepository.deleteById(id);
		
	}

}
