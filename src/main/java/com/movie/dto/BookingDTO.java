package com.movie.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.movie.entity.BookingStatus;

import lombok.Data;

@Data
public class BookingDTO {

	private Integer numberOfSeats;
	private Double price;
	private LocalDateTime bookingtime;
	private BookingStatus bookingStatus;
	
	private List<String> seatNumbers;
	private Long userId;
	private Long showId;
}
