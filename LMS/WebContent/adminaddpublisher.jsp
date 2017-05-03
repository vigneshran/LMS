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
		<h4 class="modal-title">Add a new publisher:</h4>
	</div>
	<form action="addPublisher" method="post">
		<div class="modal-body">
			 Add new publisher's name: <br/> <input type=text name="newPub"> <br /> <br/>
			 Add his address (optional): <br/> <input type=text name="newAddress"> <br /> <br/>
			 Add his phone number (optional): <br/> <input type=text name="newPhone"> <br />
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add Publisher</button>
		</div>
	</form>
</div>