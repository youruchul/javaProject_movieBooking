package service;

import java.util.List;

import dto.Coupon;
import dto.Customer;
import dto.Schedule;
import dto.Seat;
import repository.BookingRepository;
import repository.CouponRepository;
import repository.CustomerRepository;
import repository.MovieRepository;
import repository.ScheduleRepository;
import repository.SeatRepository;

public class BookingServiceImpl implements BookingService{
	private static ScheduleRepository scheduleRepository;
	private static SeatRepository seatRepository;
	private static CouponRepository couponRepository;
	private static BookingRepository bookingRepository;
	private static CustomerRepository costomerRepository;
	private static BookingServiceImpl service = new BookingServiceImpl();
	public static BookingServiceImpl getInstance() {
		return service;
	}
	
	public BookingServiceImpl() {
		this.scheduleRepository = ScheduleRepository.getInstance();
		this.seatRepository = SeatRepository.getInstance();
		this.couponRepository = CouponRepository.getInstance();
		this.bookingRepository = BookingRepository.getInstance();
		this.costomerRepository = CustomerRepository.getInstance();
	}
	
	@Override
	public List<Schedule> findSchdule(int movieId) {
		List<Schedule> schedules = scheduleRepository.findSchedule(movieId);
		return schedules;
	}

	@Override
	public List<Seat> findSeat(int movieId, int scheduleId) {
		List<Seat> seats = seatRepository.findSeat(movieId, scheduleId);
		return seats;
	}

	@Override
	public void choiceSeat(int selectSeat) {
		seatRepository.choiceSeat(selectSeat);
	}

	@Override
	public List<Coupon> findMyCoupon(int customerId) {
		List<Coupon> coupons = couponRepository.findMyCoupon(customerId);
		return coupons;
	}

	@Override
	public void bookingSeat(int seatId, int customerId) {
		seatRepository.insertCustomerId(seatId, customerId);
	}

	@Override
	public void cancelSeat(int selectSeat) {
		seatRepository.cancelSeat(selectSeat);
	}

	@Override
	public void insertBooking(int counts, int finalPrice, int customerId, int useCouponId, int scheduleId) {
		bookingRepository.insertBooking(counts, finalPrice, customerId, useCouponId, scheduleId);
	}

	@Override
	public void useCoupon(int couponId) {
		couponRepository.useCoupon(couponId);
	}

	@Override
	public Customer findCsutomerName(int memberId) {
		Customer customer = costomerRepository.findCustomerName(memberId); 
		return customer;
	}

	@Override
	public List<Seat> emptySeatCount(int movieId, int scheduleId) {
		List<Seat> seats = seatRepository.emptySeatCount(movieId, scheduleId);
		return seats;
	}


}
