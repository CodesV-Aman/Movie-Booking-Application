package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.dto.ShowDTO;
import com.movie.entity.Show;
import com.movie.services.ShowService;

@RestController
@RequestMapping("/api/show")
public class ShowController {

	@Autowired
	private ShowService showService;

	@PostMapping("/createshow")
	public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO) {
		return ResponseEntity.ok(showService.createShow(showDTO));
	}

	@GetMapping("/getallshows")
	public ResponseEntity<List<Show>> getAllShows() {
		return ResponseEntity.ok(showService.getAllShows());
	}

	@GetMapping("/getshowsbymovie/{movieid}")
	public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long movieid) {
		return ResponseEntity.ok(showService.getShowsByMovie(movieid));
	}

	@GetMapping("/getshowsbytheater/{theaterid}")
	public ResponseEntity<List<Show>> getShowsByTheater(@PathVariable Long theaterid) {
		return ResponseEntity.ok(showService.getShowsByTheater(theaterid));
	}

	@PutMapping("/updateshow/{id}")
	public ResponseEntity<Show> updateSHow(@PathVariable Long id, @RequestBody ShowDTO showDTO) {
		return ResponseEntity.ok(showService.updateShow(id,showDTO));
	}

	@DeleteMapping("/deleteshow/{id}")
	public ResponseEntity<Show> deleteShow(@PathVariable Long id){
	  showService.deleteShow(id);
	  
	  return ResponseEntity.ok().build();
	}

}
