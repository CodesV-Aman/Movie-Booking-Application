package com.movie.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer numberOfSeats;
	private Double price;
	private LocalDateTime bookingtime;
	private BookingStatus bookingStatus;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "booking_seat_numbers")
	private List<String> seatNumbers;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="show_id",nullable = false)
	private Show show;
	
	

}
