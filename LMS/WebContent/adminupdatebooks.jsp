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
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	Book book = service.returnBookObjectById(bookId).get(0);
	List<Author> bookAuthors = book.getAuthors();
	List<Integer> authorIds = new ArrayList<>();
	String pubName = "No publisher selected!";
	if (!book.getPublisher().isEmpty()) {
		pubName = book.getPublisher().get(0).getPublisherName();
	}
%>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Add a new book:</h4>
	</div>
	<form action="updateBook" method="post">

		<div class="modal-body">
			<b> Title: </b><br /> <input type=text name="updatedBook"
				value="<%=book.getBookName()%>"> <br />
			<div class="form-group">
				<label for="sel1">Select author:</label> <select
					class="form-control" multiple id="sel1" name="author">


					<%
						outerloop: for (Author a : authors) {
					%>
					<%
						innerloop: for (Author a1 : book.getAuthors()) {
								if (a1.getAuthorId() == a.getAuthorId()) {
					%>
					<option selected><%=a.getAuthorName()%>
					</option>
					<%
						continue outerloop;
								}
					%>


					<%
						}
					%>
					<option><%=a.getAuthorName()%>
					</option>
					<%
						}
					%>
					<%
						if (book.getAuthors().size() == 0) {
					%>

					<%
						for (Author a : authors) {
					%>
					<option><%=a.getAuthorName()%>
					</option>
					<%
						}
					%>

					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="sel1">Select publisher: <%=pubName%></label> <select
					class="form-control" id="sel1" name="publisher">


					<%
						outerloop: for (Publisher p : publishers) {
					%>
					<%
						innerloop: for (Publisher p1 : book.getPublisher()) {
								if (p1.getPublisherId() == p.getPublisherId()) {
					%>
					<option selected><%=p.getPublisherName()%>
					</option>
					<%
						continue outerloop;
								}
					%>


					<%
						}
					%>
					<option><%=p.getPublisherName()%>
					</option>
					<%
						}
					%>
					<%
						if (book.getPublisher().size() == 0) {
					%>

					<%
						for (Publisher p : publishers) {
					%>
					<option><%=p.getPublisherName()%>
					</option>
					<%
						}
					%>

					<%
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="sel1">Select genres:</label> <select
					class="form-control" multiple id="sel1" name="genre">


					<%
						outerloop: for (Genre g : genres) {
					%>
					<%
						innerloop: for (Genre g1 : book.getGenres()) {
								if (g1.getGenreId() == g.getGenreId()) {
					%>
					<option selected><%=g.getGenreName()%>
					</option>
					<%
						continue outerloop;
								}
					%>


					<%
						}
					%>
					<option><%=g.getGenreName()%>
					</option>
					<%
						}
					%>
					<%
						if (book.getGenres().size() == 0) {
					%>

					<%
						for (Genre g : genres) {
					%>
					<option><%=g.getGenreName()%>
					</option>
					<%
						}
					%>

					<%
						}
					%>
				</select>
			</div>
		</div>
		<div class="modal-footer">
			<input type = "hidden" name = "bookId" value = "<%=bookId %>"> 
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Update book</button>
		</div>
	</form>
</div>