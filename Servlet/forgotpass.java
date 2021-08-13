package Servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import model.EmailUtility;
import model.forgetpassword;
import db.DBUtils;

/**
 * Servlet implementation class forgotpass
 */
@WebServlet("/forgotpass")
public class forgotpass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String recipient=request.getParameter("recipient");
		String email="";
		String subject = "ForgetPassword";
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

//create StringBuffer size of AlphaNumericString
StringBuilder sb = new StringBuilder(10);
		 try {
			PreparedStatement ps=DBUtils.getPreparedStatement("select * from regUsers where Email='"+recipient+"'");
			ResultSet rs=ps.executeQuery();
			
		while(rs.next()) {
			
			email=rs.getString(4);
		}
		 } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	
		if(email.equals(recipient)) {
		 

for (int i = 0; i < 10; i++) {

// generate a random number between
// 0 to AlphaNumericString variable length
int index= (int)(AlphaNumericString.length()
     * Math.random());

// add Character one by one in end of sb
sb.append(AlphaNumericString
       .charAt(index));

		
}     

try {
	PreparedStatement ps1=DBUtils.getPreparedStatement("update regUsers set password='"+sb.toString()+"' where Email='"+recipient+"'");
	ps1.executeUpdate();
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

String content = "your new password is : "+sb.toString();	
String datacontent=sb.toString();
System.out.println(sb.toString()+"hellooooo");

String resultMessage = "";

try {
	EmailUtility eu=new EmailUtility();
	forgetpassword fp=new forgetpassword(recipient,subject,content);
			eu.sendEmail(fp);
			
    resultMessage = "The e-mail was sent successfully";
} catch (Exception ex) {
    ex.printStackTrace();
    resultMessage = "There were an error: " + ex.getMessage();
} finally {
    request.setAttribute("Message", resultMessage);
    getServletContext().getRequestDispatcher("/login.jsp").forward(
            request, response);
}




		}
		
		else {
			response.sendRedirect("forgetpass.jsp?error=Email is not validate");
			}
		
		
		
		 
	}
}
