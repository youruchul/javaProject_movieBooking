package service;

import java.util.List;

import dto.Booking;
import repository.BookingRepository;


public class MyBookingServiceImpl implements MyBookingService{
	private static BookingRepository bookingRepository;
	private static MyBookingServiceImpl service = new MyBookingServiceImpl();
	public static MyBookingServiceImpl getInstance() {
		return service;
	}
	
	public MyBookingServiceImpl( ) {
		this.bookingRepository = BookingRepository.getInstance();
	}
	
	@Override
	public List<Booking> findMyBooking(int customerId) {
		List<Booking> bookings = bookingRepository.findMyBooking(customerId);
		return bookings;
	}
}
