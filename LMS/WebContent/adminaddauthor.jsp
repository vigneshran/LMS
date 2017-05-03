<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>

<%
	AdminService service = new AdminService();
	List<Author> authors = service.readAllAuthors();
	List<Publisher> publishers = service.readAllPublishers();
	List<Genre> genres = service.readAllGenres();
%>
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Add a new author:</h4>
	</div>
	<form action="addAuthor" method="post">
		<div class="modal-body">
			<b> Author Name: </b><br/> <input type=text name="newAuthor"> <br />
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Author</button>
		</div>
	</form>
</div>