<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items</title>
</head>
<body>
<div class="container-fluid">
 	<jsp:include page="/assets/menu.jsp"></jsp:include>
 </div>
 <div class="container">
 	<h4 class="my-2">All Items</h4>
 	<hr>
 	<table class="table">
 	<thead>
 	  <tr>
 	  <th>ID</th>
 	  <th>Product</th>
 	  <th>Price</th>
 	  <th>Expired Date</th>
 	  <th>Category</th>
 	  <th>Action</th>
 	 </tr>
 	</thead>
 	<tbody>
 	<c:forEach items="${itemlist}" var="i">
 	<tr> 	
 	<td>${i.id}</td>
 	<td>${i.item_name}</td>
 	<td>${i.price }</td>
 	<td>${i.expired_date }</td>
 	<td>${i.category.name}</td>
 	 <td>
         <c:url value="/edit_item" var="edit">
      <c:param name="id" value="${i.id}"></c:param>      
      </c:url>
      <a href="${edit}"  type="button" class="btn btn-outline-success">
      <i class="fa fa-edit"></i>Edit
      </a>
      <c:url value="/remove_item" var="remove">
      <c:param name="studentId" value="${i.id}"></c:param>
      
      </c:url>
      
      <a href="${remove}"  type="button" class="btn btn-outline-danger">
      <i class="fa fa-trash"></i></a>
     
       </td>
 	</tr>
 	</c:forEach>
 	</tbody>
 	</table>
 </div>
 <div class="container-fluid">
 	<jsp:include page="/assets/footer.jsp"></jsp:include>
 </div>
</body>
</html>