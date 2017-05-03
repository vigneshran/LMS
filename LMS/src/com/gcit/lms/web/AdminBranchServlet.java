package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminBranchServlet
 */
@WebServlet({"/addBranch", "/updateBranch", "/deleteBranch"})
public class AdminBranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBranchServlet() {
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

		switch (reqUrl) {
		case "/addBranch": {
			String branchName = request.getParameter("newBranch");
			String branchAddress = request.getParameter("newAddress");
			Branch branch = new Branch();
			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			try {
				service.addBranch(branch);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("addMessage", ""+branchName+"");
			RequestDispatcher rd1 = request.getRequestDispatcher("/adminbranchservices.jsp");
			rd1.forward(request, response);
			break;
			
		}
		
		case "/updateBranch": {
			String branchName = request.getParameter("updatedBranchName");
			String branchAddress = request.getParameter("updatedBranchAddress");
			Branch updatedBranch = new Branch();
			updatedBranch.setBranchName(branchName);
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			updatedBranch.setBranchAddress(branchAddress);
			updatedBranch.setBranchId(branchId);
			try {
				service.updateBranch(updatedBranch);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("updateMessage", ""+branchName+"");
			RequestDispatcher rd2 = request.getRequestDispatcher("/adminupdateanddeletebranches.jsp");
			rd2.forward(request, response);
			break;
			
		}
		
		case "/deleteBranch": {

			Branch deleteBranch = new Branch();
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			deleteBranch.setBranchId(branchId);
			Branch branch = null;
			try {
				branch = service.returnBranchObjectById(branchId).get(0);
				Author originalAuthor = new Author();
				service.deleteBranch(deleteBranch);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("deleteMessage", ""+branch.getBranchName()+"");
			RequestDispatcher rd2 = request.getRequestDispatcher("/adminupdateanddeletebranches.jsp");
			rd2.forward(request, response);
			break;
			
		}
		}
	}

}
