package service;

import java.util.List;

import dto.Coupon;
import dto.Customer;
import dto.Schedule;
import dto.Seat;

public interface BookingService {
	List<Schedule> findSchdule(int movieId);
	List<Seat> findSeat(int movieId, int scheduleId);
	void choiceSeat(int selectSeat);
	List<Coupon> findMyCoupon(int customerId);
	void bookingSeat(int seatId, int customerId);
	void cancelSeat(int selectSeat);
	void insertBooking(int counts, int finalPrice, int customerId, int useCouponId, int scheduleId);
	void useCoupon(int couponId);
	Customer findCsutomerName(int memberId);
	List<Seat> emptySeatCount(int movieId, int scheduleId);
}
