<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="com.gcit.lms.service.AdminLibrarian"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@include file="index.htm"%>
<%
	List<Book> books = new ArrayList<>();
	Integer pageNo = 0;
	AdminService service1 = new AdminService();
	AdminLibrarian service = new AdminLibrarian();
	if (request.getAttribute("authors") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = service1.readAllBooksWithPageNo(1);
	}
	pageNo = Integer.parseInt(request.getParameter("pageNo"));
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	Branch branch = new Branch();
	branch = (Branch) request.getSession().getAttribute("branch");
%>

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
	function searchBook(page, id) {
		$.ajax({
			url : "searchBook",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page,
				branchId : id
			}
		}).done(function(data) {
			var arr_data = String(data).split("\n");
			$('#bookTable').html(arr_data[1]);
			$('#pagination').html(arr_data[0]);
		})
	}
</script>
<script>
function setPageNo(p, branchId) {
	searchBook(p, branchId); 
}

</script>
<script>
	$(document).ready ( 
		function(){
    	searchBorrower(1, branchId);
  	});
</script>

<%
	if (request.getAttribute("messageAdd") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Success!</strong> The book is added to your branch!
</div>
<%
	}
%>

<%
	if (request.getAttribute("messageUpdate") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Success!</strong> The book copies are updated to your branch!
</div>
<%
	}
%>
<div>

	<center>
		<div class="jumbotron">
			<h2>
				You are choosing to make changes to Branch:
				<%=branch.getBranchName()%>. </br> Add or update copies in the list below
			</h2>
		</div>
	</center>
	<div class="col-lg-6">
		<div class="input-group">
			<form action="searchBook">
				<input type="hidden" name="branchId" value=branchId> <input
					type="text" class="form-control" name="searchString"
					id="searchString" placeholder="Search for..."
					oninput="searchBook(1, <%=branchId%>)">
			</form>
		</div>
		<!-- /input-group -->
	</div>
	<nav aria-label="Page navigation">
		<ul class="pagination" id="pagination">

			<%
				for (int i = 1; i <= numberOfPages; i++) {
			%>
			<li><a onclick="setPageNo(<%=i%>,<%=branchId%>)"><%=i%></a></li>
			<%
				}
			%>

		</ul>
	</nav>
	<div class="page-header"></div>
	<div class="row">
		<div class="col-lg">
			<table class="table" id="bookTable">
				<thead>
					<tr>
						<th>Book Title</th>

						<th>Copies Available Now</th>

						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Book b : books) {
					%>
					<tr>
						<td>
							<%
								out.println(b.getBookName());
							%>


						</td>

						<%
							if (service.doesBookExist(b.getBookId(), branchId)) {
									Integer copy = service.getCopies(b.getBookId(), branchId).get(0).getNoOfCOpies();
						%>
						<td>
							<form action="editCopy" method="post">
								<input type="number" required="required" name="updatedCopies"
									value="<%=copy%>">
						</td>
						<td><input type="hidden" name="branchId"
							value="<%=branchId%>"> <input type="hidden" name="bookId"
							value="<%=b.getBookId()%>">
							<button class="btn btn-primary" type="submit">Update
								Copies Now</button></td>
						</form>
						<%
							} else {
						%>
						<td>
							<form action="addCopy" method="post">
								<input type="number" required="required" name="addedCopies"
									value=0>


								<td><input type="hidden" name="branchId"
									value="<%=branchId%>"> <input type="hidden"
									name="bookId" value="<%=b.getBookId()%>">
									<button class="btn btn-primary" type="submit">Add
										Copies Now</button></td>
							</form> <%
 	}
 %>
						
					</tr>
					<%
						}
					%>
				</tbody>

				<%
					request.setAttribute("branch", branch);
				%>

				</div>