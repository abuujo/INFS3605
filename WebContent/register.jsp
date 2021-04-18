<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ page import="service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>

<!--
	@TODO:
	- should change form names to lowercase-hyphen-concat format instead of camelCase
	- confirm password field
	- align center? looks kinda bad aligned-left currently
-->
<div class="page">
	<div class="middle">
		<div class="panel-body">
			<div class="container">

				<div class="col-md-8">
					<c:if test="${ !attempt || !errors.isEmpty() }">
						<!-- Not registration attempt, or attempted but errors, so show form -->
						<h4>Registration</h4>
						<hr />
						<div class="col-md-8">

							<c:if test="${ (errors != null) }">
								<div class="alert-danger">
									<ul class="alert-info-ul">
										<c:forEach var="error" items="${ errors }">
											<li>${ error }</li>
										</c:forEach>
									</ul>
								</div>
							</c:if>

							<form action="./register" method="post">
								<div class="form-group">
									<label for="email">Email <span style="color: red">*</span></label>
									<input class="form-control" type="text" name="email" required />
								</div>
								<div class="form-group">
									<label for="password">Password <span style="color: red">*</span></label>
									<input class="form-control" type="password" name="password"
										required />
								</div>
								<div class="form-group">
									<label for="firstName">First Name <span
										style="color: red">*</span></label> <input class="form-control"
										type="text" name="firstName" />
								</div>
								<div class="form-group">
									<label for="lastName">Last Name <span
										style="color: red">*</span></label> <input class="form-control"
										type="text" name="lastName" />
								</div>
								<div class="form-group">
									<label for="type">Type <span style="color: red">*</span></label>
									<a class="btn btn-default btn-select btn-select-light"> <input
										type="hidden" class="btn-select-input" id="" name="type"
										value="" required /> <span class="btn-select-value">Please
											Select</span> <span
										class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
										<ul>
											<li>Coordinator</li>
											<li>Co-Operative Coordinator</li>
										</ul>
									</a>
								</div>
								<div class="form-group">
									<input onclick="this.form.submit();this.disabled=true;"
										class="btn btn-primary" type="submit" value="Register" />
								</div>
							</form>
						</div>
					</c:if>
					<c:if test="${ attempt && errors.isEmpty() }">
						<script type="text/javascript"> window.onload = confirmRegistration; </script>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.html"%>