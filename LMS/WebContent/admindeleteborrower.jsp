<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>

<%
	AdminService service = new AdminService();
	
	Long cardNo  = Long.parseLong(request.getParameter("cardNo"));
	Borrower borrower = service.returnBorrowerByPK(cardNo).get(0);
	
%>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">You are about to delete the borrower named <b>"<%=borrower.getBorrowerName() %>" </b> with card number <%=borrower.getCardNo() %>.  Proceed?</h4>
	</div>
	<form action="deleteBorrower" method="post">

		<div class="modal-body">
			<p> Before you delete a borrower, make sure you are absolutely sure. Once you make a mistake, it's a tedious process to put it back in our database. </p>
		<div class="modal-footer">
			<input type = "hidden" name = "cardNo" value = "<%=cardNo %>"> 
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary"> Yes </button>
		</div>
	</form>
</div>