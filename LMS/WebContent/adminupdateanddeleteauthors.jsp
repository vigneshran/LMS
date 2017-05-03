<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
	List<Author> books = new ArrayList<>();
	Integer pageNo = 0;
	AdminService service = new AdminService();

	if (request.getAttribute("authors") != null) {
		books = (List<Author>) request.getAttribute("authors");
	} else {
		books = service.readAllAuthorsWithPageNo(1);
	}
	pageNo = Integer.parseInt(request.getParameter("pageNo"));
%>

<%
	Integer numberOfPages = 0;
	List<Author> fullAuthors = service.readAllAuthors();
	Integer noOfAuthors = fullAuthors.size();
	if (noOfAuthors % 5 > 0) {
		numberOfPages = noOfAuthors / 5 + 1;
	} else {
		numberOfPages = noOfAuthors / 5;
	}
%>
<script>
	function searchAuthor(page) {
		$.ajax({
			url : "searchAuthor",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			
			var arr_data = String(data).split("\n");
			$('#authorsTable').html(arr_data[1]);
			$('#pagination').html(arr_data[0]);
		})
	}
</script>
<script>
function setPageNo(p) {
	searchAuthor(p);
}

</script>
<script>
  $(document).ready ( 
	function(){
    searchBorrower(1);
  });
</script>
<%@include file="index.htm"%>

<%
	if (request.getAttribute("updateMessage") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Success!</strong> The Author named <i> <%=request.getAttribute("updateMessage")%>
	</i> is updated in our database!
</div>
<%
	}
%>

<%
	if (request.getAttribute("deleteMessage") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Success!</strong> The Author named <i> <%=request.getAttribute("deleteMessage")%>
	</i> is deleted from our database!
</div>
<%
	}
%>


<div>
	<center>
		<div class="jumbotron">
			<h2>You are about to update/delete existing list of authors.
				Pick a selection from the list below!</h2>
		</div>
	</center>
	<div class="col-lg-6">
		<div class="input-group">
			<form action="searchAuthor">
				<input type="text" class="form-control" name="searchString"
					id="searchString" placeholder="Search for..."
					oninput="searchAuthor(1)">
			</form>
		</div>
		<!-- /input-group -->
	</div>
	<!-- /.col-lg-6 -->
	<div>
		<nav aria-label="Page navigation">
			<ul class="pagination" id = "pagination">
				
				<%
					for (int i = 1; i <= numberOfPages; i++) {
				%>
				<li><a onclick = "setPageNo(<%=i%>)"><%=i%></a></li>
				<%
					}
				%>
				
			</ul>
		</nav>
		<div class="page-header"></div>
		<div class="row">
			<div class="col-lg">
				<table class="table" id="authorsTable">
					<thead>
						<tr>
							<th>Author Name</th>

							<th>Update</th>

							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 1;
							for (Author b : books) {
						%>
						<tr>
							<td>
								<%
									out.println(b.getAuthorName());
								%>
							</td>
							<td>
								<p>
									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target="#updateAuthorModal<%=i%>"
										role="button"
										href="adminupdateauthors.jsp?authorId=<%=b.getAuthorId()%>">
										Update Author Now</button>
								</p>
							</td>
							<td>
								<p>
									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target="#deleteAuthorModal<%=i%>"
										role="button"
										href="admindeleteauthors.jsp?authorId=<%=b.getAuthorId()%>">
										Delete Author Now</button>
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
</div>

<%
	int i1 = 1;
	for (Author b : books) {
%>
<div class="modal fade bs-example-modal-sm"
	id="updateAuthorModal<%=i1%>" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
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
	for (Author b : books) {
%>
<div class="modal fade bs-example-modal-sm"
	id="deleteAuthorModal<%=i2%>" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<%
	i2++;
	}
%>