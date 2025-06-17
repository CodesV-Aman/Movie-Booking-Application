package com.movie.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dto.BookingDTO;
import com.movie.entity.Booking;
import com.movie.entity.BookingStatus;
import com.movie.entity.Show;
import com.movie.entity.User;
import com.movie.repository.BookingRepository;
import com.movie.repository.ShowRepository;
import com.movie.repository.UserRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private UserRepository userRepository;

	public Booking createBooking(BookingDTO bookingDTO) {

		Show show = showRepository.findById(bookingDTO.getShowId())
				.orElseThrow(() -> new RuntimeException("Show not found"));

		if (!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())) {
			throw new RuntimeException("Not enough seats are available");

		}

		if (bookingDTO.getSeatNumbers().size() != bookingDTO.getNumberOfSeats()) {
			throw new RuntimeException("Seats numbers and numbers and number of seats must be equal");

		}

		validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumbers());

		User user = userRepository.findById(bookingDTO.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		Booking booking = new Booking();
		booking.setUser(user);
		booking.setShow(show);
		booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
		booking.setSeatNumbers(bookingDTO.getSeatNumbers());
		booking.setBookingStatus(BookingStatus.PENDING);
		booking.setBookingtime(LocalDateTime.now());
		booking.setPrice(calculateTotalAmount(show.getPrice(), bookingDTO.getNumberOfSeats()));

		return bookingRepository.save(booking);

	}

	public Double calculateTotalAmount(Double price, Integer numberOfSeats) {
		// TODO Auto-generated method stub
		return price * numberOfSeats;
	}

	public boolean isSeatsAvailable(Long showid, Integer numberOfSeats) {
		Show show = showRepository.findById(showid).orElseThrow(() -> new RuntimeException("Show not found"));

		int bookedseats = show.getBookings().stream()
				.filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELED)
				.mapToInt(Booking::getNumberOfSeats).sum();

		return (show.getTheater().getTheaterCapacity() - bookedseats) >= numberOfSeats;
	}

	public void validateDuplicateSeats(Long showid, List<String> seatNumbers) {
		Show show = showRepository.findById(showid).orElseThrow(() -> new RuntimeException("Show not found"));

		Set<String> occupiedSeats = show.getBookings().stream()
				.filter(b -> b.getBookingStatus() != BookingStatus.CANCELED).flatMap(b -> b.getSeatNumbers().stream())
				.collect(Collectors.toSet());

		List<String> duplicateSeats = seatNumbers.stream().filter(occupiedSeats::contains).collect(Collectors.toList());

		if (!duplicateSeats.isEmpty()) {
			throw new RuntimeException("Seats are already booked");

		}

	}

	public List<Booking> getUserBookings(Long userid) {
		// TODO Auto-generated method stub
		return bookingRepository.findByUserId(userid);
	}

	public List<Booking> getShowBookings(Long showid) {
		// TODO Auto-generated method stub
		return bookingRepository.findByShowId(showid);
	}

	public Booking confirmBooking(Long bookingid) {
		Booking booking = bookingRepository.findById(bookingid)
				.orElseThrow(() -> new RuntimeException("Booking not found"));

		if (booking.getBookingStatus() != BookingStatus.PENDING) {
			throw new RuntimeException("Booking is not in pending state");
		}

		// ask the payment to the user
		booking.setBookingStatus(BookingStatus.CONFIRMED);

		return bookingRepository.save(booking);

	}

	public Booking cancelBooking(Long bookingid) {
		Booking booking = bookingRepository.findById(bookingid)
				.orElseThrow(() -> new RuntimeException("Booking not found"));

		validateCancelation(booking);
		booking.setBookingStatus(BookingStatus.CANCELED);
		return bookingRepository.save(booking);
	}

	public void validateCancelation(Booking booking) {

		LocalDateTime showTime = booking.getShow().getShowTime();
		LocalDateTime deadlineTime = showTime.minusHours(2);
		if (LocalDateTime.now().isAfter(deadlineTime)) {
			throw new RuntimeException("Cannot cancel the booking");

		}

		if (booking.getBookingStatus() == BookingStatus.CANCELED) {
			throw new RuntimeException("Booking already canceled");

		}

	}

	public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
		
		return bookingRepository.findByBookingStatus(bookingStatus);

	}

	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
		
	}

}
