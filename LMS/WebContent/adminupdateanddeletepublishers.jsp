<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
	AdminService service = new AdminService();
	List<Publisher> publishers = service.readAllPublishers();
	
%>
<%@include file="index.htm"%>

<%
	if (request.getAttribute("updateMessage") != null) {
%>
<div class="alert alert-success" role="alert">
  <strong>Success!</strong> The publisher named <i> <%= request.getAttribute("updateMessage") %> </i> is updated in our database!
</div>
<%} %>

<%
	if (request.getAttribute("deleteMessage") != null) {
%>
<div class="alert alert-success" role="alert">
  <strong>Success!</strong> The publisher named <i> <%= request.getAttribute("deleteMessage") %> </i> is deleted from our database!
</div>
<%} %>
<div>

<div>

	<center>
		<div class="jumbotron">
			<h2>You are about to update/delete existing list of publishers. Pick
				a selection from the list below!</h2>
		</div>
	</center>


	<div class="page-header"></div>
	<div class="row">
		<div class="col-lg">
			<table class="table">
				<thead>
					<tr>
						<th>Publisher Name</th>

						<th>Update</th>

						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<%
						int i = 1;
						for (Publisher b : publishers) {
							
					%>
					<tr>
						<td>
							<%
								out.println(b.getPublisherName());
							%>
						</td>
						<td>
							<p><button type = "button" class = "btn btn-primary" data-toggle="modal" data-target = "#updatePublisherModal<%=i%>" role = "button" href="adminupdatepublisher.jsp?publisherId=<%=b.getPublisherId()%>"> Update Publisher Now </button></p>
						</td>
						<td>
							<p><button type = "button" class = "btn btn-primary" data-toggle="modal" data-target = "#deletePublisherModal<%=i%>" role = "button" href="admindeletepublisher.jsp?publisherId=<%=b.getPublisherId()%>"> Delete Publisher Now </button></p>
						</td>
					</tr>
					<% i++; } %>
				</tbody>
			</table>
		</div>
	</div>
</div>


<% int i1 = 1; 
for (Publisher b : publishers) { %>
<div class="modal fade bs-example-modal-sm" id = "updatePublisherModal<%=i1%>" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      ...
    </div>
  </div>
</div>
<% i1++; } %>
<% int i2 = 1; 
for (Publisher b : publishers) { %>
<div class="modal fade bs-example-modal-sm" id = "deletePublisherModal<%=i2%>" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      ...
    </div>
  </div>
</div>
<%i2++; } %>