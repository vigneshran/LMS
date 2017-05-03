<%@ page import="com.gcit.lms.service.AdminService"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
	AdminService service = new AdminService();
	List<Loan> loans = service.readAllLoans();
%>
<%@include file="index.htm"%>
<div>
	<%
		Branch branch = new Branch();
		branch = (Branch) request.getSession().getAttribute("branch");
	%>
	<center>
		<div class="jumbotron">
			<h2>You have chosen to over-ride a book-loan date. Enter the
				number of days you want to over-ride it for in the list below:</h2>
		</div>
	</center>


	<div class="page-header"></div>
	<div class="row">
		<div class="col-lg">
			<table class="table">
				<thead>
					<tr>
						<th>Loan Date Out</th>

						<th>Loaned Book</th>

						<th>Borrower</th>

						<th>Borrowered Branch</th>

						<th>Current due-date</th>

						<th>Push it for:</th>

						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Loan l : loans) {
					%>
					<tr>
						<td>
							<%
								out.println(l.getDateOut1());
							%>


						</td>

						<td>
							<%
								int bookId = l.getBookId();
									Book book = service.returnBookObjectById(bookId).get(0);
									out.println(book.getBookName());
							%>
						</td>

						<td>
							<%
								Long cardNo = l.getCardNo();
									Borrower borrower = service.returnBorrowerByPK(cardNo).get(0);
									out.println(borrower.getBorrowerName());
							%> <br /> <%
 	out.println(l.getCardNo());
 %>

						</td>

						<td>
							<%
								int branchId = l.getBranchId();
									Branch branch1 = service.returnBranchObjectById(branchId).get(0);
									out.println(branch1.getBranchName());
							%>

						</td>

						<td>
							<%
								out.println(l.getDueDate());
							%>
						</td>

						<td>
							<form action="adminOverride" method="post">
								<input type="text" name="days">
						</td>
						<td><input type="hidden" name="branchId"
							value="<%=branchId%>"> <input type="hidden" name="cardNo"
							value="<%=cardNo%>"> <input type="hidden" name="dateOut"
							value="<%=l.getDateOut()%>"> <input type="hidden"
							name="bookId" value="<%=bookId%>">
							<button class="btn btn-primary" type="submit">Push
								DueDate Now</button></td>
						</form>

						<%
							}
						%>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
