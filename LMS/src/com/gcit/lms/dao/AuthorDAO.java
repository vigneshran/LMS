package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;

public class AuthorDAO extends BaseDAO{
	
	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] {author.getAuthorName()});
	}
	
	public Integer addAuthorWithId(Author author) throws ClassNotFoundException, SQLException {
		return saveWithId("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] {author.getAuthorName()});
	}
	
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[]{author.getAuthorId()});
	}
	
	public List<Author> readAllAuthors() throws SQLException {
		return read("SELECT * FROM tbl_author", null);
	}
	
	public List<Author> returnAuthorObjectByName(String name) throws SQLException {
		return read("SELECT * FROM tbl_author WHERE authorName = ?", new Object[] {name});
	}
	
	public List<Author> returnAuthorObjectById(Integer id) throws SQLException {
		return read("SELECT * FROM tbl_author WHERE authorId = ?", new Object[] {id});
	}
	
	public List<Author> readAllAuthorsWithPageNo(Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		return readWithPageNo("SELECT * FROM tbl_author", null);
	}
	
	public List<Author> searchAuthorByName(String name, Integer pageNo) throws SQLException {
		name = "%"+name+"%";
		setPageNo(pageNo);
		return readWithPageNo("SELECT * FROM tbl_author WHERE authorName LIKE ?", new Object[] {name});
	}
	
	public List<Author> searchAuthorByNameWithoutPage(String name) throws SQLException {
		name = "%"+name+"%";
		return read("SELECT * FROM tbl_author WHERE authorName LIKE ?", new Object[] {name});
	}
	
	
	
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		BookDAO bdao = new BookDAO(conn);
		List<Author> authorList = new ArrayList<>();
		while(rs.next())	{
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(bdao.readFirstLevel("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId = ?)", new Object[] {a.getAuthorId()}));
			authorList.add(a);
		}
		return authorList;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		BookDAO bdao = new BookDAO(conn);
		List<Author> authorList = new ArrayList<>();
		while(rs.next())	{
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authorList.add(a);
		}
		return authorList;
	}
	

	
	
}
