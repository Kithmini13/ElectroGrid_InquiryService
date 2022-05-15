$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
	
		// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide(); 
	
	// Form validation-------------------
	var status = validateFeedbackForm(); 
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 } 
	// If valid------------------------
	var type = ($("#hidFeedback_idSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "FeedbackAPI", 
		 type : type, 
		 data : $("#formFeedback").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onFeedbackSaveComplete(response.responseText, status); 
		 } 
	 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidFeedback_idSave").val($(this).data("Feedback_id")); 
	 $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#email").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#rate").val($(this).closest("tr").find('td:eq(2)').text()); 
     $("#phone").val($(this).closest("tr").find('td:eq(3)').text()); 
     $("#comment").val($(this).closest("tr").find('td:eq(4)').text()); 
	 $("#Cus_id").val($(this).closest("tr").find('td:eq(5)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
	 $.ajax( 
	 { 
		 url : "FeedbackAPI", 
		 type : "DELETE", 
		 data : "Feedback_id=" + $(this).data("Feedback_id"),
		 dataType : "text", 
		 complete : function(response, status) 
	 { 
	 	onFeedbackDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validateFeedbackForm() 
{ 
// NAME
if ($("#name").val().trim() == "") 
 { 
 return "Insert your name."; 
 } 
// EMAIL
if ($("#email").val().trim() == "") 
 { 
 return "Insert your email."; 
 } 
// RATE-------------------------------
if ($("#rate").val().trim() == "") 
 { 
 return "Insert rate."; 
 } 
// PHONE
if ($("#phone").val().trim() == "") 
 { 
 return "Insert phone."; 
 }
//COMMENT------------------------
if ($("#comment").val().trim() == "") 
 { 
 return "Insert pyur feedback comment."; 
 } 
//CSUTOMER ID------------------------
if ($("#Cus_id").val().trim() == "") 
 { 
 return "Insert Customer ID."; 
 } 
return true; 
}

function onFeedbackSaveComplete(response, status) 
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
		 $("#alertSuccess").text("Successfully saved."); 
		 $("#alertSuccess").show(); 
		 $("#divFeedbacksGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
		 } 
	 } else if (status == "error") 
		 { 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show(); 
		 } else
			 { 
				 $("#alertError").text("Unknown error while saving.."); 
				 $("#alertError").show(); 
			 } 
	 $("#hidFeedback_idSave").val(""); 
	 $("#formFeedback")[0].reset(); 
}


function onFeedbackDeleteComplete(response, status) 
{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
		 $("#alertSuccess").text("Successfully deleted."); 
		 $("#alertSuccess").show(); 
		 $("#divFeedbacksGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
		 $("#alertError").text("Error while deleting."); 
		 $("#alertError").show(); 
	 } else
	 { 
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 } 
}




