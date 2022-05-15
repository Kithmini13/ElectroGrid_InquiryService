package com;

import model.Feedback;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML

import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;




@Path("/Feedbacks") 
public class FeedbackService
{
	Feedback feedbackObj = new Feedback();
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML)
    public String readFeedbacks() 
	{
		return feedbackObj.readFeedbacks();
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFeedback(@FormParam("Feedback_id") String Feedback_id, 
	 @FormParam("Customer_name") String Customer_name, 
	 @FormParam("email") String email, 
	 @FormParam("rate") String rate, 
	 @FormParam("phone") String phone,
	 @FormParam("feedback_comment") String feedback_comment,
	 @FormParam("Cus_id") String Cus_id)
	{ 
	 String output = feedbackObj.insertFeedback(Feedback_id,Customer_name, email, rate, phone, feedback_comment, Cus_id); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFeedback(String FeedbackData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject feedbackObject = new JsonParser().parse(FeedbackData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String Feedback_id = feedbackObject.get("Feedback_id").getAsString();
	 String Customer_name = feedbackObject.get("Customer_name").getAsString(); 
	 String email = feedbackObject.get("email").getAsString(); 
	 String rate = feedbackObject.get("rate").getAsString(); 
	 String phone = feedbackObject.get("phone").getAsString(); 
	 String feedback_comment = feedbackObject.get("feedback_comment").getAsString(); 
	 String feedback_reply= feedbackObject.get("feedback_reply").getAsString();
	 String Cus_id= feedbackObject.get("Cus_id").getAsString();
	String output = feedbackObj.updateFeedback(Customer_name, email, rate, phone, feedback_comment , feedback_reply, Cus_id,Feedback_id); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFeedback(String feedbackData)
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(feedbackData, "",Parser.xmlParser());
		 
		//Read the value from the element <itemID>
		String Feedback_id = doc.select("Feedback_id").text(); 
		String Cus_id = doc.select("Cus_id").text();
		String output = feedbackObj.deleteFeedback(Feedback_id,Cus_id); 
		return output; 
		}


}



