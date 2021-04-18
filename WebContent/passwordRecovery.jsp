<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>

<!-- Used in verification.jsp -->
<c:set var="locked" value="${ locked }" scope="session" />
<div class="wrapper">
	<div class="left"></div>
	<div class="right">
		<div class="right_container">
			<img src="UNSW_logo_0.png" /> <br /> <br />
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
				<h4>Password Recovery</h4>
				<p style="color: red;">[Please fill the information]</p>
				<form action="./recovery" method="POST">
					<div class="form-group">
						<label for="email">Email <span style="color: red">*</span></label>
						<input class="form-control" type="text" name="email" required />
					</div>
					<div class="form-group">
						<label for="firstName">First Name <span style="color: red">*</span></label>
						<input class="form-control" type="text" name="firstName" />
					</div>
					<div class="form-group">
						<label for="lastName">Last Name <span style="color: red">*</span></label>
						<input class="form-control" type="text" name="lastName" />
					</div>
					<div class="form-group">
						<label for="type">Type <span style="color: red">*</span></label> <a
							class="btn btn-default btn-select btn-select-light"> <input
							type="hidden" class="btn-select-input" id="" name="type" value=""
							required /> <span class="btn-select-value">Please Select</span>
							<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
							<ul>
								<li>Coordinator</li>
								<li>Co-Operative Coordinator</li>
							</ul>
						</a>
					</div>
					<div class="form-group">
						<input onclick="this.form.submit();this.disabled=true;"
							class="btn btn-primary" type="submit" value="Recovery" />
					</div>
				</form>
			</c:if>
			<c:if test="${ attempt && errors.isEmpty() }">
				<script type="text/javascript"> window.onload = confirmRecovery; </script>
			</c:if>
		</div>
	</div>
</div>
</div>
</body>
</html>