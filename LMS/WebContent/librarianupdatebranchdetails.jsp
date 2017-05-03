<%@ page import="com.gcit.lms.service.AdminLibrarian"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>
<%
	Branch branch = new Branch();
	AdminLibrarian service = new AdminLibrarian();
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	branch = service.returnBranchObjectById(branchId).get(0);
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">Edit Branch <%=branch.getBranchName()%></h4>
</div>
<form action="updateBranchDetails" method="post">
	<div class="modal-body">
		<p>You can add details here&hellip;</p>
		Branch Name <input type="text" name="updatedBranchName"
			value="<%=branch.getBranchName()%>"> <br /> <br/> Branch Address <input
			type="text" name="updatedBranchAddress"
			value="<%=branch.getBranchAddress()%>"> > <br /> 
	</div>
	<div class="modal-footer">
			<input type="hidden" name="branchName" value="<%=branch.getBranchName()%>"> <br />
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">Save changes</button>
	</div>
</form>
<div>


	