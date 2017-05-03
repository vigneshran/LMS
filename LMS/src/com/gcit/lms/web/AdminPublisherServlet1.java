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
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminPublisherServlet1
 */
@WebServlet("/deletePublisher")
public class AdminPublisherServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPublisherServlet1() {
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
		AdminService service = new AdminService();
		


		Publisher deletePublisher = new Publisher();
		Integer pubId = Integer.parseInt(request.getParameter("publisherId"));
		deletePublisher.setPublisherId(pubId);
		Publisher publisher = null;
		try {
			Author originalAuthor = new Author();
			publisher = service.returnPublisherObjectById(pubId).get(0);
			service.deletePublisher(deletePublisher);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("deleteMessage", publisher.getPublisherName());
		RequestDispatcher rd2 = request.getRequestDispatcher("/adminupdateanddeletepublishers.jsp");
		rd2.forward(request, response);
		
	}

}
