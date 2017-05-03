package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class BookDAO extends BaseDAO {
	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBook(Book book) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_book (title, pubId) VALUES (?, NULL)", new Object[] {book.getBookName()});
	}
	
	public Integer addBookWithId(Book book) throws ClassNotFoundException, SQLException {
		return saveWithId("INSERT INTO tbl_book (title) VALUES (?, NUL)", new Object[] {book.getBookName()});
	}
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_book SET title = ? WHERE bookId = ?", new Object[] {book.getBookName(), book.getBookId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[]{book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws SQLException {
		return read("SELECT * FROM tbl_book", null);
	}
	
	public List<Book> readBookByName(String title) throws SQLException {
		return read("SELECT * FROM tbl_book WHERE title = ?", new Object[] {title});
	}

	public void addPublisher(Publisher publisher, Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET pubId = ? WHERE bookId = ?", new Object[] {publisher.getPublisherId(), book.getBookId()});
	}
	
	public void addBookAuthors(Book book, Author author) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_authors VALUES (?,?)", new Object[] {book.getBookId(), author.getAuthorId()});
	}
	
	public void addBookGenres(Book book, Genre genre) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_genres VALUES (?,?)", new Object[] {genre.getGenreId(), book.getBookId()});
	}
	
	public List<Book> readBookById(Integer id) throws SQLException {
		return read("SELECT * FROM tbl_book WHERE bookId = ?", new Object[] {id});
	}
	
	public void updatePublisher(Publisher publisher, Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET pubId = ? WHERE bookId = ?", new Object[] {publisher.getPublisherId(), book.getBookId()});
	}
	
	public void deleteBookAuthors(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_authors WHERE bookId = ?", new Object[] {book.getBookId()});
	}
	
	public void deleteBookGenres(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_genres WHERE bookId = ?", new Object[] {book.getBookId()});
	}
	
	public List<Book> readAllBooksWithPageNo(Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		return readWithPageNo("SELECT * FROM tbl_book", null);
	}
	
	public List<Book> searchBookByName(String name, Integer pageNo) throws SQLException {
		name = "%"+name+"%";
		setPageNo(pageNo);
		return readWithPageNo("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {name});
	}
	
	public List<Book> searchBookByNameWithoutPage(String name) throws SQLException {
		name = "%"+name+"%";
		return read("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {name});
	}
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		AuthorDAO adao = new AuthorDAO(conn);
		PublisherDAO pdao = new PublisherDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		List<Book> books = new ArrayList<>();
		while(rs.next())	{
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setBookName(rs.getString("title"));
			b.setAuthors(adao.readFirstLevel("SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)", new Object[] {b.getBookId()}));
			b.setPublisher(pdao.read("SELECT * FROM tbl_publisher WHERE publisherId IN (SELECT pubId FROM tbl_book WHERE bookId = ?)", new Object[] {b.getBookId()}));
			b.setGenres(gdao.read("SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)", new Object[] {b.getBookId()}));
			books.add(b);
		}
		return books;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		AuthorDAO adao = new AuthorDAO(conn);
		List<Book> books = new ArrayList<>();
		while(rs.next())	{
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setBookName(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
}
