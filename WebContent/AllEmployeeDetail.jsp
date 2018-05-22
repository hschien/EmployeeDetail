<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>All Employee Detail</title>
</head>
<body>
	<center>
	<h1>All Employee Detail</h1>
	<h2>
	        <a href="/new">Add New Employees</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Employees</a>
            &nbsp;&nbsp;&nbsp;
            Export to Excel
            &nbsp;&nbsp;&nbsp;
            <a href="http://localhost:8080/EmployeeDetail/HomePage.html">Return to HomePage</a>
    </h2>
	</center>
	<div align="center">
        <table border="1" cellpadding="5">
            <caption>List of Employees</caption>
            <tr>
            	<th>EmployeeID</th>
            	<th>Name</th>
                <th>Designation</th>
                <th>Salary</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="employee" items="${listEmployee}">
                <tr>
                    <td><a href="/edit?id=<c:out value='${employee.id}' />">
                    	<c:out value="${employee.employeeid}" /></a>
                    </td>
                    <td><c:out value="${employee.name}" /></td>
                    <td><c:out value="${employee.designation}" /></td>
                    <td><c:out value="${employee.salary}" /></td>
                    <td><c:out value="${employee.email}" /></td>
                    <td>
                        <a href="/delete?id=<c:out value='${employee.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>