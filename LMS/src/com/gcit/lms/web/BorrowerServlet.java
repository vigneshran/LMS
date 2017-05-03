package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Loan;
import com.gcit.lms.service.AdminBorrower;
import com.gcit.lms.service.AdminLibrarian;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet({ "/checkNumber", "/returnBook", "/checkOutBook" })
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet() {
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

		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());

		switch (reqUrl) {
		case "/checkNumber": {
			Long cardNo = Long.parseLong(request.getParameter("cardNo"));
			List<Borrower> borrower = null;
			AdminBorrower service = new AdminBorrower();
			try {
				borrower = service.returnBorrowerByCardNo(cardNo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (borrower.isEmpty()) {
				request.setAttribute("message", "Card number doesn't exist, dude!");
				RequestDispatcher rd = request.getRequestDispatcher("/borrower.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("borrower", borrower.get(0));
				RequestDispatcher rd1 = request.getRequestDispatcher("/borrowerPage.jsp");
				rd1.forward(request, response);
			}
			break;
		}
			
		case "/returnBook": {
			String book = request.getParameter("book");
			AdminBorrower service1 = new AdminBorrower();
			Book books = new Book();

			Long cardNo1 = Long.parseLong(request.getParameter("cardNo"));
			List<Borrower> borrower1 = null;
			try {
				borrower1 = service1.returnBorrowerByCardNo(cardNo1);
				books = service1.returnBookObjectByName(book).get(0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Timestamp dateOut = null;
			Integer branchId = null;

			try {
				List<Loan> loansDue = service1.returnLoansDueByBorrower(cardNo1);
				for (Loan l : loansDue) {
					if (l.getBookId() == books.getBookId()) {
						dateOut = l.getDateOut1();
						branchId = l.getBranchId();
						break;
					}
				}
				service1.updateLoan(books.getBookId(), cardNo1, dateOut);
				service1.incrementCopies(branchId, cardNo1, books.getBookId());
				borrower1 = service1.returnBorrowerByCardNo(cardNo1);
				request.setAttribute("borrower", borrower1.get(0));
				request.setAttribute("returnMessage", "dada");
				RequestDispatcher rd2 = request.getRequestDispatcher("/borrowerPage.jsp");
				rd2.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch bl
				e.printStackTrace();
			}
			break;
		}

		case "/checkOutBook": {
			Long cardNoCheck = Long.parseLong(request.getParameter("cardNo"));
			Integer branchIdCheck = Integer.parseInt(request.getParameter("branchId"));
			String bookCheck = request.getParameter("book");
			AdminBorrower serviceCheck = new AdminBorrower();
			List<Borrower> borrowerCheck = null;
			Integer bookId = null;
			try {
				List<Book> book = serviceCheck.returnBookObjectByName(bookCheck);
				bookId = book.get(0).getBookId();
				serviceCheck.addLoan(bookId, branchIdCheck, cardNoCheck);
				serviceCheck.decrementCopies(branchIdCheck, cardNoCheck, bookId);
				request.setAttribute("checkMessage", "dada");
				borrowerCheck = serviceCheck.returnBorrowerByCardNo(cardNoCheck);
			} catch (SQLException e) {
				// TODO Auto-generated catch bl
				e.printStackTrace();
			}
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			today.add(Calendar.DATE, 7);
			SimpleDateFormat formatter=new SimpleDateFormat("DD-MMM-yyyy"); 
			String currentDate = formatter.format(today.getTime());
			request.setAttribute("checkMessage", ""+currentDate+"");
			request.setAttribute("borrower", borrowerCheck.get(0));
			RequestDispatcher rd3 = request.getRequestDispatcher("/borrowerPage.jsp");
			rd3.forward(request, response);
			break;
		}
	}
		
	}
}
