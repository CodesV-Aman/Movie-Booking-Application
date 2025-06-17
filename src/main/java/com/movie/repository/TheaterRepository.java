package com.movie.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

	public List<Theater> findByTheaterLocation(String theaterLocation);

}
