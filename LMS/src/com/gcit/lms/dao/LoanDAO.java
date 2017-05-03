package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Loan;

public class LoanDAO extends BaseDAO{

	public LoanDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addLoan(Loan loan) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_loans VALUES (?,?,?,NOW(), CURDATE() + INTERVAL 7 DAY, NULL)", new Object[]{loan.getBookId(), loan.getBranchId(), loan.getCardNo()});
	}
	
	public void updateLoan(Loan loan) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_book_loans SET dateIn = NOW() WHERE bookId = ? AND cardNo = ? AND dateOut = ?", new Object[]{loan.getBookId(), loan.getCardNo(), loan.getDateOut1()});
	}
	
	public void updatDueDate(Loan loan, Integer days) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET dueDate = dueDate + INTERVAL ? DAY WHERE bookId = ? AND cardNo = ? AND branchId = ? AND dateOut = ?", new Object[]{days, loan.getBookId(), loan.getCardNo(), loan.getBranchId(), loan.getDateOut()});
	}
	
	public List<Loan> readLoansByBorrower(Borrower borrower) throws SQLException {
		return read("SELECT * FROM tbl_book_loans WHERE cardNo = ?", new Object[] {borrower.getCardNo()});
	}
	
	public List<Loan> readLoansDueByBorrower(Borrower borrower) throws SQLException {
		return read("SELECT * FROM tbl_book_loans WHERE cardNo = ? AND dateIn IS NULL", new Object[] {borrower.getCardNo()});
	}
	
	public List<Loan> readAllLoansDue() throws SQLException {
		return read("SELECT * FROM tbl_book_loans WHERE dateIn IS NULL", null);
	}
	
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Loan> loans = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		BorrowerDAO borrdao = new BorrowerDAO(conn);
		
		while(rs.next())	{
			Loan loan = new Loan();
			loan.setBookId(rs.getInt("bookId"));
			loan.setBranchId(rs.getInt("branchId"));
			loan.setCardNo(rs.getLong("cardNo"));
			loan.setDateOut(rs.getString("dateOut"));
			loan.setDueDate(rs.getString("dueDate"));
			loan.setDateIn(rs.getString("dateIn"));
			loan.setDateOut1(rs.getTimestamp("dateOut"));
			
			loans.add(loan);
		}
		return loans;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
