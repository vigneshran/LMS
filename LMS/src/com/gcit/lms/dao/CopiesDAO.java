package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gcit.lms.entity.Copies;

public class CopiesDAO extends BaseDAO {

	public CopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addCopies(Copies copies) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_book_copies VALUES (?,?,?)", new Object[] {copies.getBookId(), copies.getBranchId(), copies.getNoOfCOpies()});
	}
	
	public void updateCopies(Copies copies) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE branchId = ? AND bookId = ?", new Object[] {copies.getNoOfCOpies(), copies.getBranchId(), copies.getBookId()});
	}
	
	public void decrementCopies(Copies copies) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies - 1 WHERE branchId = ? AND bookId = ?", new Object[] {copies.getBranchId(), copies.getBookId()});
	}
	
	public void incrementCopies(Copies copies) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies + 1 WHERE branchId = ? AND bookId = ?", new Object[] {copies.getBranchId(), copies.getBookId()});
	}
	
	public void deleteCopies(Copies copies) throws ClassNotFoundException, SQLException {
		save("DELETE * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[] {copies.getBookId(), copies.getBranchId()});
	}
	
	public List<Copies> readCopiesByBranchId(Integer branchId) throws SQLException {
		return read("SELECT * FROM tbl_book_copies WHERE branchId = ?", new Object[] {branchId});
	}
	
	public List<Copies> readCopyByBranchIdAndBookId(Integer branchId, Integer bookId) throws SQLException {
		return read("SELECT * FROM tbl_book_copies WHERE branchId = ? AND bookId = ?", new Object[] {branchId, bookId});
	}
	
	public boolean doesBookExist(Integer branchId, Integer bookId) throws SQLException {
		List<Copies> copiesInBranch = read("SELECT * FROM tbl_book_copies WHERE branchId = ? AND bookId = ?", new Object[] {branchId, bookId});
		if (copiesInBranch.isEmpty()) return false;
		return true;
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Copies> copies = new ArrayList<>();
		HashMap<Integer, HashMap<Integer, Integer>> bookCopiesByBranch = new HashMap<>();
		while(rs.next())	{
			Copies copy = new Copies();
			if (bookCopiesByBranch.containsKey(rs.getInt("branchId"))) {
				bookCopiesByBranch.get(rs.getInt("branchId")).put(rs.getInt("bookId"), rs.getInt("noOfCopies"));
			}
			else {
				HashMap<Integer, Integer> bookCopies = new HashMap<>();
				bookCopies.put(rs.getInt("bookId"), rs.getInt("noOfCopies"));
				bookCopiesByBranch.put(rs.getInt("branchId"), bookCopies);
			}
			copy.setCopiesOfBooks(bookCopiesByBranch);
			copy.setBookId(rs.getInt("bookId"));
			copy.setBranchId(rs.getInt("branchId"));
			copy.setNoOfCOpies(rs.getInt("noOfCopies"));
			copies.add(copy);
		}
		return copies;
	}
	
	

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
