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
		<h4 class="modal-title">Add a new book:</h4>
	</div>
	<form action="addBook" method="post">
		<div class="modal-body">
			<b> Title: </b><br/> <input type=text name="newBook"> <br />
			<div class="form-group">
				<label for="sel1">Select author:</label> <select
					class="form-control" multiple id="sel1" name="author">
					<%
						for (Author a : authors) {
					%>
					<option><%=a.getAuthorName()%>
					</option>
					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="sel1">Select publisher:</label> <select
					class="form-control" id="sel1" name="publisher">
					<%
						for (Publisher p : publishers) {
					%>
					<option><%=p.getPublisherName()%>
					</option>
					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="sel1">Select genres:</label> <select
					class="form-control" multiple id="sel1" name="genre">
					<%
						for (Genre g : genres) {
					%>
					<option><%=g.getGenreName()%>
					</option>
					<%
						}
					%>
				</select>
			</div>
	


		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add book</button>
		</div>
	</form>
</div>