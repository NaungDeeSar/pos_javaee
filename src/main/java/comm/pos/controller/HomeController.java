package comm.pos.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comm.pos.model.entity.Category;
import comm.pos.model.entity.Item;
import comm.pos.model.entity.Sale;
import comm.pos.model.entity.SaleDetail;
import comm.pos.model.service.CategoryService;
import comm.pos.model.service.ItemService;
import comm.pos.model.service.SaleService;


@WebServlet(urlPatterns = {"","/home","/add-to-cart","/cart-action"},loadOnStartup = 1)
public class HomeController extends HttpServlet{	
	private static final long serialVersionUID = 1L;	
	 private EntityManagerFactory emf;
	 private CategoryService catservice;
	 private ItemService itemservice;
	 private SaleService saleservice;

@Override
public void init() throws ServletException {	
	super.init();	
	Object object=getServletContext().getAttribute("EntityManagerFactory");
    if(object==null) {
    	emf=Persistence.createEntityManagerFactory("javaEE-14");
    	getServletContext().setAttribute("EntityManagerFactory", object);
    }
    catservice=new  CategoryService(emf.createEntityManager()); 
    itemservice=new  ItemService(emf.createEntityManager()); 
    saleservice=new  SaleService(emf.createEntityManager()); 
	   List <Category> list=catservice.findAll();	    	   	 
	   getServletContext().setAttribute("categories", list);	
}
	
@Override
public void destroy() {
	if(emf!=null && emf.isOpen())
		emf.close();
		
}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String action =req.getServletPath();
		  if("/add-to-cart".equals(action)) {	
			  //retrieve item
		    	 List<Item> list=itemservice.findAll();
		    	 //add list
		    	 req.setAttribute("items", list);
		    	 //invoke index.jsp
			 addTocat(req);		  
			  
		  }else if("/home".equals(action) || "".equals(action)) {	
		    	 //retrieve item
		    	 List<Item> list=itemservice.findAll();
		    	 //add list
		    	 req.setAttribute("items", list);
		    	 //invoke index.jsp
		    	
		     }
			getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    		
		     
		
	}

private void addTocat(HttpServletRequest req) {
	  String itemid=req.getParameter("id");
	  int currentitemId=Integer.parseInt(itemid);
	  //get cart info
	  HttpSession session=req.getSession(true);
	  Sale sale= (Sale) session.getAttribute("cart");
	  if(sale==null)
		  sale=new Sale();
	  //verify
	  boolean already_exist=false;
	  List<SaleDetail> saleitemList=sale.getDetaiList();
	  for(SaleDetail sd:saleitemList) {
		  if(sd.getItem().getId()==currentitemId) {
			  sd.setSubQty(sd.getSubQty()+1);
			  already_exist=true;
			  break;
		  }
	  }
	  //new sale item	  
	  if(!already_exist) {
		  Item item=itemservice.findById(currentitemId);
		  SaleDetail newsaleitem=new SaleDetail();
		  newsaleitem.setItem(item);
		  newsaleitem.setSubQty(1);
		  sale.addSaleItem(newsaleitem);
		  
	  }
	  session.setAttribute("cart", sale);  
	
}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 String action=req.getParameter("btnAction");
	 HttpSession session=req.getSession(true);
	 if("Paid".equals(action)) {
		 Sale sale=(Sale) session.getAttribute("cart");
		 if(sale !=null && !sale.getDetaiList().isEmpty()) {
			 //save to db
			 saleservice.save(sale);
		 }
		 
	 }
	 session.removeAttribute("cart");
	 resp.sendRedirect(req.getContextPath().concat("/home"));
}

}
