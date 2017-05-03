<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>

<%

%>
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Add a new borrower:</h4>
	</div>
	<form action="addBorrower" method="post">
		<div class="modal-body">
			 Add new borrower's name: <br/> <input type=text name="borrowerName"> <br /> <br/>
			 Add his/her address (optional): <br/> <input type=text name="borrowerAddress"> <br /> <br/>
			 Add his/her phone number (optional): <br/> <input type=text name="borrowerPhone"> <br />
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Borrower</button>
		</div>
	</form>
</div>