<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>


<div class="middle">
	<div class="panel-body">
		<div class="container">

			<div class="col-md-8">
				<h4>Student List</h4>
				<hr />
				<c:if test="${ error != null }">
					<p class="font-red">${ error }</p>
				</c:if>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Type</th>
							<th>ZID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Program</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="studentItem" items="${ students }"
							varStatus="counter">
							<c:if test="${counter.index mod 2 == 0 }">
								<tr class="warning">
							</c:if>
							<td>${ studentItem.getType() }</td>
							<td>${ studentItem.getzId() }</td>
							<td>${ studentItem.getFirstName() }</td>
							<td>${ studentItem.getLastName() }</td>
							<td>${ studentItem.getEmail() }</td>
							<td>${ studentItem.getProgram() }</td>
							<td><a
								href="./studentDetail?id=${ studentItem.getStudentId() }"
								class="btn btn-warning">Details</a></td>
							<td>
								<form action="./studentsList" method="POST">
									<button
										onclick="return confirm('It will delete related bookings as well.\nAre you sure you want to delete?')"
										class="btn btn-danger" name="deleteStudent">Delete</button>
									<input type="hidden" name="studentId"
										value="${ studentItem.getStudentId() }" /> <input
										type="hidden" name="flag" value="deleteStudent" />
								</form>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<%@ include file="footer.html"%>