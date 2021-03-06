<%@ page import="com.gcit.lms.service.AdminLibrarian"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@include file="index.htm"%>



<center>
	<div class="jumbotron">
		<h2>Hey, Admin! What do you want to do today?</h2>
	</div>
</center>


<div class="row">
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Book Services</h3>
				<p>We have a wide variety of books. Here, you can add, or
					delete, or update our books that help keep our database clear and
					ready to serve</p>
				<p>
					<a href="adminbookservices.jsp?" class="btn btn-primary"
						role="button"> Enter Book Services Now</a>
				</p>
			</div>
		</div>
	</div>
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Author Services</h3>
				<p>Before you add a book, make sure you add an author to connect
					him/her with the book! Keep everything connected! You can also
					update or delete existing authors with just one click!</p>
				<p>
					<a href="adminauthorservices.jsp" class="btn btn-primary"
						role="button"> Enter Author Services Now</a>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Library Branch Services</h3>
				<p>We expand and build more and more as hunger for books
					increase. Whether we like it or not, changes happen to existing
					ones too--keep us all updated and together!</p>
				<p>
					<a href="adminbranchservices.jsp" class="btn btn-primary"
						role="button"> Enter Library Branch Services Now</a>
				</p>
			</div>
		</div>
	</div>
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Over-ride Services</h3>
				<p>We run late, we miss dead-lines: it's all part of being
					human. We forgive people here: help us over-ride bool-loan due
					dates because we understand.</p>
				<p>
					<a href="adminoverride.jsp" class="btn btn-primary"
						role="button"> Enter Override Services Now</a>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Publisher Services</h3>
				<p>The real heores of library--publishers: help keep our
					database up-to-date with their addresses and phone number, so we
					keep expanding.</p>
				<p>
					<a href="adminpublisherservices.jsp" class="btn btn-primary"
						role="button"> Enter Publisher Services Now</a>
				</p>
			</div>
		</div>
	</div>
	<div class="col-lg-6">
		<div class="thumbnail">
			<div class="caption">
				<h3>Borrower Services</h3>
				<p>We add a lot of people everyday. We also need to keep track
					of their records--like their addresses to wish them happy birthday
					once a year. Also, there are rogue ones out there we need delete,
					too!</p>
				<p>
					<a href="adminborrowerservices.jsp" class="btn btn-primary"
						role="button"> Enter Borrower Services Now</a>
				</p>
			</div>
		</div>
	</div>
</div>







<div class="modal fade bs-example-modal-sm" id="updateBranchModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>