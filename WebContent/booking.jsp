<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ page import="com.dhtmlx.demo.*"%>
<%@ page import="service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>


<div class="middle">
	<div class="panel-body">
		<c:if test="${ !attempt || !errors.isEmpty() }">
			<!-- Not add booking attempt, or attempted but errors, so show form -->
			<div class="col-md-8">
				<h4>Add a booking</h4>
				<hr />
				<c:if test="${ (errors != null) }">
					<div class="alert-danger">
						<ul class="alert-info-ul">
							<c:forEach var="error" items="${ errors }">
								<li>${ error }</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<form action="./booking" method="post">
					<div class="form-group">
						<label for="studentType">Student Type <span
							style="color: red">*</span></label> <a
							class="btn btn-primary btn-select btn-select-light"> <input
							type="hidden" class="btn-select-input" id="" name="studentType"
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
						<label for="zId">Student's zID <span style="color: red">*</span></label>
						<input class="form-control" type="text" name="zId"
							placeholder="ex) z1234567" required />
					</div>
					<div class="form-group">
						<label for="studentFirstName">Student's first name <span
							style="color: red">*</span></label> <input class="form-control"
							type="text" name="studentFirstName" required />
					</div>
					<div class="form-group">
						<label for="studentLastName">Student's last name <span
							style="color: red">*</span></label> <input class="form-control"
							type="text" name="studentLastName" required />
					</div>
					<div class="form-group">
						<label for="studentEmail">Student's E-mail <span
							style="color: red">*</span></label> <input class="form-control"
							type="text" name="studentEmail" required />
					</div>
					<div class="form-group">
						<label for="studentProgram">Student's program <span
							style="color: red">*</span></label> <a
							class="btn btn-primary btn-select btn-select-light"> <input
							type="hidden" class="btn-select-input" id=""
							name="studentProgram" value="" required /> <span
							class="btn-select-value">Please Select</span> <span
							class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
							<ul>
								<c:forEach var="program" items="${ progList }">
									<li>${ program }</li>
								</c:forEach>
							</ul>
						</a>
					</div>
					<div class="form-group">
						<label for="category">Category <span style="color: red">*</span></label>
						<a class="btn btn-primary btn-select btn-select-light"> <input
							type="hidden" class="btn-select-input" id="" name="category"
							value="" required /> <span class="btn-select-value">Please
								Select</span> <span
							class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
							<ul>
								<li>General</li>
								<li>Personal</li>
								<li>Academic Progress</li>
								<li>Academic Offenses</li>
								<li>Program Transfer/Deferment</li>
							</ul>
						</a>
					</div>

					<!-- IM A FUCKING GOD -->
					<c:if test="${booking != null}">
						<div class="form-group">
							<label for="startDate">Start date <span
								style="color: red">*</span></label> <input class="form-control"
								type="text" value="${ booking.getStartDate() }" name="startDate"
								id='date' required />
						</div>
						<div class="form-group">
							<label for="endDate">End date <span style="color: red">*</span></label>
							<input class="form-control" type="text"
								value="${ booking.getEndDate() }" name="endDate" id='date'
								required />
						</div>
					</c:if>
					<c:if test="${booking == null}">
						<div class="form-group">
							<label for="startDate">Start date <span
								style="color: red">*</span></label> <input class="form-control"
								type="text" name="startDate" id='date' required />
						</div>
						<div class="form-group">
							<label for="endDate">End date <span style="color: red">*</span></label>
							<input class="form-control" type="text" name="endDate"
								id='endDate' required />
						</div>
					</c:if>
					<div class="form-group">
						<label for="location">Location <span style="color: red">*</span></label>
						<input class="form-control" type="text" name="location" required />
					</div>
					<div class="form-group">
						<label for="status">Status <span style="color: red">*</span></label>
						<input class="form-control" type="text" name="status"
							value="Pending" />
					</div>
					<div class="form-group">
						<label for="priority">Priority <span style="color: red">*</span></label>
						<a class="btn btn-primary btn-select btn-select-light"> <input
							type="hidden" class="btn-select-input" id="" name="priority"
							value="" required /> <span class="btn-select-value">Please
								Select</span> <span
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
						</a>
					</div>
					<div class="form-group">
						<input onclick="this.form.submit();this.disabled=false;"
							class="btn btn-primary" type="submit" value="Add" />
					</div>
				</form>
			</div>
		</c:if>
		<c:if test="${ attempt && errors.isEmpty() }">
			<script type="text/javascript"> window.onload = confirmBooking; </script>
		</c:if>

	</div>
</div>


<%@ include file="footer.html"%>