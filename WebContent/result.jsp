<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>




<div class="middle">
	<div class="panel-body">
		<!--  <div class="container">-->
		<div class="col-md-8">
			<h4>
				Test Results
				<c:if test="${ selected != null }">[${ selected }]</c:if>
				<div style="display: inline-block; width: 150px; margin-left: 3px;">
					<form action="./result" method="GET">
						<div class="form-group">
							<select class="form-control" name="selected"
								onchange="this.form.submit()">
								<option>Year/Semester</option>
								<c:forEach var="semYear" items="${ semYearList }">
									<option>${ semYear }</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<!--
						<div style="float: right;">
							<form action="./result" method="GET">
								<input type="submit" class="btn btn-default" name="filter" value="DEFAULT" />						  
								<input type="submit" class="btn btn-danger" name="filter" value="FL" />
								<input type="submit" class="btn btn-warning" name="filter" value="PS" />
								<input type="submit" class="btn btn-info" name="filter" value="CR" />
								<input type="submit" class="btn btn-success" name="filter" value="DN" />
								<input type="submit" class="btn btn-primary" name="filter" value="HD" />																																																					  
							</form>							
						</div>
						-->
			</h4>
			<hr />

			<table class="table table-bordered" style="max-width: 990px;">
				<thead style="color: white; background-color: #333333;">
					<tr>
						<th>zID</th>
						<th>Rank</th>
						<!-- <th>First Name</th>
								<th>Last Name</th>-->
						<th>AVG</th>
						<c:forEach var="course" items="${ courses }">
							<th>${ course }</th>
						</c:forEach>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="zId" items="${ avgs.keySet() }" varStatus="i">
						<tr>
							<td><a href="./studentDetail?id=${ ids.get(zId) }">${ zId }</a></td>
							<td><c:out value="${ i.index+1 }" /></td>
							<td><c:out value="${ avgs.get(zId) }" /></td>

							<c:set var="list" value="${ filtered.get(zId) }" />
							<c:forEach var="course" items="${ courses }">
								<td align="center"><c:forEach var="map" items="${ list }">
										<c:forEach var="code" items="${ map.keySet() }">
											<c:if test="${ course == code }">
												<c:if test="${ map.get(code) < 65 }">
													<font style="color: red"> ${ map.get(code) } </font>
												</c:if>
												<c:if test="${ map.get(code) >= 65 }">										
													${ map.get(code) }	
												</c:if>
											</c:if>
										</c:forEach>
									</c:forEach></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!--  </div>-->
	</div>
</div>


<%@ include file="footer.html"%>