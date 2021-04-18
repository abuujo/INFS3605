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
			<c:if test="${ !attempt && student == null }">
				<p>Something seems to have gone wrong. Please try again.</p>
			</c:if>
			<c:if test="${ !attempt && student != null }">
				<div class="col-sm-8">
					<h2>
						Student Details
						<div style="float: right">
							<form action="./studentDetail" method="POST">
								<input type="hidden" name="flag" value="reportGenerate" /> <input
									type="hidden" name="studentId"
									value="${ student.getStudentId() }" />
								<div class="form-group">
									<input onclick="this.form.submit();this.disabled=false;"
										class="btn btn-primary" type="submit" value="GeneratePDF" />
								</div>
							</form>
						</div>
					</h2>
					<hr />
					<form action="./studentDetail" method="POST">
						<div class="table-responsive">
							<table class="table table-hover table-striped">
								<tr>
									<td>Student Type</td>
									<td>
										<div class="form-group">
											<a class="btn btn-default btn-select btn-select-light"> <input
												type="hidden" class="btn-select-input" id="" name="type"
												value="${ student.getType() }" required /> <span
												class="btn-select-value">${ student.getType() }</span> <span
												class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
												<ul>
													<li>Normal</li>
													<li>Co-op</li>
												</ul>
											</a>
										</div>
									</td>
								</tr>
								<tr>
									<td>Student zID</td>
									<td>${ student.getzId() }</td>
								</tr>
								<tr>
									<td>First name</td>
									<td><input style="width: 100%" type="text" id="firstName"
										name="firstName" value="${ student.getFirstName() }" /></td>
								</tr>
								<tr>
									<td>Last name</td>
									<td><input style="width: 100%" type="text" id="lastName"
										name="lastName" value="${ student.getLastName() }" /></td>
								</tr>
								<tr>
									<td>Email</td>
									<td><input style="width: 100%" type="text" id="email"
										name="email" value="${ student.getEmail() }" /></td>
								</tr>
								<tr>
									<td>Program</td>
									<td><a class="btn btn-default btn-select btn-select-light">
											<input type="hidden" class="btn-select-input" id=""
											name="program" value="${ student.getProgram() }" /> <span
											class="btn-select-value">${ student.getProgram() }</span> <span
											class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
											<ul>
												<c:forEach var="program" items="${ progList }">
													<li>${ program }</li>
												</c:forEach>
											</ul>
									</a></td>
								</tr>
								<tr>
									<td>Private Email</td>
									<td><input style="width: 100%" type="text" id="privEmail"
										name="privEmail" value="${ student.getPrivEmail() }" /></td>
								</tr>
								<tr>
									<td>Private Phone</td>
									<td><input style="width: 100%" type="text" id="privPhone"
										name="privPhone" value="${ student.getPrivPhone() }" /></td>
								</tr>
								<tr>
									<td>Work Phone</td>
									<td><input style="width: 100%" type="text" id="workPhone"
										name="workPhone" value="${ student.getWorkPhone() }" /></td>
								</tr>
								<tr>
									<td>Gender</td>
									<td><input style="width: 100%" type="text" id="gender"
										name="gender" value="${ student.getGender() }" /></td>
								</tr>
								<tr>
									<td>Address</td>
									<td><input style="width: 100%" type="text" id="address"
										name="address" value="${ student.getAddress() }" /></td>
								</tr>
								<tr>
									<td>Current WAM</td>
									<td><input style="width: 100%" type="text" id="currentWam"
										name="currentWam" value="${ student.getCurrentWam() }" /></td>
								</tr>
								<tr>
									<td>Year Enrolled</td>
									<td><input style="width: 100%" type="text"
										id="yearEnrolled" name="yearEnrolled"
										value="${ student.getYearEnrolled() }" /></td>
								</tr>
								<c:if test="${ student.getType() == 'Co-op' }">
									<tr>
										<td>IP1</td>
										<td><input style="width: 100%" type="text" name="ip1"
											value="${ student.getIp1() }" /></td>
									</tr>
									<tr>
										<td>IP2</td>
										<td><input style="width: 100%" type="text" name="ip2"
											value="${ student.getIp2() }" /></td>
									</tr>
									<tr>
										<td>IP3</td>
										<td><input style="width: 100%" type="text" name="ip3"
											value="${ student.getIp3() }" /></td>
									</tr>
								</c:if>
								<tr>
									<td></td>
									<td>
										<button style="width: 100%"
											onclick="return confirm('Are you sure you want to update?')"
											class="btn btn-warning" name="updateStudentDetail">Update</button>
									</td>
								</tr>
							</table>
						</div>
						<input type="hidden" name="flag" value="updateStudentDetail" /> <input
							type="hidden" name="studentId"
							value="${ student.getStudentId() }" /> <input type="hidden"
							name="id" value="${ student.getStudentId() }" />
					</form>
				</div>
				<c:if test="${ student.getType() == 'Co-op' }">
					<!-- STUDENT'S PREFERENCE -->
					<div class="col-sm-8">
						<h2>Preferences</h2>
						<hr />

						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#ip1-tab">IP1</a></li>
							<li><a data-toggle="tab" href="#ip2-tab">IP2</a></li>
							<li><a data-toggle="tab" href="#ip3-tab">IP3</a></li>
						</ul>
						<div class="tab-content">
							<div id="ip1-tab" class="tab-pane fade in active">
								<form action="./studentDetail" method="POST">
									<div class="table-responsive">
										<table class="table table-hover table-striped">
											<tr>
												<td>First</td>
												<td>
													<div class="form-group">
														<a class="btn btn-default btn-select btn-select-light">
															<input type="hidden" class="btn-select-input" id=""
															name="firstPreference"
															value="${ pref1.getFirstPrefName() }" required /> <span
															class="btn-select-value">${ pref1.getFirstPrefName() }</span>
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
															value="${ pref1.getSecondPrefName() }" required /> <span
															class="btn-select-value">${ pref1.getSecondPrefName() }</span>
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
															value="${ pref1.getThirdPrefName() }" required /> <span
															class="btn-select-value">${ pref1.getThirdPrefName() }</span>
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
															value="${ pref1.getFourthPrefName() }" required /> <span
															class="btn-select-value">${ pref1.getFourthPrefName() }</span>
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
															value="${ pref1.getFifthPrefName() }" required /> <span
															class="btn-select-value">${ pref1.getFifthPrefName() }</span>
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
															value="${ pref1.getSixthPrefName() }" required /> <span
															class="btn-select-value">${ pref1.getSixthPrefName() }</span>
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
												<td>
													<button style="width: 100%"
														onclick="return confirm('Are you sure you want to update?')"
														class="btn btn-warning" name="updatePreference">Update</button>
												</td>
											</tr>
										</table>
									</div>
									<input type="hidden" name="flag" value="updatePreference" /> <input
										type="hidden" name="studentId"
										value="${ student.getStudentId() }" /> <input type="hidden"
										name="id" value="${ student.getStudentId() }" /> <input
										type="hidden" name="ipLvl" value="IP1" />
								</form>
							</div>
							<div id="ip2-tab" class="tab-pane fade in">
								<form action="./studentDetail" method="POST">
									<div class="table-responsive">
										<table class="table table-hover table-striped">
											<tr>
												<td>First</td>
												<td>
													<div class="form-group">
														<a class="btn btn-default btn-select btn-select-light">
															<input type="hidden" class="btn-select-input" id=""
															name="firstPreference"
															value="${ pref2.getFirstPrefName() }" required /> <span
															class="btn-select-value">${ pref2.getFirstPrefName() }</span>
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
															value="${ pref2.getSecondPrefName() }" required /> <span
															class="btn-select-value">${ pref2.getSecondPrefName() }</span>
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
															value="${ pref2.getThirdPrefName() }" required /> <span
															class="btn-select-value">${ pref2.getThirdPrefName() }</span>
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
															value="${ pref2.getFourthPrefName() }" required /> <span
															class="btn-select-value">${ pref2.getFourthPrefName() }</span>
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
															value="${ pref2.getFifthPrefName() }" required /> <span
															class="btn-select-value">${ pref2.getFifthPrefName() }</span>
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
															value="${ pref2.getSixthPrefName() }" required /> <span
															class="btn-select-value">${ pref2.getSixthPrefName() }</span>
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
												<td>
													<button style="width: 100%"
														onclick="return confirm('Are you sure you want to update?')"
														class="btn btn-warning" name="updatePreference">Update</button>
												</td>
											</tr>
										</table>
									</div>
									<input type="hidden" name="flag" value="updatePreference" /> <input
										type="hidden" name="studentId"
										value="${ student.getStudentId() }" /> <input type="hidden"
										name="id" value="${ student.getStudentId() }" /> <input
										type="hidden" name="ipLvl" value="IP2" />
								</form>
							</div>
							<div id="ip3-tab" class="tab-pane fade in ">
								<form action="./studentDetail" method="POST">
									<div class="table-responsive">
										<table class="table table-hover table-striped">
											<tr>
												<td>First</td>
												<td>
													<div class="form-group">
														<a class="btn btn-default btn-select btn-select-light">
															<input type="hidden" class="btn-select-input" id=""
															name="firstPreference"
															value="${ pref3.getFirstPrefName() }" required /> <span
															class="btn-select-value">${ pref3.getFirstPrefName() }</span>
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
															value="${ pref3.getSecondPrefName() }" required /> <span
															class="btn-select-value">${ pref3.getSecondPrefName() }</span>
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
															value="${ pref3.getThirdPrefName() }" required /> <span
															class="btn-select-value">${ pref3.getThirdPrefName() }</span>
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
															value="${ pref3.getFourthPrefName() }" required /> <span
															class="btn-select-value">${ pref3.getFourthPrefName() }</span>
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
															value="${ pref3.getFifthPrefName() }" required /> <span
															class="btn-select-value">${ pref3.getFifthPrefName() }</span>
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
															value="${ pref3.getSixthPrefName() }" required /> <span
															class="btn-select-value">${ pref3.getSixthPrefName() }</span>
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
												<td>
													<button style="width: 100%"
														onclick="return confirm('Are you sure you want to update?')"
														class="btn btn-warning" name="updatePreference">Update</button>
												</td>
											</tr>
										</table>
									</div>
									<input type="hidden" name="flag" value="updatePreference" /> <input
										type="hidden" name="studentId"
										value="${ student.getStudentId() }" /> <input type="hidden"
										name="id" value="${ student.getStudentId() }" /> <input
										type="hidden" name="ipLvl" value="IP3" />
								</form>
							</div>
						</div>




					</div>
				</c:if>
				<!-- ADD COURSES -->
				<div class="col-sm-8">
					<h2>
						Courses <a href="#" data-toggle="modal" data-target="#addCourse"><span><i
								class="glyphicon glyphicon-plus-sign"></i></span></a>
					</h2>
					<hr />

					<div id="addCourse" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Add Course</h4>
								</div>
								<div class="modal-body">
									<form action="./studentDetail" method="post">
										<div class="form-group">
											<label for="courseCode">Course Code:</label> <a
												class="btn btn-default btn-select btn-select-light"> <input
												type="hidden" class="btn-select-input" id=""
												name="courseCode" value="" required /> <span
												class="btn-select-value">Please Select</span> <span
												class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
												<ul>
													<c:forEach var="course" items="${ courseList }">
														<li>${ course }</li>
													</c:forEach>
												</ul>
											</a>
										</div>
										<div class="form-group">
											<label for="courseCode">Enrolled Year/Semester:</label> <input
												class="form-control" type="text" name="semYear" id="semYear"
												placeholder="ex)15s1" required />
										</div>
										<div class="form-group">
											<label for="mark">Mark:</label> <input class="form-control"
												type="text" name="mark" id="mark" required />
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<input onclick="this.form.submit();this.disabled=true;"
												class="btn btn-primary" type="submit" value="Save" />
										</div>
										<input type="hidden" name="flag" value="addCourse" /> <input
											type="hidden" name="studentId"
											value="${ student.getStudentId() }" /> <input type="hidden"
											name="id" value="${ student.getStudentId() }" />
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-8">
					<!-- LIST OF ENROLMENT -->

					<c:if test="${ enrolments != null }">
						<div class="table-responsive">
							<table class="table table-hover table-striped"
								style="table-layout: fixed;">
								<thead>
									<tr>
										<td><b>Course Code</b></td>
										<td><b>Year/Semester</b></td>
										<td><b>Mark</b></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="enrolment" items="${ enrolments }">
										<tr>
											<td>${ enrolment.getCourseCode() }</td>
											<td>${ enrolment.getSemYear() }</td>
											<td>${ enrolment.getMark() }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				</div>
				<!-- ADD OFFENSES -->
				<div class="col-sm-8">
					<h2>
						Offenses <a href="#" data-toggle="modal" data-target="#addOffense"><span><i
								class="glyphicon glyphicon-plus-sign"></i></span></a>
					</h2>
					<hr />

					<div id="addOffense" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Add Course</h4>
								</div>
								<div class="modal-body">
									<form action="./studentDetail" method="post">
										<div class="form-group">
											<label for="courseCode">Offense Name:</label> <input
												class="form-control" type="text" name="offenseName"
												id="offenseName" required />
										</div>
										<div class="form-group">
											<label for="courseCode">Date Committed:</label> <input
												class="form-control" type="text" value=""
												name="dateCommitted" id='date' required />
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
											<input onclick="this.form.submit();this.disabled=true;"
												class="btn btn-primary" type="submit" value="Save" />
										</div>
										<input type="hidden" name="flag" value="addOffense" /> <input
											type="hidden" name="studentId"
											value="${ student.getStudentId() }" /> <input type="hidden"
											name="id" value="${ student.getStudentId() }" />
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-8">
					<!-- LIST OF OFFENSES -->

					<c:if test="${ offenses != null }">
						<div class="table-responsive">
							<table class="table table-hover table-striped"
								style="table-layout: fixed;">
								<thead>
									<tr>
										<td><b>Offense Name</b></td>
										<td><b>Date Committed</b></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="offense" items="${ offenses }">
										<tr>
											<td>${ offense.getOffenseName() }</td>
											<td>${ offense.getDateCommitted() }</td>
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