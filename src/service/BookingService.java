package service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BookingDAO;
import dao.BookingListDAO;
import dto.Booking;
import dto.BookingList;
import dto.User;

public class BookingService {

	public static void addBooking(Booking booking)  {
		BookingDAO.add(booking);
	}
	
	public static List<Booking> getBookingList()  {
		List<Booking> list = new ArrayList<Booking>();
		list = BookingDAO.selectAll();
		
		return list;
	}
	
	public static List<Booking> getBookingsByUser(User user) {
		List<Booking> list = new ArrayList<Booking>();
		
		List<BookingList> bl = BookingListDAO.selectAll();
		for(BookingList row : bl) {
			if(row.getUserId() == user.getUserId()) {
				list.add(BookingDAO.selectById(row.getBookingId()));
			}
		}
		
		return list;
	}
	
	public static List<Booking> getBookingsBySearch(User user, String query)  {
		List<Booking> all = getBookingsByUser(user);
		List<Booking> result = new ArrayList<Booking>();
		for(Booking booking : all) {
			// Search by Category
			if(booking.getCategory() != null && booking.getCategory().toLowerCase().contains(query.toLowerCase())) {
				result.add(booking);
			
			// Search by Start date
			} else if(booking.getStartDate() != null && booking.getStartDate().contains(query)) {
				result.add(booking);
				
			// Search by End date
			} else if(booking.getEndDate() != null && booking.getEndDate().contains(query)) {
				result.add(booking);
				
			// Search by Location
			} else if(booking.getLocation() != null && booking.getLocation().toLowerCase().contains(query.toLowerCase())) {
				result.add(booking);
				
			// Search by student's zID
			} else if(booking.getzId() != null && booking.getzId().toLowerCase().contains(query.toLowerCase())) {
				result.add(booking);
			}
		}
		return result;
	}
	
	public static boolean isDateValid(String startDate, String endDate) throws ParseException {
		boolean bool = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date start = format.parse(startDate);
		Date end = format.parse(endDate);
		
		if(start.compareTo(end) <= 0) {
			bool = true;
		}
		return bool;
	}
}
