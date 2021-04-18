<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>


<div class="middle">
	<div class="panel-body">
		<div class="container">
			<div class="col-sm-8">
				<!-- Set attribute -->
				<c:set var="bookingId" value="${ booking.getBookingId() }"
					scope="session" />

				<h2>
					Sponsors <a href="#" data-toggle="modal" data-target="#addSponsor"><span><i
							class="glyphicon glyphicon-plus-sign"></i></span></a>
				</h2>
				<hr />
				<!-- ADD SPONSOR -->
				<div id="addSponsor" class="modal fade" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Sponsor</h4>
							</div>
							<div class="modal-body">
								<form action="./sponsor" method="post">
									<div class="form-group">
										<label for="sponsorName">Sponsor Name</label> <input
											class="form-control" type="text" name="sponsorName"
											id="sponsorName" required />
									</div>
									<div class="form-group">
										<label for="description">Description</label>
										<textarea style="resize: vertical; min-height: 300px;"
											class="form-control" type="text" name="description" row="6"
											id="description" required></textarea>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<input onclick="this.form.submit();this.disabled=true;"
											class="btn btn-primary" type="submit" value="Save" />
									</div>
									<input type="hidden" name="flag" value="addSponsor" />

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<!-- LIST OF ATTENDEES -->
				<c:if test="${ sponsors != null}">

					<div class="table-responsive">
						<table class="table table-hover table-striped"
							style="table-layout: fixed;">
							<thead>
								<tr>
									<td><b>Sponsor Name</b></td>
									<td><b>Description</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sponsor" items="${ sponsors }">
									<tr>
										<form action="./sponsor" method="POST">
											<td>${ sponsor.getSponsorName() }</td>
											<td><textarea
													style="max-width: 250px; width: 100%; min-height: 100px;"
													type="text" name="description">${ sponsor.getDescription() }</textarea></td>
											<td><input type="submit"
												onclick="return confirm('Are you sure you want to update?')"
												class="btn btn-warning" name="flag" value="Update" /> <input
												type="submit"
												onclick="return confirm('Are you sure you want to delete?')"
												class="btn btn-danger" name="flag" value="Delete" /> <input
												type="hidden" name="sponsorId"
												value="${ sponsor.getSponsorId() }" /></td>
										</form>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>


<%@ include file="footer.html"%>