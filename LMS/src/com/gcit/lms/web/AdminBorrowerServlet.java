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
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminBorrowerServlet
 */
@WebServlet({ "/addBorrower", "/updateBorrower"})
public class AdminBorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminBorrowerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		AdminService service = new AdminService();

		switch(reqUrl) {
		case "/addBorrower": {
			String borrowerName = request.getParameter("borrowerName");
			String borrowerAddress = request.getParameter("borrowerAddress");
			String borrowerPhone = request.getParameter("borrowerPhone");
			Borrower borrower = new Borrower();
			
			borrower.setBorrowerName(borrowerName);
			borrower.setBorrowerAddress(borrowerAddress);
			borrower.setBorrowerPhone(borrowerPhone);
			try {
				service.addBorrower(borrower);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("addMessage", ""+borrowerName+"");
			RequestDispatcher rd1 = request.getRequestDispatcher("/adminborrowerservices.jsp");
			rd1.forward(request, response);
			break;
		}
		
		case "/updateBorrower": {
			String borrowerName = request.getParameter("borrowerName");
			String borrowerAddress = request.getParameter("borrowerAddress");
			String borrowerPhone = request.getParameter("borrowerPhone");
			Long cardNo = Long.parseLong(request.getParameter("cardNo"));
			
			Borrower borrower = new Borrower();
			
			borrower.setBorrowerName(borrowerName);
			borrower.setBorrowerAddress(borrowerAddress);
			borrower.setBorrowerPhone(borrowerPhone);
			borrower.setCardNo(cardNo);
			try {
				service.updateBorrower(borrower);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("updateMessage", ""+borrowerName+"");
			RequestDispatcher rd1 = request.getRequestDispatcher("/adminupdateanddeleteborrowers.jsp");
			rd1.forward(request, response);
			break;
		}
		
		}
	}

	

}
