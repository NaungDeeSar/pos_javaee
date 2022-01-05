<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sale Histroy</title>
</head>
<body>
<div class="container-fluid">
 	<jsp:include page="/assets/menu.jsp"></jsp:include>
 </div>
 <div class="container">
 	<h4 class="my-2">Sale-History</h4>
 	<hr>
  <div class="row">
     <div class="col-md-8 offset-2 py-3">
     <c:url value="/search" var="search"></c:url>
     <form action="${search}" method="get">
      <div class="row">
		  <div class="col-auto">
		    <label for="form" class="col-form-label">Form :</label>
		  </div>
		  <div class="col-auto">		    
		     <input value="${param.from }" type="date" class="form-control" name="from">   
		  </div>
		         
		         <div class="col-auto">
		    <label for="to" class="col-form-label">To :</label>
		  </div>
		  <div class="col-auto">		    
		     <input  value="${param.to }"type="date" class="form-control" name="to">   
		  </div> 
		  <div class="col-auto">	
		  <button class="btn btn-success form-control" type="submit">Search</button>
		  </div>
      </div>
    
    
     </form>
     </div>
 </div>
 	<table class="table table-hover my-3">
     <thead>
     <tr>
     <th>Id</th>
     <th>Sale Date</th>
     <th>Qty</th>
     <th>Tax</th>
     <th>Total Balance</th>
     <th></th>
     </tr>
     </thead>
     <tbody id="table">
      <c:forEach items="${sale}" var="s">
       <tr>
           						<td>${s.id}</td>
								<td>${s.saleDate}</td>
								<td>${s.totalQty}</td>
								<td>${s.tax} Ks</td>
								<td>${s.total} Ks</td>
            <td>          
          
           <a href="" class="detail" data-bs-toggle="modal" data-id="${s.id}" data-bs-target="#exampleModal">
           <i class="fa fa-eye"></i></a>
            </td>
      
      </tr>
      </c:forEach>
     
     </tbody>
</table>
 </div>
  <div class="container-fluid">
 	<jsp:include page="/assets/footer.jsp"></jsp:include>
 </div>
 <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-secondary shadow text-white">
        <h5 class="modal-title" id="exampleModalLabel">Sale Item</h5>
        <button type="button" class="btn-close bg-white text-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
         <table class="table table-bordered">
				<thead>
					<tr>
						<th>Product</th>
						<th>Price</th>
						<th>Qty</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody id="tbody">
					
				</tbody>
			</table>
      </div>
      
  </div>
</div>
	<script type="text/javascript">
		$(document).ready(function(){
			//alert("ok");
			$('#table').on('click','.detail',function(){
				var id=$(this).data('id');
				//alert(id);
				$.get('sale-detail?id='+id,function(responseJson){
				 	
					//console.log(typeof responseJson);
					var saledetailList=JSON.parse(JSON.stringify(responseJson));
					var html = "";
					//console.log(typeof saledetailList);
					$.each(saledetailList,function(i,v){
						/* console.log(v.product); */
						html += '<tr>'+
									'<td>'+v.product+'</td>'+
									'<td>'+v.price+' Ks</td>'+
									'<td>'+v.qty+'</td>'+
									'<td>'+v.price * v.qty+' Ks</td>'+
								'</tr>';
					})
					$('#tbody').html(html);
				}); 
			
				
			})
			
	    })
	</script>
	
</body>
</html>