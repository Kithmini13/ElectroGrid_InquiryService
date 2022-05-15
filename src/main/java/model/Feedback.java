package model;

import java.sql.*;

public class Feedback {
		//A common method to connect to the DB
		private Connection connect() 
		{ 
				Connection con = null; 
				try
				{ 
					Class.forName("com.mysql.jdbc.Driver"); 
	 
					//Provide the correct details: DBServer/DBName, username, password 
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid_inquiry", "root", ""); 
				} 
				catch (Exception e) 
				{e.printStackTrace();
				} 
				return con; 
		}
		
		
	//Insert data to DB	
		public String insertFeedback(String Feedback_id,String name, String email, String rate, String phone, String comment, String Cus_id) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for inserting."; } 
				// create a prepared statement
				String query = " insert into feedback (`Feedback_id`,`Customer_name`,`email`,`rate`,`phone`,`feedback_comment`,`Cus_id`)"+ " values (?, ?, ?, ?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setString(1, Feedback_id); 
				preparedStmt.setString(2, name); 
				preparedStmt.setString(3, email); 
				preparedStmt.setInt(4, Integer.parseInt(rate)); 
				preparedStmt.setLong(5, Long.parseLong(phone));
				preparedStmt.setString(6, comment);
				preparedStmt.setString(7, Cus_id); 
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while inserting the feedback."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		
	//Read the data in DB	
		public String readFeedbacks() 
		{ 
			String output = ""; 
	 	try
	 	{ 
	 		Connection con = connect(); 
	 		if (con == null) 
	 		{return "Error while connecting to the database for reading."; } 
	 		// Prepare the html table to be displayed
	 		output = "<table border='1'><tr><th>Feedback ID</th><th>Customer Name</th><th>Customer Email</th>" +
	 				"<th>Rate</th>" +
	 				"<th>Phone Number</th>" +
	 				"<th>Feedback Commet</th>" +
	 				"<th>Customer ID</th>" +
	 				"<th>Update</th><th>Remove</th></tr>"; 
	 
	 		String query = "select * from feedback"; 
	 		Statement stmt = con.createStatement(); 
	 		ResultSet rs = stmt.executeQuery(query); 
	 		// iterate through the rows in the result set
	 		while (rs.next()) 
	 		{ 
	 			String Feedback_id = rs.getString("Feedback_id"); 
	 			String Customer_name = rs.getString("Customer_name"); 
	 			String email = rs.getString("email"); 
	 			String rate = Integer.toString(rs.getInt("rate"));  
	 			String phone = Long.toString(rs.getLong("phone"));
	 			String feedback_comment = rs.getString("feedback_comment");
	 			String Cus_id = rs.getString("Cus_id"); 
	 			// Add into the html table
	 			output += "<tr><td>" + Feedback_id + "</td>"; 
	 			output += "<td>" + Customer_name + "</td>"; 
	 			output += "<td>" + email + "</td>"; 
	 			output += "<td>" + rate + "</td>"; 
	 			output += "<td>" + phone + "</td>"; 
	 			output += "<td>" + feedback_comment + "</td>";
	 			output += "<td>" + Cus_id + "</td>";
	 			// buttons
	 			/*output += "</tr>";*/
	 			output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 					+ "<td><form method='post' action='Feedback-List.jsp'>"
	 					+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 					+ "<input name='Feedaback_id' type='hidden' value='" + Feedback_id + "'>" + "</form></td></tr>"; 
	 		} 
	 		con.close(); 
	 		// Complete the html table
	 		output += "</table>"; 
	 	} 
	 	catch (Exception e) 
	 	{ 
	 		output = "Error while reading the items."; 
	 		System.err.println(e.getMessage()); 
	 	} 
	 	return output; 
		}
		
		
//update the data in the DB
		public String updateFeedback(String customer_name, String email, String rate, String phone, String feedback_comment, String feedback_reply, String Cus_id, String Feedback_id) {
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for updating."; } 
				// create a prepared statement
				String query = "UPDATE feedback SET Customer_name=?,email=?,rate=?,phone=? ,feedback_comment=?, feedback_reply=?, Cus_id=? WHERE Feedback_id=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				preparedStmt.setString(1, customer_name); 
				preparedStmt.setString(2, email); 
				preparedStmt.setInt(3, Integer.parseInt(rate)); 
				preparedStmt.setLong(4, Long.parseLong(phone)); 
				preparedStmt.setString(5, feedback_comment); 
				preparedStmt.setString(6, feedback_reply);
				preparedStmt.setString(7, Cus_id);
				preparedStmt.setString(8, Feedback_id);
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Updated successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while updating the Feedback."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
		
		//Delete the data in DB	
				public String deleteFeedback(String Feedback_id,String Cus_id) {
					String output = "";
					try {
						Connection con = connect();
						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}
						// create a prepared statement
						String query = "delete from feedback where Feedback_id=? AND Cus_id=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values
						preparedStmt.setString(1, Feedback_id);
						preparedStmt.setString(2, Cus_id);
						// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Feedback Deleted successfully";
					} catch (Exception e) {
						output = "Error while deleting the from feedback table.";
						System.err.println(e.getMessage());
					}
					return output;
				}
		
		
		

}

