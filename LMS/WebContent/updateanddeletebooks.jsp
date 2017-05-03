<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
	List<Book> books = new ArrayList<>();
	Integer pageNo = 0;
	AdminService service = new AdminService();
	if (request.getAttribute("authors") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = service.readAllBooksWithPageNo(1);
	}
%>
<%@include file="index.htm"%>

<%
	if (request.getAttribute("updateMessage") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Success!</strong> The book titled <i> <%=request.getAttribute("updateMessage")%>
	</i> is updated in our database!
</div>
<%
	}
%>

<%
	if (request.getAttribute("deleteMessage") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Success!</strong> The book titled <i> <%=request.getAttribute("deleteMessage")%>
	</i> is deleted from our database!
</div>
<%
	}
%>
<div>

	<center>
		<div class="jumbotron">
			<h2>You are about to update/delete existing list of books. Pick
				a selection from the list below!</h2>
		</div>
	</center>

	<%
		Integer numberOfPages = 0;
		List<Book> fullBooks = service.readAllBooks();
		Integer noOfBooks = fullBooks.size();
		if (noOfBooks % 5 > 0) {
			numberOfPages = noOfBooks / 5 + 1;
		} else {
			numberOfPages = noOfBooks / 5;
		}
	%>

	<script>
	function searchBookAdmin(page) {
		$.ajax({
			url : "searchBookAdmin",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			var arr_data = String(data).split("\n");
			$('#bookTable').html(arr_data[1]);
			$('#pagination').html(arr_data[0]);
		})
	}
</script>
	<script>
function setPageNo(p) {
	searchBookAdmin(p); 
}

</script>
	<script>
	$(document).ready ( 
		function(){
    	searchBookAdmin(1);
  	});
</script>
	<div class="col-lg-6">
		<div class="input-group">
			<form action="searchBookAdmin">
				<input type="hidden" name="branchId" value=branchId> <input
					type="text" class="form-control" name="searchString"
					id="searchString" placeholder="Search for..."
					oninput="searchBookAdmin(1)">
			</form>
		</div>
		<!-- /input-group -->
	</div>
	<nav aria-label="Page navigation">
		<ul class="pagination" id="pagination">

			<%
				for (int i = 1; i <= numberOfPages; i++) {
			%>
			<li><a onclick="setPageNo(<%=i%>)"><%=i%></a></li>
			<%
				}
			%>

		</ul>
	</nav>
	<div class="page-header"></div>
	<div class="row">
		<div class="col-lg">
			<table class="table" id = "bookTable">
				<thead>
					<tr>
						<th>Book Title</th>

						<th>Update</th>

						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 1;
						for (Book b : books) {
					%>
					<tr>
						<td>
							<%
								out.println(b.getBookName());
							%>
						</td>
						<td>
							<p>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#updateBookModal<%=i%>"
									role="button"
									href="adminupdatebooks.jsp?bookId=<%=b.getBookId()%>">
									Update Book Now</button>
							</p>
						</td>
						<td>
							<p>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#deleteBookModal<%=i%>"
									role="button"
									href="admindeletebooks.jsp?bookId=<%=b.getBookId()%>">
									Delete Book Now</button>
							</p>
						</td>
					</tr>
					<%
						i++;
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</div>


<%
	int i1 = 1;
	for (Book b : books) {
%>
<div class="modal fade bs-example-modal-sm" id="updateBookModal<%=i1%>"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<%
	i1++;
	}
%>
<%
	int i2 = 1;
	for (Book b : books) {
%>
<div class="modal fade bs-example-modal-sm" id="deleteBookModal<%=i2%>"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<%
	i2++;
	}
%>



