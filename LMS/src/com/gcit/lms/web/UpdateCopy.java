package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Copies;
import com.gcit.lms.service.AdminLibrarian;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class UpdateCopy
 */
@WebServlet({ "/editCopy", "/addCopy", "/pageBook", "/searchBook" })
public class UpdateCopy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCopy() {
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
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		Book book = new Book();
		AdminService service = new AdminService();
		AdminLibrarian serviceLib = new AdminLibrarian();
		Boolean isAjax = false;
		switch (reqUrl) {
		case "/pageBook": {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			List<Book> books = null;
			try {
				books = service.readAllBooksWithPageNo(pageNo);
				request.setAttribute("books", books);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}

		case "/searchBook": {
			String searchString = request.getParameter("searchString");
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			List<Book> books = null;
			try {
				Integer numberOfPages = 0;

				books = service.searchByBookName(searchString, pageNo);
				List<Book> booksFull = service.searchByBookName(searchString);
				Integer noOfBooks = booksFull.size();
				if (noOfBooks % 5 > 0) {
					numberOfPages = noOfBooks / 5 + 1;
				} else {
					numberOfPages = noOfBooks / 5;
				}
				StringBuffer strBuf = new StringBuffer();
				strBuf.append(
						"<thead><tr><th>Book Title</th><th>Copies Available Now</th><th>Action</th></tr></thead>");
				strBuf.append("<tbody>");

				for (Book b : books) {
					strBuf.append("<tr><td>" + b.getBookName() + "</td>");
					if (serviceLib.doesBookExist(b.getBookId(), branchId)) {
						Integer copy = serviceLib.getCopies(b.getBookId(), branchId).get(0).getNoOfCOpies();
						strBuf.append("<form action='editCopy' method='post'>");
						strBuf.append("<td><input type='text' name='updatedCopies' value='" + copy + "'></td>");
						strBuf.append("<input type='hidden' name='branchId'value='" + branchId + "'>");
						strBuf.append("<input type='hidden' name='bookId' value='" + b.getBookId() + "'>");
						strBuf.append("<td><button class='btn btn-primary' type='submit'>Update Copies Now</button></td>");
						strBuf.append("</form>");
					} else {
						strBuf.append("<form action='addCopy' method='post'>");
						strBuf.append("<td><input type='text' name='addedCopies' value=0></td>");
						strBuf.append("<input type='hidden' name='branchId' value='" + branchId + "'>");
						strBuf.append("<input type='hidden' name='bookId' value='" + b.getBookId() + "'>");
						strBuf.append("<td><button class='btn btn-primary' type='submit'>Add Copies Now</button></td>");
						strBuf.append("</form>");
						strBuf.append(" <% } %>");
						strBuf.append("</tr>");
					}
				}
				strBuf.append("</tbody>");
				StringBuffer strBug1 = new StringBuffer();

				for (int i = 1; i <= numberOfPages; i++) {
					strBug1.append("<li><a onclick = 'setPageNo(" + i + ")'>" + i + "</a></li>");
				}
				String data1 = strBug1.toString();
				String data2 = strBuf.toString();
				String data = data1 + '\n' + data2;
				response.getWriter().write(data);
				isAjax = true;

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
		}
		if (isAjax == false) {
			RequestDispatcher rd = request.getRequestDispatcher("/librarianaddcopies.jsp?pageNo=1");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		Integer branchId = Integer.parseInt(request.getParameter("branchId"));

		Book book = new Book();
		AdminLibrarian service = new AdminLibrarian();

		switch (reqUrl) {
		case "/addCopy": {
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Integer copies = Integer.parseInt(request.getParameter("addedCopies"));
			addCopy(bookId, branchId, copies, service);
			request.setAttribute("messageAdd", "dada");
			break;
		}
		case "/editCopy": {
			Integer bookId1 = Integer.parseInt(request.getParameter("bookId"));
			Integer copies1 = Integer.parseInt(request.getParameter("updatedCopies"));
			updateCopy(bookId1, branchId, copies1, service);
			request.setAttribute("messageUpdate", "dada");
			break;
		}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/librarianaddcopies.jsp?pageNo=1");
		rd.forward(request, response);
	}

	private void updateCopy(Integer bookId, Integer branchId, Integer copies, AdminLibrarian service) {

		try {
			service.updateCopies(bookId, branchId, copies);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addCopy(Integer bookId, Integer branchId, Integer copies, AdminLibrarian service) {
		try {

			service.addCopies(bookId, branchId, copies);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
