<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${item.id !=null ? 'Edit Item':'Add Item' }</title>
</head>
<body>
<div class="container-fluid">
 	<jsp:include page="/assets/menu.jsp"></jsp:include>
 </div>
 <div class="container">
 <div class="row">
   <h3 class="my-3">  
   ${item.id !=null ? 'Edit Item':'Add Item' }
   </h3>
   <hr>
  <div class="col-md-6">
   <c:url value="/item-save" var="add"></c:url>
    <form action="${add}" method="post">    
    <input type="hidden" name="itemid" value="${item.id }"> 
  	<div class="mb-3">
  		<label for="name" class="text-success"><i class="fas fa-comment-medical"></i> Item Name</label>
  		<input type="text" value="${item.item_name}" name="name"  placeholder="Enter Item Name" class="form-control" required="required">
  	</div>
  	
  		<div class="mb-3">
  		<label for="price" class="text-success"><i class="fas fa-comment-dollar"></i> Price</label>
  		<input type="number" value="${item.price}" name="price" placeholder="Enter Price Item" class="form-control" required="required">
  	</div> 	
  	
  		<div class="mb-3">
  		<label for="duration" class="text-success">
  		<i class="fas fa-clock"></i> Expired Date</label>
  		<input type="date" value="${item.expired_date}" placeholder="Enter Expired Date" name="expried" class="form-control" required="required">
  	    </div>
  	    
  	    	<div class="mb-3">
  		<label for="category" class="text-success"><i class="fas fa-biohazard"></i> Category</label>
  		<select id="level" name="category" class="form-control">
  		<option value="">Selected Category</option>   			
  			<c:forEach items="${categories}" var="c">
  			
  			<option value="${c.id}" 
  			selected="${item.category.id ==c.id ? 'selected' :'' }">${c.name }</option>
  			
  			</c:forEach>
  		</select>
  	      </div>
  	<button type="submit" class="btn btn-success text-white">
  	<i class="fa fa-plus"></i> Add Item</button>
  	</div>  	

  </form>
   </div>
 </div>
</div>
<br>
<div class="container-fluid">
 	<jsp:include page="/assets/footer.jsp"></jsp:include>
 </div>
</body>
</html>