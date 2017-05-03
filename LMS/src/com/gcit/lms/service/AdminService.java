package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LoanDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Loan;
import com.gcit.lms.entity.Publisher;

public class AdminService {

	public List<Book> readAllBooks() throws SQLException {
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
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public List<Author> readAllAuthors() throws SQLException {
		Connection conn = null;
		AuthorDAO adao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			adao = new AuthorDAO(conn);
			conn.commit();
			return adao.readAllAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Author> readAllAuthorsWithPageNo(Integer pageNo) throws SQLException {
		Connection conn = null;
		AuthorDAO adao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			adao = new AuthorDAO(conn);
			conn.commit();
			return adao.readAllAuthorsWithPageNo(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public List<Publisher> readAllPublishers() throws SQLException {
		Connection conn = null;
		PublisherDAO pdao = null;
		try {
			conn = ConnectionUtil.getConnection();
			pdao = new PublisherDAO(conn);
			conn.commit();
			return pdao.readAllPublishers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public List<Genre> readAllGenres() throws SQLException {
		Connection conn = null;
		GenreDAO gdao = null;
		try {
			conn = ConnectionUtil.getConnection();
			gdao = new GenreDAO(conn);
			conn.commit();
			return gdao.readAllGenres();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}

	public List<Author> returnAuthorObjectByName(String name) throws SQLException {
		Connection conn = null;
		AuthorDAO adao = null;
		try {
			conn = ConnectionUtil.getConnection();
			adao = new AuthorDAO(conn);
			conn.commit();
			return adao.returnAuthorObjectByName(name);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Book> returnBookObjectByNAme(String name) throws SQLException {
		Connection conn = null;
		BookDAO bdao = null;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BookDAO(conn);
			conn.commit();
			return bdao.readBookByName(name);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Genre> returnGenreObjectByName(String name) throws SQLException {
		Connection conn = null;
		GenreDAO gdao = null;
		try {
			conn = ConnectionUtil.getConnection();
			gdao = new GenreDAO(conn);
			conn.commit();
			return gdao.returnGenreObjectByName(name);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Publisher> returnPublisherObjectByName(String name) throws SQLException {
		Connection conn = null;
		PublisherDAO pdao = null;
		try {
			conn = ConnectionUtil.getConnection();
			pdao = new PublisherDAO(conn);
			conn.commit();
			return pdao.returnPublisherObjectByName(name);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public void addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.addBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void addPublisher(Publisher publisher, Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.addPublisher(publisher, book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void addBookAuthors(Book book, Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.addBookAuthors(book, author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void addBookGenres(Book book, Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.addBookGenres(book, genre);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public List<Book> returnBookObjectById(Integer bookId) throws SQLException {
		Connection conn = null;
		BookDAO bdao = null;
		List<Book> bookById = null ;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BookDAO(conn);
			bookById = bdao.readBookById(bookId);
			conn.commit();
			return bookById;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null) conn.close();
		}
		return null;
	}
	
	public void updateBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.updateBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void updatePublisher(Publisher publisher, Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.updatePublisher(publisher, book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deleteBookAuthors(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBookAuthors(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deleteBookGenres(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBookGenres(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deleteBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void addAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.addAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public List<Author> returnAuthorObjectById(Integer id) throws SQLException {
		Connection conn = null;
		AuthorDAO adao = null;
		try {
			conn = ConnectionUtil.getConnection();
			adao = new AuthorDAO(conn);
			conn.commit();
			return adao.returnAuthorObjectById(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public void updateAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
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
	
	public void addBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.addBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void updateBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.updateBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deleteBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.deleteBranch(branch);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	public List<Branch> returnBranchObjectById(Integer id) throws SQLException {
		Connection conn = null;
		BranchDAO bdao = null;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BranchDAO(conn);
			conn.commit();
			return bdao.readBranchById(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			bdao.addPublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			bdao.updatePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			bdao.deletePublisher(publisher);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public List<Publisher> returnPublisherObjectById(Integer id) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PublisherDAO bdao = new PublisherDAO(conn);
			return bdao.returnPublisherObjectById(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Borrower> returnBorrowerByPK(Long  cardNo) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.returnBorrowerByPK(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {;
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public void addBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.addBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void updateBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.updateBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borrower);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public List<Borrower> readAllBorrowers() throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readAllBorrowers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {;
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Loan> readAllLoans() throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			LoanDAO ldao = new LoanDAO(conn);
			return ldao.readAllLoansDue();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {;
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public void overrideDueDate(Loan loan, Integer days) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			LoanDAO ldao = new LoanDAO(conn);
			ldao.updatDueDate(loan, days);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {;
			if (conn != null)
				conn.close();
		}
	}
	
	public List<Author> searchByAuthorName(String name, Integer pageNo) throws SQLException {
		Connection conn = null;
		AuthorDAO adao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			adao = new AuthorDAO(conn);
			conn.commit();
			return adao.searchAuthorByName(name, pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Author> searchByAuthorName(String name) throws SQLException {
		Connection conn = null;
		AuthorDAO adao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			adao = new AuthorDAO(conn);
			conn.commit();
			return adao.searchAuthorByNameWithoutPage(name);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Book> readAllBooksWithPageNo(Integer pageNo) throws SQLException {
		Connection conn = null;
		BookDAO bdao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BookDAO(conn);
			conn.commit();
			return bdao.readAllBooksWithPageNo(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
	public List<Book> searchByBookName(String name, Integer pageNo) throws SQLException {
		Connection conn = null;
		BookDAO bdao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BookDAO(conn);
			conn.commit();
			return bdao.searchBookByName(name, pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	public List<Book> searchByBookName(String name) throws SQLException {
		Connection conn = null;
		BookDAO bdao = null;
		List<Book> allBooks = null;
		try {
			conn = ConnectionUtil.getConnection();
			bdao = new BookDAO(conn);
			conn.commit();
			return bdao.searchBookByNameWithoutPage(name);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		} finally {
			if (conn != null)
				conn.close();
		}
		return null;
	}
	
}
