package com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Booking;
import com.movie.entity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByUserId(Long userid);

	List<Booking> findByShowId(Long showid);

	List<Booking> findByBookingStatus(BookingStatus bookingStatus);

}
