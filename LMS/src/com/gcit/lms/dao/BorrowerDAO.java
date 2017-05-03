package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO{
	
	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_borrower (name, address, phone) VALUES (?, ?, ?)", new Object[] {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
	}
	
	public Integer addBorrowerWithId(Borrower borrower) throws ClassNotFoundException, SQLException {
		return saveWithId("INSERT INTO tbl_borrower (name, address, phone) VALUES (?, ?, ?)", new Object[] {borrower.getBorrowerName(),borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
	}
	
	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_borrower SET name = ?, address =?, phone = ? WHERE cardNo = ?", new Object[] {borrower.getBorrowerName(),borrower.getBorrowerAddress(), borrower.getBorrowerPhone(), borrower.getCardNo()});
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrower.getCardNo()});
	}

	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public List<Borrower> returnBorrowerByPK(Long cardNo) throws SQLException {
		return read("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{cardNo});
	}
	
	public List<Borrower> readAllBorrowers() throws SQLException {
		return read("SELECT * FROM tbl_borrower", null);
	}
	
	public List<Borrower> readBorrowerByCardNo(Long cardNo) throws SQLException {
		return read("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[] {cardNo});
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		BookDAO bdao = new BookDAO(conn);
		List<Book> booksBorrowed = new ArrayList<>();
		List<Book> booksDue = new ArrayList<>();
		List<Borrower> borrowers = new ArrayList<>();
		LoanDAO ldao = new LoanDAO(conn);
		
		while (rs.next())	{
			Borrower b = new Borrower();
			b.setCardNo(rs.getLong("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerAddress(rs.getString("address"));
			b.setBorrowerPhone(rs.getString("phone"));
			
			b.setBooksDue(bdao.read("SELECT book.bookId, title, pubId FROM tbl_book book JOIN tbl_book_loans loan ON loan.bookId = book.bookId WHERE cardNo = ? AND dateIn IS NULL ", new Object[] {b.getCardNo()}));
			b.setBooksBorrowed(bdao.read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE cardNo = ?)", new Object[] {b.getCardNo()}));
			b.setLoans(ldao.read("SELECT * FROM tbl_book_loans WHERE cardNo = ?", new Object[] {b.getCardNo()}));
			b.setUnreturnedLoans(ldao.read("SELECT * FROM tbl_book_loans WHERE cardNo = ? AND dateIn IS NULL", new Object[] {b.getCardNo()}));
			borrowers.add(b);
		}
		return borrowers;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
