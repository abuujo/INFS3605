<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ page import="service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>

<div class="middle">
	<div class="panel-body">



		<!-- BACK BUTTON (OPTION) -->
		<!-- <a href="#" onclick="window.history.back()" class="btn btn-primary" role="button">Back</a> -->


		<c:if test="${ !attempt && user == null }">
			<p>Something seems to have gone wrong. Please try again.</p>
		</c:if>
		<c:if test="${ !attempt && user != null }">
			<div class="col-sm-8">
				<h2>Profile</h2>
				<hr />
				<form action="./profile" method="POST">
					<div class="table-responsive">

						<table class="table table-hover table-striped">
							<tr>
								<td><b>Email</b></td>
								<td><input style="width: 100%" type="text" id="email"
									name="email" value="${ user.getEmail() }" /></td>
							</tr>
							<tr>
								<td><b>Password</b></td>
								<td><input style="width: 100%" type="password"
									id="password" name="password" value="${ user.getPassword() }" /></td>
							</tr>
							<tr>
								<td><b>First Name</b></td>
								<td><input style="width: 100%" type="text" id="firstName"
									name="firstName" value="${ user.getFirstName() }" /></td>
							</tr>
							<tr>
								<td><b>Last Name</b></td>
								<td><input style="width: 100%" type="text" id="lastName"
									name="lastName" value="${ user.getLastName() }" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<button
										onclick="return confirm('Are you sure you want to update?')"
										class="btn btn-warning" name="updateProfile">Update</button>
								</td>
							</tr>
						</table>
					</div>
					<input type="hidden" name="flag" value="update" />
				</form>
			</div>
		</c:if>

	</div>
</div>


<%@ include file="footer.html"%>