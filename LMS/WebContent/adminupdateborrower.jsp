<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>
<%
	Borrower borrower = new Borrower();
	AdminService service = new AdminService();
	Long cardNo = Long.parseLong(request.getParameter("cardNo"));
	borrower = service.returnBorrowerByPK(cardNo).get(0);
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">Edit Borrower <%=borrower.getBorrowerName()%></h4>
</div>
<form action="updateBorrower" method="post">
	<div class="modal-body">
		<p>You can add details here&hellip;</p>
		Borrower Name <input type="text" name="borrowerName"
			value="<%=borrower.getBorrowerName()%>"> <br /> <br/> Borrower Address <input
			type="text" name="borrowerAddress"
			value="<%=borrower.getBorrowerAddress()%>"> > <br /> <br/> Borrower Phone <input 
			type = "text" name = "borrowerPhone" value = "<%=borrower.getBorrowerPhone()%>"> 
	</div>
	<div class="modal-footer">
			<input type="hidden" name="cardNo" value="<%=borrower.getCardNo()%>"> <br />
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">Save changes</button>
	</div>
</form>
<div>