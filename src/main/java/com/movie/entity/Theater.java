package com.movie.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Theater {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String theaterName;
	private String theaterLocation;
	private Long theaterCapacity;
	private String theaterScreenType;
	

	@OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Show> show;
}
