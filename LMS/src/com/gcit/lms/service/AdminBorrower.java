package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;


public class AdminBorrower {
	public List<Borrower> returnBorrowerByCardNo(Long cardNo) throws SQLException	{
		Connection conn = null;
		BorrowerDAO bdao = null;
		List<Borrower> borrowers = null;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BorrowerDAO(conn);
			borrowers = bdao.returnBorrowerByPK(cardNo);
			conn.commit();
			return borrowers;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return null;
	}
	
	public void incrementCopies(Integer branchId, Long cardNo, Integer bookId) throws SQLException {
		Connection conn = null;
		CopiesDAO cdao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			cdao = new CopiesDAO(conn);
			Copies copy = new Copies();
			copy.setBookId(bookId);
			copy.setBranchId(branchId);
			cdao.incrementCopies(copy);
			conn.commit();
		} catch(ClassNotFoundException | SQLException e)	{
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn!=null) conn.close();
		}
	}
	
	public void decrementCopies(Integer branchId, Long cardNo, Integer bookId) throws SQLException {
		Connection conn = null;
		CopiesDAO cdao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			cdao = new CopiesDAO(conn);
			Copies copy = new Copies();
			copy.setBookId(bookId);
			copy.setBranchId(branchId);
			cdao.decrementCopies(copy);
			conn.commit();
		} catch(ClassNotFoundException | SQLException e)	{
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn!=null) conn.close();
		}
	}
	
	public List<Book> returnBookObjectByName(String title) throws SQLException {
		Connection conn = null;
		BookDAO bdao = null;
		List<Book> bookByName = null ;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BookDAO(conn);
			bookByName = bdao.readBookByName(title);
			conn.commit();
			return bookByName;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return null;
	}
	
	public void updateLoan(Integer bookId, Long cardNo, java.sql.Timestamp dateOut) throws SQLException {
		Connection conn = null;
		LoanDAO ldao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			ldao = new LoanDAO(conn);
			Loan loan = new Loan();
			loan.setBookId(bookId);
			loan.setCardNo(cardNo);
			loan.setDateOut1(dateOut);
			ldao.updateLoan(loan);
			conn.commit();
		} catch(ClassNotFoundException | SQLException e)	{
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn!=null) conn.close();
		}
	}
	
	public void addLoan(Integer bookId, Integer branchId, Long cardNo) throws SQLException {
		Connection conn = null;
		LoanDAO ldao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			ldao = new LoanDAO(conn);
			Loan loan = new Loan();
			loan.setBookId(bookId);
			loan.setCardNo(cardNo);
			loan.setBranchId(branchId);
			ldao.addLoan(loan);
			conn.commit();
		} catch(ClassNotFoundException | SQLException e)	{
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn!=null) conn.close();
		}
	}
	
	public List returnLoansDueByBorrower(Long cardNo) throws SQLException {
		Connection conn = null;
		LoanDAO ldao = null;
		List<Loan> loans = new ArrayList<>();
		
		try {
			conn = ConnectionUtil.getConnection();
			ldao = new LoanDAO(conn);
			Loan loan = new Loan();
			Borrower borrower = new Borrower();
			borrower.setCardNo(cardNo);
			loans = ldao.readLoansDueByBorrower(borrower);
			conn.commit();
			return loans;
		} catch(ClassNotFoundException | SQLException e)	{
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn!=null) conn.close();
		}
		return null;
	}
	
	public List returnBooksByBranch(Integer branchId) throws SQLException {
		Connection conn = null;
		BranchDAO bdao = null;
		List<Book> books = new ArrayList<>();
		
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BranchDAO(conn);
			List<Branch> branch = bdao.readBranchById(branchId);
			books = branch.get(0).getBooks();
			conn.commit();
			return books;
		} catch(ClassNotFoundException | SQLException e)	{
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn!=null) conn.close();
		}
		return null;
	}
	
}
