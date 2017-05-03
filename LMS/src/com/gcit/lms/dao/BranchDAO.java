package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Copies;
import com.gcit.lms.entity.Genre;

public class BranchDAO extends BaseDAO {
	
	public BranchDAO (Connection conn)	{
		super(conn);
	}
	
	public List<Branch> readAllBranches() throws SQLException {
		return read("SELECT * FROM tbl_library_branch", null);
	}
	
	public void addBranch(Branch branch) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?)", new Object[] {branch.getBranchName(), branch.getBranchAddress()});
	}
	
	public Integer addBranchWithId(Branch branch) throws ClassNotFoundException, SQLException {
		return saveWithId("INSERT INTO tbl_library_branch (BranchName, branchAddress) VALUES (?,?)", new Object[] {branch.getBranchName(), branch.getBranchAddress()});
	}
	
	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE BranchId = ?", new Object[] {branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
	}
	
	public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[]{branch.getBranchId()});
	}
	
	public void addBooksToBranch(Integer bookId, Branch branch, Integer no) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_book_copies VALUES (?,?,?)", new Object[]{bookId, branch.getBranchId(), no});
	}
	
	public void updateCopies(Integer bookId, Branch branch, Integer no) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE branchId = ? AND bookId = ?", new Object[] {no, branch.getBranchId(), bookId});
	}
	
	public List<Branch> readBranchByName (String branchName) throws SQLException	{
		return read("SELECT * FROM tbl_library_branch WHERE branchName = ?", new Object[] {branchName});
	}
	
	public List<Branch> readBranchById (Integer branchId) throws SQLException	{
		return read("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[] {branchId});
	}
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		BookDAO bdao = new BookDAO(conn);
		List<Branch> branches = new ArrayList<>();
		while (rs.next())	{
			Branch b = new Branch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchAddress(rs.getString("branchAddress"));
			b.setBranchName(rs.getString("branchName"));
			b.setBooks(bdao.read("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies > 0)", new Object[]{b.getBranchId()}));
			branches.add(b);
		}
		
 		return branches;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
