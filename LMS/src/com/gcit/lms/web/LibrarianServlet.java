package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.AdminLibrarian;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet("/pickBranch")
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianServlet() {
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
		String branchName = request.getParameter("branchName");  
		Branch branch = new Branch();
		AdminLibrarian service = new AdminLibrarian();
		try {
			branch = service.returnBranchObjectByName(branchName).get(0);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// HttpSession session =request.getSession();
		request.setAttribute("branch", branch);
		
		try {
            RequestDispatcher rd = request.getRequestDispatcher("./branchPage.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

		
	}

}
