<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>
<%
	Publisher publisher = new Publisher();
	AdminService service = new AdminService();
	Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
	publisher = service.returnPublisherObjectById(publisherId).get(0);
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">Edit Publisher <%=publisher.getPublisherName()%></h4>
</div>
<form action="updatePublisher" method="post">
	<div class="modal-body">
		<p>You can add details here&hellip;</p>
		Publisher Name <input type="text" name="publisherName"
			value="<%=publisher.getPublisherName()%>"> <br /> <br/> Publisher Address <input
			type="text" name="publisherAddress"
			value="<%=publisher.getPublisherAddress()%>"> > <br /> <br/> Publisher Phone <input 
			type = "text" name = "publisherPhone" value = "<%=publisher.getPublisherPhone()%>"> 
	</div>
	<div class="modal-footer">
			<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>"> <br />
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">Save changes</button>
	</div>
</form>
<div>