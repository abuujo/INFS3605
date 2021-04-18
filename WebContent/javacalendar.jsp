
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="dto.User,com.dhtmlx.planner.*,com.dhtmlx.planner.data.*,java.util.Calendar"%>
<%@ page import="com.dhtmlx.demo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>

<div class="planner" id="planner"
	style="margin-top: 60px; display: flex; position: relative;">



	<%= getPlanner(request) %></div>

<%!
		
       	String getPlanner (HttpServletRequest request) throws Exception {
			User user = (User) request.getSession().getAttribute("user");
			EventsManager.user = user;
			Calendar rightNow = Calendar.getInstance();
			int year = rightNow.get(Calendar.YEAR);
			int month = rightNow.get(Calendar.MONTH);
			int day = rightNow.get(Calendar.DAY_OF_MONTH);
			
	        DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
	   
	        s.setWidth(950);
	        s.setHeight(500);
	        s.setInitialDate(year, month, day);
	        s.load("events.jsp", DHXDataFormat.JSON);
	        s.data.dataprocessor.setURL("events.jsp");
	     	return s.render();
  		
     }
	%>


</div>

<%@ include file="footer.html"%>


