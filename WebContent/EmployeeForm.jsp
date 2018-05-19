<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<center>
        <h1>Employees Management</h1>
        <h2>
            <a href="/new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Employees</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${employee != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${employee == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                    <c:if test="${employee != null}">
                       <h2> Edit Book</h2>
                    </c:if>
                    <c:if test="${employee == null}">
                      <h2>   Add New Book</h2>
                    </c:if>
            </caption>
                <c:if test="${employee != null}">
                    <input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
                </c:if> 
            <tr>
                <th>ID: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${employee.employeeid}' />"
                        />
                </td>
            </tr>          
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${employee.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Designation: </th>
                <td>
                    <input type="text" name="designation" size="45"
                            value="<c:out value='${employee.designation}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Salary: </th>
                <td>
                    <input type="text" name="salary" size="45"
                            value="<c:out value='${employee.price}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${employee.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>