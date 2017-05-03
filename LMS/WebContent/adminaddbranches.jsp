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
		<h4 class="modal-title">Add a new branch:</h4>
	</div>
	<form action="addBranch" method="post">
		<div class="modal-body">
			 Add new branch's name: <br/> <input type=text name="newBranch"> <br /> <br/>
			 Add its address (optional): <br/> <input type=text name="newAddress"> <br />
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Branch</button>
		</div>
	</form>
</div>