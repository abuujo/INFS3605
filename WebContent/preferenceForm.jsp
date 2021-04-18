<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Enabling responsive features -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>UGCIS - Four Leaves Technology</title>

<link rel="icon" href="flt.ico" type="image/x-icon" />
<link rel="shortcut icon" href="flt.ico" type="image/x-icon" />

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<!-- Contextual CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login_style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/head_foot_style.css" />

<!--  datetimepicker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<!-- Footer -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/social_media.css">

<!-- Google fonts -->
<link href='https://fonts.googleapis.com/css?family=Arial'
	rel='stylesheet' type='text/css' />

<!-- jQuery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script type="text/javascript"
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/custom.js"></script>


<!-- CALENDAR -->


</head>
<body>

	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@ page import="dao.*"%>
	<%@ page import="dto.*"%>
	<%@ page import="service.*"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<div class="middle">
		<div class="panel-body">
			<div class="container">

				<c:if test="${ !errors.isEmpty() }">
					<div class="col-md-8">
						<h1>Student preference form for : ${ IPLevel }</h1>
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

						<form action="./preferenceForm" method="post">
							<div class="col-sm-12">
								<h2>
									Preferences for Student:
									<c:out value="${ student.getzId() }" />
								</h2>
								<hr />
								<div class="table-responsive">
									<table class="table table-hover table-striped">
										<tr>
											<td>First</td>
											<td>
												<div class="form-group">
													<a class="btn btn-default btn-select btn-select-light">
														<input type="hidden" class="btn-select-input" id=""
														name="firstPreference"
														value="${ preference.getFirstPrefName() }" required /> <span
														class="btn-select-value">${ preference.getFirstPrefName() }</span>
														<span
														class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
														<ul>
															<c:forEach var="sponsor" items="${ sponsors }">
																<li>${ sponsor }</li>
															</c:forEach>
														</ul>
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td>Second</td>
											<td>
												<div class="form-group">
													<a class="btn btn-default btn-select btn-select-light">
														<input type="hidden" class="btn-select-input" id=""
														name="secondPreference"
														value="${ preference.getSecondPrefName() }" required /> <span
														class="btn-select-value">${ preference.getSecondPrefName() }</span>
														<span
														class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
														<ul>
															<c:forEach var="sponsor" items="${ sponsors }">
																<li>${ sponsor }</li>
															</c:forEach>
														</ul>
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td>Third</td>
											<td>
												<div class="form-group">
													<a class="btn btn-default btn-select btn-select-light">
														<input type="hidden" class="btn-select-input" id=""
														name="thirdPreference"
														value="${ preference.getThirdPrefName() }" required /> <span
														class="btn-select-value">${ preference.getThirdPrefName() }</span>
														<span
														class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
														<ul>
															<c:forEach var="sponsor" items="${ sponsors }">
																<li>${ sponsor }</li>
															</c:forEach>
														</ul>
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td>Fourth</td>
											<td>
												<div class="form-group">
													<a class="btn btn-default btn-select btn-select-light">
														<input type="hidden" class="btn-select-input" id=""
														name="fourthPreference"
														value="${ preference.getFourthPrefName() }" required /> <span
														class="btn-select-value">${ preference.getFourthPrefName() }</span>
														<span
														class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
														<ul>
															<c:forEach var="sponsor" items="${ sponsors }">
																<li>${ sponsor }</li>
															</c:forEach>
														</ul>
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td>Fifth</td>
											<td>
												<div class="form-group">
													<a class="btn btn-default btn-select btn-select-light">
														<input type="hidden" class="btn-select-input" id=""
														name="fifthPreference"
														value="${ preference.getFifthPrefName() }" required /> <span
														class="btn-select-value">${ preference.getFifthPrefName() }</span>
														<span
														class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
														<ul>
															<c:forEach var="sponsor" items="${ sponsors }">
																<li>${ sponsor }</li>
															</c:forEach>
														</ul>
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td>Sixth</td>
											<td>
												<div class="form-group">
													<a class="btn btn-default btn-select btn-select-light">
														<input type="hidden" class="btn-select-input" id=""
														name="sixthPreference"
														value="${ preference.getSixthPrefName() }" required /> <span
														class="btn-select-value">${ preference.getSixthPrefName() }</span>
														<span
														class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
														<ul>
															<c:forEach var="sponsor" items="${ sponsors }">
																<li>${ sponsor }</li>
															</c:forEach>
														</ul>
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td></td>
											<td><input type="hidden" name="studentId"
												value="${ student.getStudentId() }" /> <input type="hidden"
												name="ip" value="${ IPLevel }" />
												<button style="width: 100%"
													onclick="return confirm('Are you sure you want to submit these as your preferences?')"
													class="btn btn-warning" name="submitPreference">Submit
													Preferences</button></td>
										</tr>
									</table>
								</div>
						</form>
					</div>
				</c:if>
				<c:if test="${ errors.isEmpty() }">
					<h2>That's it, thank you!</h2>

				</c:if>
			</div>
		</div>
	</div>

	<%@ include file="footer.html"%>