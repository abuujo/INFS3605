<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>

<!-- Used in verification.jsp -->
<c:set var="locked" value="${ locked }" scope="session" />
<div class="wrapper">
	<div class="left"></div>
	<div class="right">
		<div class="right_container">
			<img src="UNSW_logo_0.png" /> <br /> <br />

			<ul class="nav nav-tabs">
				<li
					class="<c:if test="${ activeTab != 'registerTab' }">active</c:if>"><a
					data-toggle="tab" href="#login">Login</a></li>
				<li
					class="<c:if test="${ activeTab == 'registerTab' }">active</c:if> style="
					display:none;"><a data-toggle="tab" href="#register">Register</a></li>
			</ul>

			<div class="tab-content">
				<div id="login"
					class="tab-pane fade in <c:if test="${ activeTab != 'registerTab' }">active</c:if>">
					<c:if test="${ error != null }">
						<p style="color: red">* ${ error }</p>
					</c:if>
					<c:if test="${ unlocked != null }">
						<p style="color: blue">* ${ unlocked }</p>
					</c:if>
					<form action="./login" method="post">
						<div class="form-group">
							<label for="email">Email</label> <input class="form-control"
								type="text" name="email" />
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input
								class="form-control" type="password" name="password" />
						</div>
						<div class="form-group">
							<input onclick="this.form.submit();this.disabled=true;"
								class="btn btn-primary" type="submit" value="Login" />
						</div>
					</form>
					<a href="${pageContext.request.contextPath}/passwordRecovery.jsp">Forgotten
						password?</a>
				</div>

				<div id="register"
					class="tab-pane fade in <c:if test="${ activeTab == 'registerTab' }">active</c:if>">
					<c:if test="${ !attempt || !errors.isEmpty() }">

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
								<label for="lastName">Last Name <span style="color: red">*</span></label>
								<input class="form-control" type="text" name="lastName" />
							</div>
							<div class="form-group">
								<label for="type">Type <span style="color: red">*</span></label>
								<select class="form-control" name="type" required>
									<option selected disabled>Select Type</option>
									<option>Coordinator</option>
									<option>Co-Operative Coordinator</option>
								</select>
							</div>
							<div class="form-group">
								<input onclick="this.form.submit();this.disabled=true;"
									class="btn btn-primary" type="submit" value="Register" />
							</div>
						</form>
					</c:if>
					<c:if test="${ attempt && errors.isEmpty() }">
						<script type="text/javascript"> window.onload = confirmRegistration; </script>
					</c:if>
				</div>

			</div>
		</div>
	</div>
</div>
</body>
</html>