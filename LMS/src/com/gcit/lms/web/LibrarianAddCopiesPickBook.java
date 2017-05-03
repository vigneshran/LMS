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
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.AdminLibrarian;

/**
 * Servlet implementation class LibrarianAddCopiesPickBook
 */
@WebServlet("/pickBook")
public class LibrarianAddCopiesPickBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianAddCopiesPickBook() {
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
		String title = request.getParameter("bookTitle");
		
		Book book = new Book();
		AdminLibrarian service = new AdminLibrarian();
		try {
			book = service.returnBookObjectByName(title).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String branchName = request.getParameter("branchName");
		Branch branch = new Branch();
		try {
			branch = service.returnBranchObjectByName(branchName).get(0);
			System.out.println(branch.getBranchAddress());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("branch", branch);
		request.setAttribute("book", book);
		
		try {
            RequestDispatcher rd = request.getRequestDispatcher("./addcopiesbookwithbranch.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
