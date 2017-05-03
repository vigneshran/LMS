package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.*;
import com.gcit.lms.service.AdminLibrarian;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminstratorServlet
 */
@WebServlet({ "/addBook", "/updateBook", "/pageBookAdmin", "/searchBookAdmin" })
public class AdminstratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminstratorServlet() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		Book book = new Book();
		AdminService service = new AdminService();
		AdminLibrarian serviceLib = new AdminLibrarian();
		Boolean isAjax = false;
		switch (reqUrl) {
		case "/pageBookAdmin": {
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

		case "/searchBookAdmin": {
			String searchString = request.getParameter("searchString");
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			List<Book> books = null;
			try {
				Integer numberOfPages = 0;
				books = service.searchByBookName(searchString, pageNo);
				List<Book> booksFull = service.searchByBookName(searchString);
				Integer noOfAuthors = booksFull.size();
				if (noOfAuthors % 5 > 0) {
					numberOfPages = noOfAuthors / 5 + 1;
				} else {
					numberOfPages = noOfAuthors / 5;
				}
				StringBuffer strBuf = new StringBuffer();
				strBuf.append("<thead><tr><th>Book Title</th><th>Update</th><th>Delete</th></tr></thead>");
				strBuf.append("<tbody>");
				int i = 1;
				for (Book b : books) {
					strBuf.append("<tr><td>" + b.getBookName() + "</td>");
					strBuf.append(
							"<td><p><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#updateBookModal"
									+ i + "'role='button' href='adminupdatebooks.jsp?bookId=" + b.getBookId()
									+ "'> Update Book Now</button></p></td>");
					strBuf.append(
							"<td><p><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#deleteBookModal"
									+ i + "' role='button' href='admindeletebooks.jsp?bookId=" + b.getBookId()
									+ "'> Delete Book Now</button></p></td>");
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (isAjax == false) {
			RequestDispatcher rd = request.getRequestDispatcher("/updateanddeletebooks.jsp?pageNo=1");
			rd.forward(request, response);
		}
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
		case "/addBook": {

			String[] selectGenres = request.getParameterValues("genre");
			String[] selectAuthors = request.getParameterValues("author");
			String selectPublihser = request.getParameter("publisher");
			String selectBook = request.getParameter("newBook");

			Book book = new Book();
			book.setBookName(selectBook);
			try {
				service.addBook(book);
				book = service.returnBookObjectByNAme(selectBook).get(0);
				if (selectPublihser.isEmpty()) {
					Publisher nullPub = new Publisher();
					service.addPublisher(nullPub, book);
				}
				List<Publisher> publisher = service.returnPublisherObjectByName(selectPublihser);
				service.addPublisher(publisher.get(0), book);
				authorif:
				if (selectAuthors == null) {
					break authorif;
				} else {
					service.deleteBookAuthors(book);
					for (String s : selectAuthors) {
						List<Author> authors = new ArrayList<>();
						authors = service.returnAuthorObjectByName(s);
						service.addBookAuthors(book, authors.get(0));
					}
				}
				
				genreif:
				if (selectGenres == null) {
					break genreif;
				} else {
					service.deleteBookGenres(book);
					for (String s : selectGenres) {
						List<Genre> genres = new ArrayList<>();
						genres = service.returnGenreObjectByName(s);
						service.addBookGenres(book, genres.get(0));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("addMessage", ""+selectBook+"");
			System.out.println("added!");
			RequestDispatcher rd = request.getRequestDispatcher("/adminbookservices.jsp");
			rd.forward(request, response);
			break;

		}

		case "/updateBook": {

			String[] selectGenres = request.getParameterValues("genre");
			String[] selectAuthors = request.getParameterValues("author");
			String selectPublihser = request.getParameter("publisher");
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			String updatedBook = request.getParameter("updatedBook");

			try {

				Book originalBook = new Book();
				originalBook = service.returnBookObjectById(bookId).get(0);

				Book updated = new Book();
				updated.setBookName(updatedBook);
				updated.setBookId(originalBook.getBookId());
				service.updateBook(updated);

				if (selectPublihser.isEmpty()) {
					Publisher nullPub = new Publisher();
					service.updatePublisher(nullPub, updated);
				}
				List<Publisher> publisher = service.returnPublisherObjectByName(selectPublihser);
				service.updatePublisher(publisher.get(0), updated);
				if (selectAuthors == null) {
					service.deleteBookAuthors(updated);
				} else {
					service.deleteBookAuthors(updated);
					for (String s : selectAuthors) {
						List<Author> authors = new ArrayList<>();
						authors = service.returnAuthorObjectByName(s);
						service.addBookAuthors(updated, authors.get(0));
					}
				}

				if (selectGenres == null) {
					service.deleteBookGenres(updated);
				} else {
					service.deleteBookGenres(updated);
					for (String s : selectGenres) {
						List<Genre> genres = new ArrayList<>();
						genres = service.returnGenreObjectByName(s);
						service.addBookGenres(updated, genres.get(0));
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("updated!");
			request.setAttribute("updateMessage", ""+updatedBook+"");
			RequestDispatcher rd1 = request.getRequestDispatcher("/updateanddeletebooks.jsp");
			rd1.forward(request, response);
		}
		break;
		
		case "lol" : {
			System.out.println("You're drunk!");
		}

		}
	}

}
