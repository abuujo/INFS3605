<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>


<div class="middle">
	<div class="panel-body">
		<div class="container">
			<c:if test="${ !attempt || !errors.isEmpty() }">
				<div class="col-sm-8">
					<h4>Preferences</h4>
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
					<form action="./emailPreferences" method="post">
						<div class="form-group">
							<label for="ipLevel">Industry Placement level<span
								style="color: red">*</span></label> <a
								class="btn btn-primary btn-select btn-select-light"> <input
								type="hidden" class="btn-select-input" id="" name="ipType"
								value="" required /> <span class="btn-select-value">Please
									Select</span> <span
								class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
								<ul>
									<li>IP1</li>
									<li>IP2</li>
									<li>IPE3</li>
								</ul>
							</a>
						</div>
						<label for="note">Information for students</label>
						<textarea style="resize: vertical; min-height: 300px;"
							class="form-control" type="text" name="details" rows="5"
							id="note"></textarea>
						<div class="form-group">
							<input onclick="this.form.submit();this.disabled=false;"
								class="btn btn-primary" type="submit" value="Add" />
						</div>
					</form>
				</div>
				<c:if test="${ attempt && errors.isEmpty() }">
					<script type="text/javascript"> window.onload = confirmPreferences; </script>
				</c:if>
			</c:if>
		</div>
	</div>
</div>


<%@ include file="footer.html"%>