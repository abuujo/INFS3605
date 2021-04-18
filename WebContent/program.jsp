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
				<h4>Program List</h4>
				<hr />
				<c:if test="${ error != null }">
					<p style="color: red">${ error }</p>
				</c:if>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Program Code</th>
							<th>Program Name</th>
							<th>Handbook Year</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="program" items="${ programs }" varStatus="counter">
							<c:if test="${counter.index mod 2 == 0 }">
								<tr class="warning">
							</c:if>
							<td>${ program.getProgramCode() }</td>
							<td>${ program.getProgramName() }</td>
							<td>${ program.getHandbookYear() }</td>
							<!-- <td><a href="./studentDetail?id=${ studentItem.getStudentId() }" class="btn btn-warning">Details</a></td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<%@ include file="footer.html"%>