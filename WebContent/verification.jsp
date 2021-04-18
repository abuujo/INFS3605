<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ page import="service.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>

<c:set var="email" value="${ sessionScope.locked.getEmail() }"
	scope="session" />
<c:set var="uCode" value="${ sessionScope.locked.getUnlockCode() }"
	scope="session" />

<div class="page">
	<div class="middle">
		<div class="panel-body">
			<div class="container">
				<div class="col-md-8">
					<h2>Unlock Your Account</h2>
					<hr />
					<c:if test="${ error != null }">
						<div class="alert-danger">
							<ul class="alert-info-ul">
								<li>${ error }</li>
							</ul>
						</div>
					</c:if>

					<form action="./verify" method="POST">
						<div class="form-group">
							<label for="unlockCode">Unlock Code<span
								style="color: red;">*</span></label> <input class="form-control"
								type="text" name="unlockCode" required />
						</div>
						<div class="form-group">
							<input class="btn btn-primary" type="submit" value="Unlock" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="footer.html"%>