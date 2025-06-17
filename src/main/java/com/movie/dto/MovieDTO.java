package com.movie.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovieDTO {
	
	private String name;
	private String description;
	private String genre;
	private String language;
	private Integer duration;
	private LocalDate releaseDate;
	

}
