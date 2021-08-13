package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DataAccess;

/**
 * Servlet implementation class AdminEditProfile
 */
@WebServlet("/AdminEditProfile")
public class AdminEditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session= request.getSession();
		int Uid=Integer.parseInt(session.getAttribute("Id").toString());
		String Name=request.getParameter("Name");
	    String password=request.getParameter("password");
	     String Email=request.getParameter("Email");
	     int DOBDay=Integer.parseInt(request.getParameter("DOBDay"));
	     String DOBMonth=request.getParameter("DOBMonth");
	     int DOBYear=Integer.parseInt(request.getParameter("DOBYear"));
	     String Gender=request.getParameter("Gender");
	     String state=request.getParameter("state");
	     String contact=request.getParameter("contact");
	    
	     System.out.println("UPDATES SUCCESSFULLY"+ session.getAttribute("Id"));
	       
	       System.out.println(Name + "I Am In PatientEditProfile Servlet");
	      // regUser r=new regUser(Name,password, Email, DOBDay, DOBMonth, DOBYear, Gender, state, contact);
	       DataAccess da=new DataAccess();
	       try {
			da.UpdateAdmin(Name, Email, DOBDay, DOBMonth, DOBYear, Gender, state, contact,Uid,password);
			response.sendRedirect("AdminProfile.jsp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	       System.out.println("UPDATES SUCCESSFULLY");
	       
	       
	       
	}

		// TODO Auto-generated method stub
	}


