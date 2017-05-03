package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO {
	public GenreDAO (Connection conn)	{
		super(conn);
	}
	
	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException	{
		save("INSERT INTO tbl_genre (genreName) VALUES (?)", new Object[] {genre.getGenreName()});
	}
	
	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException	{
		save("UPDATE tbl_genre SET genreName = ? WHERE genreId = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("DELETE * FROM tbl_genre WHERE genreId = ?", new Object[]{genre.getGenreId()});
	}
	
	public List<Genre> readAllGenres() throws SQLException {
		return read("SELECT * FROM tbl_genre", null);
	}
	
	public List<Genre> returnGenreObjectByName(String name) throws SQLException {
		return read("SELECT * FROM tbl_genre WHERE genre_name = ?", new Object[] {name});
	}
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		BookDAO bdao = new BookDAO(conn);
		List<Genre> genres = new ArrayList<>();
		while (rs.next())	{
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			genres.add(g);
		}
		
 		return genres;
	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws SQLException {
		return null;
	}
}
