<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>

<%
	AdminService service = new AdminService();
	
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	Author author = service.returnAuthorObjectById(authorId).get(0);
	
%>

<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">You are about to delete the Author names <i>"<%=author.getAuthorName() %>" </i>. Proceed?</h4>
	</div>
	<form action="deleteAuthor" method="post">

		<div class="modal-body">
			<p> Before you delete an author, make sure you are absolutely sure. Once you make a mistake, it's a tedious process to put it back in our database. </p>
		<div class="modal-footer">
			<input type = "hidden" name = "authorId" value = "<%=authorId %>"> 
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary"> Yes </button>
		</div>
	</form>
</div>>