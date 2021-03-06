<%@ page import="com.gcit.lms.service.AdminLibrarian"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@include file="index.htm"%>
<%
	Branch branch = new Branch();
	AdminLibrarian service = new AdminLibrarian();
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	branch = service.returnBranchObjectById(branchId).get(0);
%>

<%
	if (request.getAttribute("editMessage") != null) {
%>
<div class="alert alert-success" role="alert">
  <strong>Success!</strong> The branch details are updated!
</div>
<% } %>
<center>
<div class = "jumbotron">
<h2>
	You are choosing to make changes to Branch:
	<%=branch.getBranchName()%>.
	What do you want to do?
</h2>
</div>
</center>
<%
	request.setAttribute("branch", branch);
%>
<%
	request.getSession().setAttribute("branch", branch);
%>

<div class="row">
  <div class="col-lg-6">
    <div class="thumbnail">
      <div class="caption">
        <h3>Adding copies to the branch</h3>
        <p>The <%= branch.getBranchName() %> Public Library has <%= branch.getBooks().size() %> books in its database. It is located at <%= branch.getBranchAddress()  %>. Help expand the library. </p>
        <p><a href="librarianaddcopies.jsp?branchId=<%=branch.getBranchId()%>&pageNo=1" class="btn btn-primary" role="button"> Add Copies Now</a></p>
      </div>
    </div>
  </div>
    <div class="col-lg-6">
    <div class="thumbnail">
      <div class="caption">
        <h3>Update the details of the library</h3>
        <p>The <%= branch.getBranchName() %> Public Library has <%= branch.getBooks().size() %> books in its database. It is located at <%= branch.getBranchAddress()  %>. Help keep it up-to-date! </p>
        <p><button type = "button"  class="btn btn-primary" data-toggle="modal" data-target="#updateBranchModal " role="button" href="librarianupdatebranchdetails.jsp?branchId=<%=branch.getBranchId()%>"> Update Details Now</button></p>
      </div>
    </div>
  </div>
</div>

<div class="modal fade bs-example-modal-sm" id = "updateBranchModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      ...
    </div>
  </div>
</div>

<%
	request.setAttribute("branch", branch);
%>
<%
	request.getSession().setAttribute("branch", branch);
%>

