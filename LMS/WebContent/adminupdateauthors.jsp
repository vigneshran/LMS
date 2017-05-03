<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>

<%
	AdminService service = new AdminService();
	List<Author> authors = service.readAllAuthors();
	List<Publisher> publishers = service.readAllPublishers();
	List<Genre> genres = service.readAllGenres();
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	Author author = service.returnAuthorObjectById(authorId).get(0);
%>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Update author:</h4>
	</div>
	<form action="updateAuthor" method="post">

		<div class="modal-body">
			<b> Author Name: </b><br /> <input type=text name="newAuthor"
				value="<%=author.getAuthorName()%>"> <br />
			<div class="form-group">
			
				</div>
				</div>
		<div class="modal-footer">
			<input type = "hidden" name = "authorId" value = "<%=authorId %>"> 
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Update Author</button>
		</div>
	</form>
</div>