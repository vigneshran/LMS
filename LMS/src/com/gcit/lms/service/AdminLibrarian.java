package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.CopiesDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Copies;

public class AdminLibrarian {
	public List<Branch> returnBranchObjectByName(String branchName) throws SQLException	{
		Connection conn = null;
		BranchDAO bdao = null;
		List<Branch> branchByName = null ;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BranchDAO(conn);
			branchByName = bdao.readBranchByName(branchName);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return branchByName;
	}
	
	public List<Branch> readAllBranches() throws SQLException	{
		Connection conn = null;
		BranchDAO bdao = null;
		List<Branch> branchByName = null ;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BranchDAO(conn);
			conn.commit();
			return bdao.readAllBranches();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return null;
	}
	
	public List<Book> readAllBooks() throws SQLException	{
		Connection conn = null;
		BookDAO bdao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BookDAO(conn);
			conn.commit();
			return bdao.readAllBooks();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return null;
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
	
	public void addCopies(Integer bookId, Integer branchId, Integer noOfCopies) throws SQLException	{
		Connection conn = null;
		CopiesDAO cdao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			cdao = new CopiesDAO(conn);
			Copies copy = new Copies();
			copy.setBranchId(branchId);
			copy.setNoOfCOpies(noOfCopies);
			copy.setBookId(bookId);
			cdao.addCopies(copy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}		
	}
	
	public void updateCopies(Integer bookId, Integer branchId, Integer noOfCopies) throws SQLException	{
		Connection conn = null;
		CopiesDAO cdao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			cdao = new CopiesDAO(conn);
			Copies copy = new Copies();
			copy.setBranchId(branchId);
			copy.setNoOfCOpies(noOfCopies);
			copy.setBookId(bookId);
			cdao.updateCopies(copy);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}		
	}
	
	public boolean doesBookExist(Integer bookId, Integer branchId) throws SQLException	{
		Connection conn = null;
		CopiesDAO cdao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			cdao = new CopiesDAO(conn);
			conn.commit();
			return cdao.doesBookExist(branchId, bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
			
		} finally {
			if (conn != null) conn.close();
		}
		return false;
	}
	
	public List<Copies> getCopies(Integer bookId, Integer branchId) throws SQLException	{
		Connection conn = null;
		CopiesDAO cdao = null;
		List<Copies> copies = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			cdao = new CopiesDAO(conn);
			Copies copy = new Copies();
			copy.setBranchId(branchId);
			copies = cdao.readCopyByBranchIdAndBookId(branchId, bookId);
			conn.commit();
			return copies;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return null;
	}
	
	public void updateBranch(Branch branch) throws SQLException	{
		Connection conn = null;
		BranchDAO bdao = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BranchDAO(conn);
			bdao.updateBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}		
	}
	
	public List<Branch> returnBranchObjectById(Integer branchId) throws SQLException	{
		Connection conn = null;
		BranchDAO bdao = null;
		List<Branch> branchByName = null ;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BranchDAO(conn);
			branchByName = bdao.readBranchById(branchId);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return branchByName;
	}
	
	
}
