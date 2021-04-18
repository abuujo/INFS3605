<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ page import="service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>


<div class="middle">
	<div class="panel-body">
		<div class="container">

			<c:if test="${ booking == null }">
				<p>Something seems to have gone wrong. Please try again.</p>
			</c:if>
			<c:if test="${ booking != null }">
				<!-- Set attribute -->
				<c:set var="bookingId" value="${ booking.getBookingId() }"
					scope="session" />

				<div class="col-sm-10">
					<h2>
						Booking Details
						<div style="float: right;">
							<form action="./bookingDetail" method="POST">
								<input type="hidden" name="flag" value="sendEmail" /> <input
									type="hidden" name="bookingId"
									value="${ booking.getBookingId() }" /> <input type="hidden"
									name="id" value="${ booking.getBookingId() }" /> <input
									type="hidden" name="studentId" value="${ studentId }" />
								<div class="form-group">
									<button
										onclick="return confirm('Are you sure you want to send notes to students?')"
										class="btn btn-primary" type="submit">Send Notes</button>
								</div>
							</form>
						</div>
					</h2>
					<hr />
					<form action="./bookingDetail" method="POST">
						<div class="table-responsive">

							<table class="table table-hover table-striped">
								<tr>
									<td><b>Student zID</b></td>
									<td><b><a
											href="${pageContext.request.contextPath}/studentDetail?id=${ studentId }">${ booking.getzId() }</a></b></td>
								</tr>
								<tr>
									<td><b>Category</b></td>
									<td><a class="btn btn-default btn-select btn-select-light">
											<input type="hidden" class="btn-select-input" id=""
											name="category" value="${ booking.getCategory() }" /> <span
											class="btn-select-value">${ booking.getCategory() }</span> <span
											class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
											<ul>
												<li>General</li>
												<li>Personal</li>
												<li>Academic Progress</li>
												<li>Academic Offenses</li>
												<li>Program Transfer/Deferment</li>
											</ul>
									</a></td>
								</tr>
								<tr>
									<td><b>Date</b></td>
									<td>${ booking.getStartDate() }~ ${ booking.getEndDate().substring(11) }</td>
								</tr>
								<tr>
									<td><b>Location</b></td>
									<td><input style="width: 100%" type="text" id="location"
										name="location" value="${ booking.getLocation() }" /></td>
								</tr>
								<tr>
									<td><b>Status</b></td>
									<td><a class="btn btn-default btn-select btn-select-light">
											<input type="hidden" class="btn-select-input" id=""
											name="status" value="${ booking.getStatus() }" /> <span
											class="btn-select-value">${ booking.getStatus() }</span> <span
											class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
											<ul>
												<li>Pending</li>
												<li>Ongoing</li>
												<li>Finished</li>
											</ul>
									</a></td>
								</tr>
								<tr>
									<td><b>Priority</b></td>
									<td><a class="btn btn-default btn-select btn-select-light">
											<input type="hidden" class="btn-select-input" id=""
											name="priority" value="${ booking.getPriority() }" /> <span
											class="btn-select-value">${ booking.getPriority() }</span> <span
											class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
											<ul>
												<li>1</li>
												<li>2</li>
												<li>3</li>
												<li>4</li>
												<li>5</li>
												<li>6</li>
												<li>7</li>
												<li>8</li>
												<li>9</li>
											</ul>
									</a></td>
								</tr>
								<tr>
									<td></td>
									<td>
										<button style="width: 100%"
											onclick="return confirm('Are you sure you want to update?')"
											class="btn btn-warning" name="updateBooking">Update</button>
									</td>
								</tr>
							</table>
							<input type="hidden" name="flag" value="updateBooking" /> <input
								type="hidden" name="bookingId"
								value="${ booking.getBookingId() }" /> <input type="hidden"
								name="id" value="${ booking.getBookingId() }" />
						</div>
					</form>
				</div>

				<div class="col-sm-10">
					<!-- Set attribute -->
					<c:set var="bookingId" value="${ booking.getBookingId() }"
						scope="session" />

					<h2>
						Notes <a href="#" data-toggle="modal" data-target="#myModal"><span><i
								class="glyphicon glyphicon-plus-sign"></i></span></a>
					</h2>
					<hr />
					<!-- ADD NOTE -->
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Add Notes</h4>
								</div>
								<div class="modal-body">
									<form action="./bookingDetail" method="post">
										<div class="form-group">
											<label for="note">Note:</label>
											<textarea style="resize: vertical; min-height: 300px;"
												class="form-control" type="text" name="note" rows="5"
												id="note"></textarea>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<input onclick="this.form.submit();this.disabled=true;"
												class="btn btn-primary" type="submit" value="Save" />
										</div>
										<input type="hidden" name="flag" value="addNote" /> <input
											type="hidden" name="bookingId"
											value="${ booking.getBookingId() }" /> <input type="hidden"
											name="id" value="${ booking.getBookingId() }" />
									</form>
								</div>
							</div>
						</div>
					</div>


					<!-- LIST OF NOTES -->
					<c:if test="${ notes != null}">
						<!-- Set attribute -->
						<c:set var="bookingId" value="${ booking.getBookingId() }"
							scope="session" />


						<div class="table-responsive">
							<table class="table table-hover table-striped"
								style="table-layout: fixed;">
								<thead>
									<tr>
										<td><b>Created / Last Modified</b></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="noteItem" items="${ notes }">

										<tr>
											<form action="./bookingDetail" method="POST">
												<td>${ noteItem.getDateCreated() }</td>
												<td><textarea
														style="max-width: 250px; width: 100%; min-height: 100px"
														type="text" name="note">${ noteItem.getTexts() }</textarea>
												</td>
												<td><input type="submit"
													onclick="return confirm('Are you sure you want to update?')"
													class="btn btn-warning" name="flag" value="Update" /> <input
													type="submit"
													onclick="return confirm('Are you sure you want to delete?')"
													class="btn btn-danger" name="flag" value="Delete" /> <input
													type="hidden" name="noteId"
													value="${ noteItem.getNoteId() }" /> <input type="hidden"
													name="bookingId" value="${ booking.getBookingId() }" /> <input
													type="hidden" name="id" value="${ booking.getBookingId() }" />
												</td>
											</form>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				</div>
				<div class="col-sm-10">
					<!-- Set attribute -->
					<c:set var="bookingId" value="${ booking.getBookingId() }"
						scope="session" />

					<h2>
						Additional Attendees <a href="#" data-toggle="modal"
							data-target="#addAttendee"><span><i
								class="glyphicon glyphicon-plus-sign"></i></span></a>
					</h2>
					<hr />
					<!-- ADD Attendee -->
					<div id="addAttendee" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Add Notes</h4>
								</div>
								<div class="modal-body">
									<form action="./bookingDetail" method="post">
										<div class="form-group">
											<label for="type">Student Type <span
												style="color: red">*</span></label> <a
												class="btn btn-primary btn-select btn-select-light"> <input
												type="hidden" class="btn-select-input" id="" name="type"
												value="" required /> <span class="btn-select-value">Please
													Select</span> <span
												class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
												<ul>
													<li>Normal</li>
													<li>Co-op</li>
												</ul>
											</a>
										</div>
										<div class="form-group">
											<label for="zID">Student's zID:</label> <input
												class="form-control" type="text" name="zID" id="zID"
												required />
										</div>
										<div class="form-group">
											<label for="email">Student's email:</label> <input
												class="form-control" type="text" name="email" id="email"
												required />
										</div>
										<div class="form-group">
											<label for="firstName">First Name:</label> <input
												class="form-control" type="text" name="firstName"
												id="firstName" required />
										</div>
										<div class="form-group">
											<label for="lastName">Last Name:</label> <input
												class="form-control" type="text" name="lastName"
												id="lastName" required />
										</div>
										<div class="form-group">
											<label for="program">Student's Program</label> <a
												class="btn btn-default btn-select btn-select-light"> <input
												type="hidden" class="btn-select-input" id="" name="program"
												value="" required /> <span class="btn-select-value">Please
													Select</span> <span
												class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
												<ul>
													<c:forEach var="program" items="${ progList }">
														<li>${ program }</li>
													</c:forEach>
												</ul>
											</a>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<input onclick="this.form.submit();this.disabled=true;"
												class="btn btn-primary" type="submit" value="Save" />
										</div>
										<input type="hidden" name="flag" value="addAttendee" /> <input
											type="hidden" name="bookingId"
											value="${ booking.getBookingId() }" /> <input type="hidden"
											name="id" value="${ booking.getBookingId() }" />
									</form>
								</div>
							</div>
						</div>
					</div>

					<!-- LIST OF ATTENDEES -->
					<c:if test="${ attendees != null}">
						<!-- Set attribute -->
						<c:set var="bookingId" value="${ booking.getBookingId() }"
							scope="session" />


						<div class="table-responsive">
							<table class="table table-hover table-striped"
								style="table-layout: fixed;">
								<thead>
									<tr>
										<td><b>Type</b></td>
										<td><b>zID</b></td>
										<td><b>Email</b></td>
										<td><b>First Name</b></td>
										<td><b>Last Name</b></td>
										<td><b>Program</b></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="student" items="${ attendees }">

										<tr>
											<form action="./bookingDetail" method="POST">
												<td>${ student.getType() }</td>
												<td><b><a
														href="${pageContext.request.contextPath}/studentDetail?id=${ student.getStudentId() }">${ student.getzId() }</a></b></td>
												<td style="word-wrap: break-word;">${ student.getEmail() }</td>
												<td>${ student.getFirstName() }</td>
												<td>${ student.getLastName() }</td>
												<td>${ student.getProgram() }</td>
												<td><input type="submit"
													onclick="return confirm('Are you sure you want to delete?')"
													class="btn btn-danger" name="attendeeFlag" value="Delete" />
												</td> <input type="hidden" name="bookingId"
													value="${ booking.getBookingId() }" /> <input
													type="hidden" name="studentId"
													value="${ student.getStudentId() }" /> <input
													type="hidden" name="id" value="${ booking.getBookingId() }" />
											</form>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
</div>


<%@ include file="footer.html"%>