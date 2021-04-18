<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>


<div class="middle">
	<div class="panel-body">

		<div class="col-md-8">
			<h4>Booking List</h4>
			<hr />
			<c:if test="${ error != null }">
				<p class="font-red">${ error }</p>
			</c:if>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Category</th>
						<th>Date</th>
						<th>Location</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bookingItem" items="${ bookings }"
						varStatus="counter">
						<c:if test="${counter.index mod 2 == 0 }">
							<tr class="warning">
						</c:if>
						<td>${ bookingItem.getCategory() }</td>
						<td>${ bookingItem.getStartDate() }~ ${ bookingItem.getEndDate().substring(11) }</td>
						<td>${ bookingItem.getLocation() }</td>
						<td><a
							href="./bookingDetail?id=${ bookingItem.getBookingId() }"
							class="btn btn-warning">Details</a></td>
						<td>
							<form action="./home" method="POST">
								<button
									onclick="return confirm('Are you sure you want to delete?')"
									class="btn btn-danger" name="deleteBooking">Delete</button>
								<input type="hidden" name="bookingId"
									value="${ bookingItem.getBookingId() }" /> <input
									type="hidden" name="flag" value="deleteBooking" />
							</form>
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>


<%@ include file="footer.html"%>