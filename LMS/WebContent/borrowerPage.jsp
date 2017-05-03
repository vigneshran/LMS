
<%
	if (request.getAttribute("message") != null) {
%>
<div class="alert alert-danger" role="alert">
	<strong>Oh snap!</strong> The card number you entered is incorrect. Try
	again!
</div>

<%
	}
%>

<%@ page import="com.gcit.lms.service.AdminBorrower"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@include file="index.htm"%>


<%
	Borrower borrower = (Borrower) request.getAttribute("borrower");
%>

<%
	if (request.getAttribute("returnMessage") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Great job!</strong> The book has been returned!
</div>
<%
	}
%>

<%
	if (request.getAttribute("checkMessage") != null) {
%>
<div class="alert alert-success" role="alert">
	<strong>Happy Reading!</strong> The book has been checked out! And the
	due date is set to:
	<%=request.getAttribute("checkMessage")%>
</div>
<%
	}
%>

<center>
	<div class="jumbotron">
		<h2>
			Hi,
			<%=borrower.getBorrowerName()%>. What do you want to do today?
		</h2>
	</div>
</center>

<div class="row">
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Returning a book</h3>
				<p>
					You have
					<%=borrower.getUnreturnedLoans().size()%>
					books you haven't returned yet. Keeping in mind that you can only
					return books to branches you borrowed them from, do you want to
					return any books today?
				</p>
				<p>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#returnBooksModal" role="button"
						href="borrowerreturnbooks.jsp?cardNo=<%=borrower.getCardNo()%>">
						Return Books Now</button>
				</p>
			</div>
		</div>
	</div>
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Checking out books</h3>
				<p>We have over 20 books in our database spanning over different
					genres spread across multiple outlets all over the country. Come
					choose a branch and be a part of our ever-growing community of avid
					readers.</p>
				<p>
					<a
						href="borrowercheckoutbooks.jsp?cardNo=<%=borrower.getCardNo()%>"
						class="btn btn-primary" role="button"> Check Out Books Now</a>
				</p>
			</div>
		</div>
	</div>
</div>

<div class="modal fade bs-example-modal-sm" id="returnBooksModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>

<%
	List<Book> booksDue = borrower.getBooksDue();
	if (!booksDue.isEmpty()) {
		Book book = new Book();
		book = booksDue.get(0);
%>

<div class="alert alert-warning" role="alert">
	<strong>Hey, borrower!</strong> The book <i> <%=book.getBookName()%>
	</i> is due soon. Are you returning it back to us?
</div>

<%
	}
%>
<<center>
<p>
	<a href="borrower.jsp" class="btn btn-info btn-lg"> <span
		class="glyphicon glyphicon-log-out"></span> Log out
	</a>
</p>
</center>
