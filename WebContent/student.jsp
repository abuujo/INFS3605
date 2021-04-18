<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ page import="service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>


<div class="middle">
	<div class="panel-body">
		<c:if test="${ !attempt || !errors.isEmpty() }">
			<div class="col-md-8">
				<h4>Add a Student</h4>
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

				<form action="./student" method="post">
					<div class="form-group">
						<label for="type">Student Type <span style="color: red">*</span></label>
						<a class="btn btn-primary btn-select btn-select-light"> <input
							type="hidden" class="btn-select-input" id="" name="type" value=""
							required /> <span class="btn-select-value">Please Select</span>
							<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
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
						<label for="studentGender">Student's gender (M/F/O) <span
							style="color: red">*</span></label> <a
							class="btn btn-primary btn-select btn-select-light"> <input
							type="hidden" class="btn-select-input" id="" name="studentGender"
							value="" required /> <span class="btn-select-value">Please
								Select</span> <span
							class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
							<ul>
								<li>Male</li>
								<li>Female</li>
								<li>Other</li>
							</ul>
						</a>
					</div>
					<div class="form-group">
						<label for="studentCurrentWam">Student's current WAM <span
							style="color: red">*</span></label> <input class="form-control"
							type="text" name="studentCurrentWam" required />
					</div>
					<div class="form-group">
						<label for="studentYearEnrolled">Student's enrollment year
							<span style="color: red">*</span>
						</label> <input class="form-control" type="text"
							name="studentYearEnrolled" required />
					</div>
					<div class="form-group">
						<label for="studentAddress">Student's address<span
							style="color: red"></span></label> <input class="form-control"
							type="text" name="studentAddress" required />
					</div>
					<div class="form-group">
						<label for="studentPrivPhone">Student's private phone
							number<span style="color: red"></span>
						</label> <input class="form-control" type="text" name="studentPrivPhone"
							required />
					</div>
					<div class="form-group">
						<label for="studentWorkPhone">Student's work phone<span
							style="color: red"></span></label> <input class="form-control"
							type="text" name="studentWorkPhone" required />
					</div>
					<div class="form-group">
						<label for="studentPrivEmail">Student's private email <span
							style="color: red"></span></label> <input class="form-control"
							type="text" name="studentPrivEmail" required />
					</div>
					<div class="form-group">
						<input onclick="this.form.submit();this.disabled=false;"
							class="btn btn-primary" type="submit" value="Add" />
					</div>
				</form>
			</div>
		</c:if>
		<c:if test="${ attempt && errors.isEmpty() }">
			<script type="text/javascript"> window.onload = confirmStudent; </script>
		</c:if>
	</div>
</div>

<%@ include file="footer.html"%>