package notification;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.StudentDAO;
import dao.UserDAO;
import dao.BookingDAO;

import dto.Booking;
import dto.Student;
import dto.User;


public class PreferenceEmail {
	
	public static void sendPreferenceFormEmail(Student student, String info, String ipType) {
		
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "fourleavestechnology3605@gmail.com";
		String password = "weDeserveHD";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = new Date();
		String mailTo = student.getEmail();
		String subject = "Sponsor Preference form";

		String message =
				"<font face='Arial'>"
				+ "<table width='600' max-height='100%' border='3'>"
				+ "<tr>"
					+ "<td colspan='2' bgcolor=Gold align='center'>"
						+ "<img style='width: 120px; height:100px' src='https://coursera-university-assets.s3.amazonaws.com/6c/b759685fe8d8dcb491ac80208ca2b7/unsw_logo_360x360.png' align='left'>"
						+ "<h1> INFS Undergraduate Coordinator System</h1>"
					+ "</td>"
				+ "</tr>"
				+ "<tr>"
					+ "<td colspan='1' bgcolor=Black align='center'>"
						+ "<font size=2 color='white'>"
							+ "<p> Email preference form notification </p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "<tr valign='center'>"
					+ "<td bgcolor='#eee' width='78%' height='78%'>" + "<br />"
						+ "<p align='right'>" + sdf.format(current) + "</p>"
						+ "<br />"
						+ "<p> Dear " + student.getFirstName()+ "</p><br />"
							+ "It is time to fill out your preferences for: .<br />" + ipType
							+ "Please select the company that you wish to have under each numbered preferences.<br />"
							+ "Furthermore the coop coordinator wanted you the read this: <br />"
							+ info
							+ "Please click the link to access the form: "
							+ "Link:  <a href='http://localhost:8080/FourLeavesTech/preferenceForm?id="+student.getStudentId()+"&ip="+ipType+"'>Link</a>"
							
						+"</p>"
						+"<p> Kindly Regards, </p>"
						+"<p> UGCIS </p>"
					+ "</td>"
				+"</tr>"
				+"<tr>"
					+ "<td colspan='2' bgcolor=Black align='left'>"
						+ "<font size=1 color='white'>"
							+ "<p> UNSW AUSTRALIA  |  UNSW SYDNEY NSW 2052 AUSTRALIA</p>"
						+ "</font>"
						+ "<font size=1 color='white'>"
							+ "<p> ABN 57 195 873 179  |  CRICOS Provider Code 00098G</p>"
						+ "</font>"
					+ "</td>"
				+ "</tr>"
				+ "</table>"
				+ "</font>";
			

		

		try {
			sendEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("Email sent to student.");
		} catch (Exception ex) {
			System.out.println("Failed to send email.");
			ex.printStackTrace();
		}
		
	}
	
	
	public static void sendEmail(String host, String port, final String userName, final String password,
			String toAddress, String subject, String message)
					throws AddressException, MessagingException, UnsupportedEncodingException {

		// sets SMTP server properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		String username = "fourleavestechnology3605@gmail.com";
		

			class GMailAuthenticator extends Authenticator {
				String user;
				String pw;
				public GMailAuthenticator (String username, String password)
				{
					super();
					this.user = username;
					this.pw = password;
				}
				public PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(user, pw);
				}
			}


		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName, "UGCIS"));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// set plain text message
		//msg.setText(message);
		msg.setContent(message, "text/html; charset=utf-8");

		// sends the e-mail
		Transport.send(msg);
	}

}
