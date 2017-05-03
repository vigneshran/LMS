package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Loan;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminOverrideServlet
 */
@WebServlet("/adminOverride")
public class AdminOverrideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOverrideServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));
		Long cardNo = Long.parseLong(request.getParameter("cardNo"));
		String dateOut = request.getParameter("dateOut");
		Timestamp timestamp = null;
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = dateFormat.parse(dateOut);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){//this generic but you can control another types of exception
			e.printStackTrace();
		}
		
		long retryDate = System.currentTimeMillis();

        int hours = 4;

        Timestamp original = new Timestamp(retryDate);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.HOUR, hours);
        Timestamp later = new Timestamp(cal.getTime().getTime());
        
        Date date = new Date();
        date.setTime(later.getTime());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date);

        System.out.println(formattedDate);
		
		Integer days = Integer.parseInt(request.getParameter("days"));
		
		Loan loan = new Loan();
		loan.setBookId(bookId);
		loan.setBranchId(branchId);
		loan.setCardNo(cardNo);
		loan.setDateOut(formattedDate);
		
		AdminService service = new AdminService();
		
		try {
			service.overrideDueDate(loan, days);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd1 = request.getRequestDispatcher("/adminoverride.jsp");
		rd1.forward(request, response);
		
		
	}

}
