<%@ page import="com.gcit.lms.service.AdminLibrarian"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
	AdminLibrarian service = new AdminLibrarian();
	List<Branch> branches = service.readAllBranches();
%>
<%@include file="index.htm"%>

<%
	if (request.getAttribute("editMessage") != null) {
%>
<div class="alert alert-success" role="alert">
  <strong>Success!</strong> The details of the branch are updated!
</div>
<%} %>
<div>
<div class = "jumbotron">
	<h2>Welcome, Librarian. Pick the branch you would like to serve
		today.</h2>
</div>
	
	<div class="page-header"></div>
	<div class="row">
		<div class="col-md-6">
		
			<table class="table gcit ">
				<thead>
					<tr>
					</tr>
					<tr>
					</tr>
				</thead>
				<tbody>
					<%
						for (Branch b : branches) {
					%>
					<tr>
						<td>
							<%
								out.println(b.getBranchName());
							%>
						</td>
						<td><a href="branchPage.jsp?branchId=<%=b.getBranchId()%>">Enter
								</button></td>
					</tr>
					<%
						}
					%>
				</tbody>
				</div>
			</table>
			</center>
		</div>
	</div>
</div>