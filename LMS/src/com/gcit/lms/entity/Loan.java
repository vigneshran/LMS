package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class Loan implements Serializable{

	private static final long serialVersionUID = -1025341928144630363L;
	
	private Integer bookId;
	private Integer branchId;
	private Long cardNo;
	private String dateIn;
	private String dateOut;
	private String dueDate;
	private java.sql.Timestamp dateOut1;
	
	private List<Borrower> borrower;
	private List<Book> book;
	
	
	
	
	/**
	 * @return the borrower
	 */
	public List<Borrower> getBorrower() {
		return borrower;
	}
	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(List<Borrower> borrower) {
		this.borrower = borrower;
	}
	/**
	 * @return the book
	 */
	public List<Book> getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(List<Book> book) {
		this.book = book;
	}
	/**
	 * @return the bookId
	 */
	
	
	
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @return the dateOut1
	 */
	public java.sql.Timestamp getDateOut1() {
		return dateOut1;
	}
	/**
	 * @param dateOut1 the dateOut1 to set
	 */
	public void setDateOut1(java.sql.Timestamp dateOut1) {
		this.dateOut1 = dateOut1;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the cardNo
	 */
	public Long getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the dateIn
	 */
	public String getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	/**
	 * @return the dateOut
	 */
	public String getDateOut() {
		return dateOut;
	}
	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
