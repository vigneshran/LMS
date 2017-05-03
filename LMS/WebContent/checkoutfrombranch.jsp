<%@ page import="com.gcit.lms.service.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>
<%
	AdminLibrarian service = new AdminLibrarian();
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	AdminBorrower service1 = new AdminBorrower();
	List<Book> books = service1.returnBooksByBranch(branchId);
	Long cardNo = Long.parseLong((String) request.getParameter("cardNo"));
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">
		Check out books
	</h4>
</div>
<form action="checkOutBook" method="post">
	<div class="modal-body">
		<div class="form-group">
			<label for="sel1">Select list:</label> <select class="form-control"
				id="sel1" name = "book">
				<% for(Book b : books) { %>
					<option><%= b.getBookName()%> </option>
					<% } %>
			</select>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">Check Out book</button>
	</div>
	<input type = "hidden" name = "cardNo" value = "<%= cardNo %>"> <br/>
	<input type = "hidden" name = "branchId" value = "<%= branchId %>"> <br/>
</form>
<div>

