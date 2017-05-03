<%@ page import="com.gcit.lms.service.AdminLibrarian"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
	AdminLibrarian service = new AdminLibrarian();
	List<Branch> branches = service.readAllBranches();
%>
<%@include file="index.htm"%>

<%
	if (request.getAttribute("message") != null) {
%>
<div class="alert alert-danger" role="alert">
  <strong>Oh snap!</strong> The card number you entered is incorrect. Try again!
</div>

<%
	}
%>
<div>
	<style>
.small {
	line-height: 10px;
}

.big {
	line-height: 100px;
}
</style>
	<center>
		<div class="jumbotron">
			<h2>Welcome, borrower! Enter your Card Number here!</h2>
		</div>
	</center>
	<form action="checkNumber" method="post">
		<center>
			Enter number: <input type="text" name="cardNo"> <br />
		</center>
		<div class=big>
			<center>
				<button type="submit" class="btn btn-primary">Sumbit</button>
			</center>
		</div>
	</form>
</div>

