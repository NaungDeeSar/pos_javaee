<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

<style type="text/css">

.menu .navbar-nav .nav-item .nav-link {
  padding: 0 2rem;
  font-size: 1.1rem;
  color: gay;
}
.logo img{
 margin-top:-15px;
width:80px;
}
</style>
</head>
<body>

<c:url value="./assets/js/jquery.min.js" var="jqjs"></c:url>
<c:url value="./assets/css/bootstrap.min.css" var="bsCss"></c:url>
<c:url value="./assets/fontawesome/css/all.min.css" var="ftCss"></c:url>

<link href="${bsCss}" rel="stylesheet" type="text/css"/>
<link href="${ftCss}" rel="stylesheet" type="text/css"/>
<link href="./css/style.css" rel="stylesheet" type="text/css"/>
<script src="${jqjs}"></script>

<div class="container">
<nav class="container navbar navbar-expand-lg navbar-dark  bg-secondary menu shadow">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
       <a class="nav-link logo" href="#">
              
        <img src="./assets/images/log.png"/>
       
          </a>
      <ul class="navbar-nav">
      
        <li class="nav-item" >
        <c:url value="/home" var="home"></c:url>
          <a class="nav-link ${empty title ? 'active': '' }" aria-current="page" 
          href="${home}">
          <i class="fa fa-home fa-2x"></i>
           <br>
           <label>Home</label>
          </a>
        </li>
        <li class="nav-item">
        <c:url value="/item" var="items">
        </c:url>
          <a class="nav-link 
          ${(not empty title and title == 'items') ? 'active': ''}" href="${items }">
          <i class="fa fa-list-alt fa-2x" ></i>
          <br>
           <label> Items</label>
           </a>
        </li>
        <li class="nav-item">
        <c:url value="/add_item" var="additem"></c:url>
          <a class="nav-link ${(not empty title and title=='additem')  ? 'active': ''}" href="${additem}">
          <i class="fa fa-plus fa-2x"></i> 
          <br>
           <label>Add Item</label>
        
          </a>
        </li>
         <li class="nav-item">
         <c:url value="/sale_history" var="sale"></c:url>
          <a class="nav-link ${(not empty title and title == 'salehistory') ? 'active': ''}" href="${sale}">
          <i class="fa fa-gift fa-2x"></i>
          <br>
          <label>Sale</label> 
          </a>
        </li>
      
      
      </ul>
    </div>
  </div>
</nav>
</div>
</body>
</html>