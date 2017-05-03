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
import com.gcit.lms.service.*;

/**
 * Servlet implementation class AdminAuthorServlet
 */


@WebServlet({ "/addAuthor", "/updateAuthor", "/deleteAuthor", "/pageAuthor", "/searchAuthor" })
public class AdminAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAuthorServlet() {
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
		AdminService service = new AdminService();
		Boolean isAjax = false;
		switch (reqUrl) {
		case "/pageAuthor": {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			List<Author> authors = null;
			try {
				authors = service.readAllAuthorsWithPageNo(pageNo);
				request.setAttribute("authors", authors);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}

		case "/searchAuthor": {
			String searchString = request.getParameter("searchString");
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			List<Author> authors = null;
			try {
				Integer numberOfPages = 0;
			
				
				authors = service.searchByAuthorName(searchString, pageNo);
				List<Author> authorsFull = service.searchByAuthorName(searchString);
				Integer noOfAuthors = authorsFull.size();
				if (noOfAuthors % 5 > 0) {
					numberOfPages = noOfAuthors / 5 + 1;
				} else {
					numberOfPages = noOfAuthors / 5;
				}
				StringBuffer strBuf = new StringBuffer();
				strBuf.append("<thead><tr><th>Author Name</th><th>Update</th><th>Delete</th></tr></thead>");
				strBuf.append("<tbody>");
				int i = 1;
				for (Author b : authors) {
					strBuf.append("<tr><td>" + b.getAuthorName() + "</td>");
					strBuf.append(
							"<td><p><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#updateAuthorModal"
									+ i + "'role='button' href='adminupdateauthors.jsp?authorId=" + b.getAuthorId()
									+ "'> Update Author Now</button></p></td>");
					strBuf.append(
							"<td><p><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#deleteAuthorModal"
									+ i + "' role='button' href='admindeleteauthors.jsp?authorId=" + b.getAuthorId()
									+ "'> Delete Author Now</button></p></td>");
					strBuf.append("</tr>");
					i++;
				}
				strBuf.append("</tbody>");
				
				StringBuffer strBug1 = new StringBuffer();
				for ( i = 1; i <= numberOfPages; i++) {
					strBug1.append("<li><a onclick = 'setPageNo("+i+")'>"+i+"</a></li>");
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
			RequestDispatcher rd2 = request.getRequestDispatcher("/adminupdateanddeleteauthors.jsp?pageNo=1");
			rd2.forward(request, response);
		}
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

		switch (reqUrl) {
		case "/addAuthor": {
			String authorName = request.getParameter("newAuthor");
			Author author = new Author();
			author.setAuthorName(authorName);
			try {
				service.addAuthor(author);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("addMessage", "" + authorName + "");
			RequestDispatcher rd1 = request.getRequestDispatcher("/adminauthorservices.jsp");
			rd1.forward(request, response);

			break;

		}

		case "/updateAuthor": {
			String authorName = request.getParameter("newAuthor");
			Author updatedAuthor = new Author();
			updatedAuthor.setAuthorName(authorName);
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			updatedAuthor.setAuthorId(authorId);
			try {
				Author originalAuthor = new Author();
				service.updateAuthor(updatedAuthor);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("updateMessage", authorName);
			RequestDispatcher rd2 = request.getRequestDispatcher("/adminupdateanddeleteauthors.jsp?pageNo=1 ");
			rd2.forward(request, response);

			break;

		}

		case "/deleteAuthor": {

			Author deleteAuthor = new Author();
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			deleteAuthor.setAuthorId(authorId);
			Author author = null;
			try {
				author = service.returnAuthorObjectById(authorId).get(0);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Author originalAuthor = new Author();
				service.deleteAuthor(deleteAuthor);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("deleteMessage", "" + author.getAuthorName() + "");
			RequestDispatcher rd2 = request.getRequestDispatcher("/adminupdateanddeleteauthors.jsp?pageNo=1");
			rd2.forward(request, response);

			break;

		}

		}

	}

}
