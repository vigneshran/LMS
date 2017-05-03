package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminBorrowerServlet1
 */
@WebServlet("/deleteBorrower")
public class AdminBorrowerServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBorrowerServlet1() {
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
		
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		AdminService service = new AdminService();
		
		
		Long cardNo = Long.parseLong(request.getParameter("cardNo"));
		
		Borrower borrower = new Borrower();
		
		borrower.setCardNo(cardNo);
		Borrower wow = new Borrower();
		try {
			wow = service.returnBorrowerByPK(cardNo).get(0);
			service.deleteBorrower(borrower);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("deleteMessage", ""+wow.getBorrowerName()+"");
		RequestDispatcher rd1 = request.getRequestDispatcher("/adminupdateanddeleteborrowers.jsp");
		rd1.forward(request, response);
	}

}
