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
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminPublisherServlet
 */
@WebServlet({"/addPublisher", "/updatePublisher"})
public class AdminPublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPublisherServlet() {
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
		case "/addPublisher": {
			String pubName = request.getParameter("newPub");
			String pubAddress = request.getParameter("newAddress");
			String pubPhone = request.getParameter("newPhone");
			
			Publisher publisher = new Publisher();
			
			publisher.setPublisherName(pubName);
			publisher.setPublisherAddress(pubAddress);
			publisher.setPublisherPhone(pubPhone);
			
			try {
				service.addPublisher(publisher);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("addMessage", ""+pubName+"");
			RequestDispatcher rd1 = request.getRequestDispatcher("/adminpublisherservices.jsp");
			rd1.forward(request, response);
			break;
			
		}
		
		case "/updatePublisher": {
			String pubName = request.getParameter("publisherName");
			String pubAddress = request.getParameter("publisherAddress");
			String pubPhone = request.getParameter("publisherPhone");
			Integer pubId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher pub = new Publisher();
			pub.setPublisherId(pubId);
			pub.setPublisherName(pubName);
			pub.setPublisherAddress(pubAddress);
			pub.setPublisherPhone(pubPhone);

			try {
				service.updatePublisher(pub);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("updateMessage", ""+pubName+"");
			RequestDispatcher rd2 = request.getRequestDispatcher("/adminupdateanddeletepublishers.jsp");
			rd2.forward(request, response);
			break;
		}
		}
	}

}
