package movieBooking;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dto.Booking;
import dto.Coupon;
import dto.Customer;
import dto.Movie;
import dto.Schedule;
import dto.Seat;
import service.BookingServiceImpl;
import service.MovieServiceImpl;
import service.MyBookingServiceImpl;

public class Main {
	public static MovieServiceImpl movieService = MovieServiceImpl.getInstance();
	public static BookingServiceImpl bookingService = BookingServiceImpl.getInstance();
	public static MyBookingServiceImpl myBookingService = MyBookingServiceImpl.getInstance();
	public static void main (String[] args) {
		
		while(true) {
			Scanner sc = new Scanner(System.in); 
			System.out.println("------------------------------------------------------------------");
			System.out.println("원하는 동작을 선택하세요. 1. 영화 조회, 2. 영화 예매, 3. 내 예매 조회 4. 나가기");
			int data = sc.nextInt();
			// 1을 입력하면 영화 조회 하기
			if(data == 1) {
				System.out.println("<현재의 상영작>");
				// 영화 리스트를 반복 조회
				List<Movie> movies = movieService.findAllMovie();
				for (int i = 0; i < movies.size(); i++) {
					System.out.println(i+1 + "번 상영작" + movies.get(i));
				}
			}
			// 2를 입력하면 예매하기 
			else if(data == 2) {
				// 회원번호 입력
				System.out.println("회원번호를 입력하세요.");
				int memberId = 0;
				try {
					memberId = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("숫자를 입력해주세요.");
					continue;
				}
				Customer customer = bookingService.findCsutomerName(memberId);
				if (customer.getId() == null) {
					System.out.println("없는 회원입니다.");
					continue;
				}
				System.out.println(customer.getName() + "님 환영합니다.");
				
				// 영화 번호 선택 
				System.out.println("영화의 번호를 선택하세요.");
				int selectMovieNumber = 0;
				try {
					selectMovieNumber = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("숫자를 입력해주세요.");
					continue;
				}
				List<Movie> movies = movieService.findAllMovie();
				List<Schedule> schedules = new ArrayList<>();
				try {
					schedules = bookingService.findSchdule(movies.get(selectMovieNumber-1).getId());
					System.out.println("<"+ movies.get(selectMovieNumber-1).getTitle() + "> 의 상영시간표 입니다.");
					for (int i = 0; i < schedules.size(); i++) {
						System.out.println("<" + (i+1) + "> " + schedules.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("없는 번호 입니다.");
					continue;
				} 
				
				// 영화 시간 선택
				System.out.println("영화의 시간을 선택하세요.");
				int selectMovieSchedule = 0;
				try {
					selectMovieSchedule = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("숫자를 입력해주세요.");
					continue;
				}
				
				// 인원수 입력
				System.out.println("인원수를 입력하세요.");
				int counts = 0;
				try {
					counts = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("숫자를 입력해주세요.");
					continue;
				}
				
				List<Seat> emptySeatCount = new ArrayList<>();
				try {
					emptySeatCount = bookingService.emptySeatCount(movies.get(selectMovieNumber-1).getId(), schedules.get(selectMovieSchedule-1).getId());
				} catch (IndexOutOfBoundsException e) {
					System.out.println("없는 시간표입니다.");
					continue;
				}
				
				if (counts > emptySeatCount.size()) {
					System.out.println("좌석이 부족합니다.");
					continue;
				}
				
				List<Integer> choiceSeatIds = new ArrayList<>();
				// 좌석 선택
				
				for (int i = 0; i < counts; i++) {
					List<Seat> seats = bookingService.findSeat(movies.get(selectMovieNumber-1).getId(), schedules.get(selectMovieSchedule-1).getId());
					System.out.println("원하는 좌석을 선택해주세요.(" + ( i + 1 ) + "/" + counts + ")");
					System.out.println("<잔여좌석>");
					for (int j = 0; j < 5; j++) {
						if (seats.get(j).getEmptySeat()==1) {
							System.out.print(" " + (j+1));
						} else {
							System.out.print(" X");
						}
					}
					System.out.println();
					// System.out.println("좌석의 번호를 선택하세요.");
					int selectSeat = sc.nextInt();
					if (seats.get(selectSeat-1).getEmptySeat() == 1) {
						bookingService.choiceSeat(seats.get(selectSeat-1).getId());
						choiceSeatIds.add(seats.get(selectSeat-1).getId());
					} else if (seats.get(selectSeat-1).getEmptySeat() == 0){
						i--;
						System.out.println("이미 예매된 좌석입니다.");
						System.out.println("다시 좌석을 선택해주세요.");
					}
				
				}
				
				// 쿠폰 선택
				List<Coupon> coupons = bookingService.findMyCoupon(memberId);
				System.out.println("고객님께서 보유하신 쿠폰입니다.");
				for (int i = 0; i < coupons.size(); i++) {
					System.out.println((i+1) + ". " + coupons.get(i));
				}
				System.out.println("쿠폰을 사용하시려면 쿠폰번호를 / 사용하지 않고 결제하시려면 0를 입력해주세요.");
				int selectCoupon = sc.nextInt();
				
				int finalPrice = schedules.get(selectMovieSchedule-1).getPrice() * counts;
				if (selectCoupon == 0) {
					System.out.println("최종금액 " + finalPrice + "원 입니다.");
				} else {
					int discountPrice = schedules.get(selectMovieSchedule-1).getPrice() * counts * coupons.get(selectCoupon-1).getPercent()/100;
					finalPrice = finalPrice - discountPrice;
					System.out.println("쿠폰적용 금액 " + finalPrice + "원 입니다.");
				}
				
				// 예매 완료
				System.out.println("예매완료 >> 1 / 취소 >> 0");
				int order = sc.nextInt();
				if (order == 1) {
					// useCoupon
					bookingService.useCoupon(coupons.get(selectCoupon-1).getId());
					// counts finalPrice
					System.out.println(counts + " " +  finalPrice + " " + memberId);
					bookingService.insertBooking(counts, finalPrice, memberId, coupons.get(selectCoupon-1).getId(), 
							schedules.get(selectMovieSchedule-1).getId());
					// seat - customerId
					for (int i = 0; i < counts; i++) {
						bookingService.bookingSeat(choiceSeatIds.get(i), memberId);
					}
					System.out.println("예매가 완료되었습니다.");
				} else if (order == 0){
					// update emptySeat -> 1
					System.out.println("예매를 취소합니다.");
					for (int i = 0; i < counts; i++) {
						bookingService.cancelSeat(choiceSeatIds.get(i));
					}
				} else {
					System.out.println("잘못된 입력입니다. 초기화면으로 돌아갑니다.");
					// update emptySeat -> 1
					for (int i = 0; i < counts; i++) {
						bookingService.cancelSeat(choiceSeatIds.get(i));
					}
				}
				
			}
			else if(data == 3) {
				System.out.println("회원번호를 입력하세요.");
				int memberId = sc.nextInt();
				Customer customer = bookingService.findCsutomerName(memberId);
				System.out.println(customer.getName() + "님의 예매내역입니다.\n");
				List<Booking> bookings = myBookingService.findMyBooking(memberId);
				for (int i = 0; i < bookings.size(); i++) {
					System.out.println((i+1) + "번 예매내역\n" + bookings.get(i));
				}
				
				
			}
			else if(data == 4) {
				// 나가기
				break;
			}
			else {
				System.out.println("다시 입력하세요!");
			}
			
		}
		
	}
}
// conn.setAutoCommit(false);

