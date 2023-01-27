package service;

import java.util.List;

import dto.Booking;

public interface MyBookingService {
	List<Booking> findMyBooking(int customerId);
}
