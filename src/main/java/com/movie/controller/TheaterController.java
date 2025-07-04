package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.dto.TheaterDTO;
import com.movie.entity.Theater;
import com.movie.services.TheaterService;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	@PostMapping("/addtheater")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO) {

		return ResponseEntity.ok(theaterService.addTheater(theaterDTO));
	}

	@GetMapping("/gettheaterbylocation")
	public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String theaterLocation) {

		return ResponseEntity.ok(theaterService.getTheaterByLocation(theaterLocation));
	}

	@PutMapping("/updatetheater/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody TheaterDTO theaterDTO) {

		return ResponseEntity.ok(theaterService.updateTheater(id, theaterDTO));
	}
	
	@DeleteMapping("/deletetheater/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {

		theaterService.deleteTheater(id);
		
		return ResponseEntity.ok().build();
	}


}
