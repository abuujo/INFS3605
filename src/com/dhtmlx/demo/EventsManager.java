package com.dhtmlx.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.mysql.jdbc.Statement;

import dao.BaseDAO;
import dao.BookingDAO;
import dao.BookingListDAO;
import dao.EnrolmentDAO;
import dao.StudentDAO;
import dto.Booking;
import dto.BookingList;
import dto.Course;
import dto.Enrolment;
import dto.Student;
import dto.User;
import service.CourseService;

public class EventsManager extends DHXEventsManager {
	
	public static User user;
	
	public EventsManager(HttpServletRequest request) {
		super(request);
	}

	//This class will generate Calendar UI based on actual bookings.
	public Iterable getEvents() {

		ArrayList<DHXEv> evs = new ArrayList<DHXEv>();
		List<BookingList> bookinglist = BookingListDAO.selectAll();
		
		for(BookingList bookinglister: bookinglist){
			
			Booking booking = BookingDAO.selectById(bookinglister.getBookingId());
			
			String dateStr = booking.getStartDate();
			String dateStr1 = booking.getEndDate();

			DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd kk:mm", Locale.US);
			DateFormat writeFormat2 = new SimpleDateFormat( "MM/dd/yyyy kk:mm", Locale.US);



			Date date = null;
			Date date1 = null;
			try {

				date = writeFormat.parse(dateStr);
				date1 = writeFormat.parse(dateStr1);
				String dateString2 = writeFormat2.format(date);
				String dateString = writeFormat2.format(date1);

				DHXEvent ev1 = new DHXEvent();
				ev1.setId(booking.getBookingId());
				ev1.setStart_date(dateString2);
				ev1.setEnd_date(dateString);
				ev1.setText("Appointment with: " + booking.getzId());

				evs.add(ev1);

			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return evs;
	}

	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		if (status == DHXStatus.UPDATE) {
			String start_date = new SimpleDateFormat("yyyy-MM-dd kk:mm").
					format(event.getStart_date());
			String end_date = new SimpleDateFormat("yyyy-MM-dd kk:mm").
					format(event.getEnd_date());
			
			Booking booking = BookingDAO.selectById(event.getId());
			

			booking.setStartDate(start_date);
			booking.setEndDate(end_date);

			BookingDAO.update(booking);

		} else if (status == DHXStatus.INSERT) {
			
			
			String start_date = new SimpleDateFormat("yyyy-MM-dd kk:mm").
					format(event.getStart_date());
			String end_date = new SimpleDateFormat("yyyy-MM-dd kk:mm").
					format(event.getEnd_date());
			
			String zId = event.getText();
			
			Booking booking = new Booking();
			
			booking.setStartDate(start_date);
			booking.setEndDate(end_date);
			booking.setzId(zId);
			booking.setCategory("Please Complete");
			booking.setLocation("TBD");
			booking.setStatus("Pending");
			booking.setPriority(5);
			
			
			BookingDAO.add(booking);
			
			Booking currentBooking = BookingDAO.selectByAllAttributes(zId,
					"Please Complete",
					start_date,
					end_date,
					"TBD",
					"Pending",
					5);
			
			Student student = StudentDAO.selectByZId(zId);
			int userId = 0;
			if(user != null){
				userId = user.getUserId();
				BookingListDAO.add(currentBooking.getBookingId(), student.getStudentId(), userId);
				System.out.println("Booking list added");
			}
			
			//Complete
			
			
		} else if (status == DHXStatus.DELETE) {
			Booking booking = BookingDAO.selectById(event.getId());
			BookingDAO.delete(booking);
			
		}
		return status;
	}

	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new DHXEvent();
	}

}

