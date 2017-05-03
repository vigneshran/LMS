package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO {
	public PublisherDAO (Connection conn)	{
		super(conn);
	}
	
	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}
	
	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisher.getPublisherId()});
	}

	public List<Publisher> readAllPublishers() throws SQLException {
		return read("SELECT * FROM tbl_publisher", null);
	}
	
	public List<Publisher> returnPublisherObjectByName(String name) throws SQLException {
		return read("SELECT * FROM tbl_publisher WHERE publisherName = ?", new Object[] {name});
	}
	
	public List<Publisher> returnPublisherObjectById(Integer id) throws SQLException {
		return read("SELECT * FROM tbl_publisher WHERE publisherId = ?", new Object[] {id});
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		BookDAO bdao = new BookDAO(conn);
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next())	{
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		
 		return publishers;
	}
	
	
	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		return null;
	}
	
	
}
