
<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>
<%@include file="index.htm"%>
<%
	AdminService service = new AdminService();
	List<Book> books = service.readAllBooks();
	List<Author> authors = service.readAllAuthors();
	List<Publisher> publihsers = service.readAllPublishers();
	List<Genre> genres = service.readAllGenres();
%>

<%
	if (request.getAttribute("addMessage") != null) {
%>
<div class="alert alert-success" role="alert">
  <strong>Success!</strong> The author named <i> <%= request.getAttribute("addMessage") %> </i> is added to our database!
</div>
<%} %>


<center>
	<div class="jumbotron">
		<h2>
			Add or delete or update authors here! We have about
			<%=authors.size()%>
			authors in our database. Help us expand!
		</h2>
	</div>
</center>

<div class="row">
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Adding Authors</h3>
				<p>
					We have about
					<%=authors.size()%>
					authors in our database. Help us expand!
				</p>
				<p>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#addAuthorModal" role="button" href="adminaddauthor.jsp">Add
						Authors Now</button>
				</p>
			</div>
		</div>
	</div>
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Updating and Deleting Authors</h3>
				<p>From a list of well-curated database, this section provides
					the opportunity to update and delete authors on the go!</p>
				<p><p>
					<a
						href="adminupdateanddeleteauthors.jsp?pageNo=1"
						class="btn btn-primary" role="button"> Update or Delete Now! </a>
				</p></p>

			</div>
		</div>
	</div>
</div>

<%
	if (request.getAttribute("addMessage") != null) {
%>
<div class="alert alert-success" role="alert">
  <strong>Success!</strong> The author named <i> <%= request.getAttribute("addMessage") %> </i> is added to our database!
</div>
<%} %>

<div class="modal fade bs-example-modal-sm" id="addAuthorModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
