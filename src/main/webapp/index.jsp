<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>POS HOME</title>
</head>
<body>
 <div class="container-fluid">
 	<jsp:include page="/assets/menu.jsp"></jsp:include>
 </div>
 <div class="container">

  <div class="row">
     <div class="col-md-8">
     <h4 class="my-2"> All Items</h4>
     <hr>     
     <table class="table">
 	<thead>
 	  <tr>
 	  <th>ID</th>
 	  <th>Product</th>
 	  <th>Price</th>
 	  <th>Expired Date</th>
 	  <th>Category</th>
 	  <th></th>
 	 
 	 </tr>
 	</thead>
 	<tbody>
 	<c:forEach items="${items}" var="i">
 	<tr> 	
 	<td>${i.id}</td>
 	<td>${i.item_name}</td>
 	<td>${i.price } Ks</td>
 	<td>${i.expired_date }</td>
 	<td>${i.category.name}</td>
 	<td>
 	 <c:url value="/add-to-cart" var="addtocart">
      <c:param name="id" value="${i.id}"></c:param>      
      </c:url>
 	<a  href="${addtocart }" class="btn btn-outline-success">
 	<i class="fa fa-shopping-cart text-info"> </i> Add To Sale</a>
 	</td>
 	
 	</tr>
 	</c:forEach>
 	</tbody>
 	</table>
     </div>
       <div class="col-md-4 my-5">
          <div class="card">
          <h5 class="card-header">Sale Detail</h5>
			  <div class="card-body">
			  <table class="table table-hover">
			 <thead>
			    <tr>
			      <th scope="col">Product</th>
			      <th scope="col">Price</th>
			      <th scope="col">Qty</th>
			      <th scope="col">Total</th>
			    </tr>
			  </thead>
			    <tbody>
			    <c:forEach items="${cart.detaiList}" var="sd">
			    <tr>
			      <td>${sd.item.item_name}</td>
			      <td>${sd.item.price} Ks</td>
			      <td>${sd.subQty }</td>
			      <td>${sd.item.price * sd.subQty} Ks</td>
			    </tr>			   
			    </c:forEach>
			     <tr class="${empty cart ? 'd-none':'' }">
			    <td colspan="3" align="right">SubTotal :</td>
			      <td>${cart.subTotal}Ks</td>
			    </tr>
			    <tr  class="${empty cart ? 'd-none':'' }">
			    <td colspan="3" align="right">Tax :</td>
			      <td>${cart.tax}Ks</td>
			    </tr>
			    <tr  class="${empty cart ? 'd-none':'' }">
			    <td colspan="3" align="right">Total :</td>
			      <td>${cart.total}Ks</td>
			    </tr>
    		</tbody>
 
				</table>			    
			    
			  </div>
			  <div class="card-footer">
			  <c:url value="/cart-action" var="action"></c:url>
			   <form action="${action}" method="post">
			  <div class="row">
			  
			    <div class="col-md-6">
			   <input class="form-controller btn btn-success w-100" type="submit" name="btnAction" value="Paid">
			  
			  </div>
			    <div class="col-md-6">
			    <input class="form-controller btn btn-danger w-100" type="submit" name="btnAction" value="Clear">
			  
			  </div>
			
			    
			  </div>
			     </form>
			  </div>
			</div>
       </div>
  </div>
 </div>
<div class="container-fluid">
 	<jsp:include page="/assets/footer.jsp"></jsp:include>
 </div>
</body>
</html>