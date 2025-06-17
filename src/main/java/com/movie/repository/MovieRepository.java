package com.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	public Optional<List<Movie>> findByGenre(String genre);
	public Optional<List<Movie>> findByLanguage(String language);
	public Optional<List<Movie>> findByName(String title);

}
