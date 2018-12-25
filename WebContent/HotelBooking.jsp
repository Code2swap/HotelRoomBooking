<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Hotel Booking Form</title>
		<style type="text/css">
			table {
				/* width: 400px; */
				/* height: 200px; */
				border: 1px solid black;
				table-layout: auto;
				border-collapse: collapse;
				/* border-collapse : separate; */
			}
			td {
				height: 30px;
			}
			td.tdError {
				
				border: none;
			}
			td.tdBtn {
				text-align: center;
			}
			.btn {
				cursor: pointer;
				border: none;
			}
			input {
				display: table-cell;
				height: 100%;
				width: 97%;
			}

		</style>
		<script>
			/* 
				either use only js validators or java bean validator(hibernate)
				not both together
			*/
			function validateForm() {
				var errorMsg = "must be greater than equal to from date";
				var presentOrFututre = "must be greater than or euqual to today"
				var from_string = document.getElementById("fdate").value;
				var to_string = document.getElementById("tdate").value;
				var cust_name = document.getElementById("cname").value;
				/* 
					for validating available rooms using js u have to connect to db
					but it cant be possible directly using js, u have to use jquery,
					ajax or php
				*/
				var no_of_rooms = document.getElementById("nrooms").value;
				
				if(!cust_name) {
					document.getElementById("cust_name").innerHTML = "required";
					return false;
				} else {
					document.getElementById("cust_name").innerHTML = "";
				}
				if(!from_string) {
					document.getElementById("from_date").innerHTML = "required";
					return false;
				} else {
					document.getElementById("from_date").innerHTML = "";
				}
				if(!to_string) {
					document.getElementById("to_date").innerHTML = "required";
					return false;
				} else {
					document.getElementById("to_date").innerHTML = "";
				}
				var from_date = new Date(from_string);
				var to_date = new Date(to_string);
				var today = new Date();
				if(from_date.getTime() < today.getTime()) {
					document.getElementById("from_date").innerHTML = presentOrFututre;
					document.getElementById("to_date").innerHTML = "";
					return false;
				}
				if(from_date.getTime() > to_date.getTime()) {
					document.getElementById("from_date").innerHTML = "";
					document.getElementById("to_date").innerHTML = errorMsg;
					return false;
				}
				return true;
			}
			function getAvailableRooms() {
				db.transactional(function(transaction) {
					transaction.execute
				})
			}
		</script>
	</head>

	<body style="background-color: pink">
		<h3 align="center">Hotel Booking</h3>
		<form:form action="bookHotel.obj" onsubmit="return validateForm()" modelAttribute="bookingDetail">
			<table align="center" bgcolor="#EFC8C8">
				<tr>
					<td>Hotel Id</td>
					<td><form:input path="hotelId" value="${hotel.id}" readonly="true" /></td>
					<td class="tdError"></td>
				</tr>
				<tr>
					<td>Hotel Name</td>
					<td><input value="${hotel.name}" readonly></td>
					<td class="tdError"></td>
				</tr>
				<tr>
					<td>Customer Name</td>
					<td><form:input path="custName" id="cname" /></td>
					<td id="cust_name" class="tdError"><form:errors path="custName" /></td>
				</tr>
				<tr>
					<td>From Date</td>
					<td><form:input path="fromDate" id="fdate" type="date" /></td>
					<td id="from_date" class="tdError"><form:errors path="fromDate" /></td>
				</tr>
				<tr>
					<td>To Date</td>
					<td><form:input path="toDate" id="tdate" type="date" /></td>
					<td id="to_date" class="tdError"><form:errors path="toDate" /></td>
				</tr>
				<tr>
					<td>No of Rooms</td>
					<td><form:input path="noOfRooms" id="nrooms" /></td>
					<td id="no_of_rooms" class="tdError"><form:errors path="noOfRooms" /></td>
				</tr>
				<tr>
					<td class="tdBtn"><input class="btn" type="reset" value="Clear" /></td>
					<td class="tdBtn"><input class="btn" type="submit" value="Book Now" /></td>
					<td class="tdError"></td>
				</tr>
			</table>
		</form:form>
	
	</body>
</html>


