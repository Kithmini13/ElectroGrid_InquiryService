<%@page import="model.Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%
if (request.getParameter("Feedback_id") != null) 
{ 
	 Feedback feedbackObj = new Feedback(); 
	 String stsMsg = ""; 
	//Insert--------------------------
	if (request.getParameter("hidFeedback_idSave") == "") 
	 { 
		 stsMsg = feedbackObj.insertFeedback(request.getParameter("Feedback_id"), 
		 request.getParameter("name"), 
		 request.getParameter("email"), 
		 request.getParameter("rate"),
		 request.getParameter("phone"),
		 request.getParameter("comment"),
		 request.getParameter("Cus_id")); 
	 } 
	else//Update----------------------
	 { 
		 stsMsg = feedbackObj.updateFeedback(request.getParameter("hidFeedback_idSave"), 
		 request.getParameter("name"), 
		 request.getParameter("email"), 
		 request.getParameter("rate"),
		 request.getParameter("phone"),
		 request.getParameter("feedback_comment"),
		 request.getParameter("feedback_reply"),
		 request.getParameter("Cus_id")); 
	 } 
	 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidFeedback_idDelete") != null) 
{ 
	 Feedback feedbackObj = new Feedback(); 
	 String stsMsg = 
	 feedbackObj.deleteFeedback(request.getParameter("hidFeedback_idDelete"),
	                    request.getParameter("hidCus_idDelete")); 
	 session.setAttribute("statusMsg", stsMsg); 
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedbacks Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Feedback.js"></script>
</head>
<body> 
	<div class="container"><div class="row"><div class="col-6"> 
		<h1>Feedback Form</h1>
		<form id="formFeedback" name="formFeedback">
			 Customer Name: 
			 <input id="name" name="name" type="text" 
			 class="form-control form-control-sm">
			 <br> 
			 Customer Email Address: 
			 <input id="email" name="email" type="text" 
			 class="form-control form-control-sm">
			 <br> 
			 Rating Number: 
			 <input id="rate" name="rate" type="text" 
			 class="form-control form-control-sm">
			 <br> 
			 Customer Phone Number: 
			 <input id="phone" name="phone" type="text" 
			 class="form-control form-control-sm">
			 <br>
			 Feedback Comment: 
			 <input id="feedback_comment" name="feedback_comment" type="text" 
			 class="form-control form-control-sm">
			 <br>
			 Feedback Reply:
			 (for administrator use only.) 
			 <input id="feedback_reply" name="feedback_reply" type="text" 
			 class="form-control form-control-sm">
			 <br>
			 Customer ID: 
			 <input id="Cus_id" name="Cus_id" type="text" 
			 class="form-control form-control-sm">
			 <br>
		
			 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			 <input type="hidden" id="hidFeedback_idSave" name="hidFeedback_idSave" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divFeedbackGrid">
		 <%
		 Feedback feedbackObj = new Feedback(); 
		 out.print(feedbackObj.readFeedbacks());
		 %>
		</div>
	</div> </div> </div> 
</body>
</html>
