package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Book;
import com.gcit.lms.service.*;

/**
 * Servlet implementation class AdministratorServlet1
 */
@WebServlet("/deleteBook")
public class AdministratorServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorServlet1() {
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
		
		AdminService service = new AdminService();

		Integer bookId1 = Integer.parseInt(request.getParameter("bookId"));
		Book book = null;
		try {
			book = service.returnBookObjectById(bookId1).get(0);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = book.getBookName();
		try {

			Book deleteBook = new Book();
			deleteBook.setBookId(bookId1);
			service.deleteBook(deleteBook);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("deleted!!");
		request.setAttribute("deleteMessage", ""+name+"");
		RequestDispatcher rd2 = request.getRequestDispatcher("/updateanddeletebooks.jsp");
		rd2.forward(request, response);
	}
}
