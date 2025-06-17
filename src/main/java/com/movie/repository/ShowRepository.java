package com.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.movie.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

	Optional<List<Show>> findByMovieId(Long movieid);

	Optional<List<Show>> findByTheaterId(Long theaterid);

}
