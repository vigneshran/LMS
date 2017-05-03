package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.AdminLibrarian;

/**
 * Servlet implementation class updateBranchDetails
 */
@WebServlet("/updateBranchDetails")
public class updateBranchDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBranchDetails() {
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
		AdminLibrarian service = new AdminLibrarian();
		String branchName = request.getParameter("branchName");
		String updatedBranchName = request.getParameter("updatedBranchName");
		String branchAddress = request.getParameter("updatedBranchAddress");
	
		Branch branch = new Branch();
		try {
			branch = service.returnBranchObjectByName(branchName).get(0);
			System.out.println(branch.getBranchAddress());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Integer branchId = branch.getBranchId();
		
		Branch updatedBranch = new Branch();
		
		updatedBranch.setBranchId(branchId);
		if(updatedBranchName.isEmpty())
			updatedBranch.setBranchName(null);
		else updatedBranch.setBranchName(updatedBranchName);
		if (branchAddress.isEmpty())
			updatedBranch.setBranchAddress(null);
		else updatedBranch.setBranchAddress(branchAddress);
		
		try {
			service.updateBranch(updatedBranch);
			request.setAttribute("editMessage", "dada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("branch", updatedBranch);
		request.setAttribute("branchId", updatedBranch.getBranchId());
		
		RequestDispatcher rd = request.getRequestDispatcher("/branchPage.jsp?branchId="+updatedBranch.getBranchId());
		rd.forward(request, response);

	}

}
