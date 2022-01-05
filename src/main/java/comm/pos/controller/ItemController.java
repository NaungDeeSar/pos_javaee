package comm.pos.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.pos.model.entity.Item;
import comm.pos.model.service.CategoryService;
import comm.pos.model.service.ItemService;
@WebServlet(urlPatterns = {"/item","/add_item","/item-save","/edit_item"},loadOnStartup = 1)
public class ItemController extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	private CategoryService catservice;
	private ItemService itemservice;
	@Override
	public void init() throws ServletException {	
		super.init();	
		Object object=getServletContext().getAttribute("EntityManagerFactory");
	    if(object==null) {
	    	emf=Persistence.createEntityManagerFactory("javaEE-14");
	    	getServletContext().setAttribute("EntityManagerFactory", object);
	    }else {
	    	emf=(EntityManagerFactory) object;
	    }
	    catservice=new CategoryService(emf.createEntityManager());
	    itemservice=new ItemService(emf.createEntityManager());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action =req.getServletPath();	     
	     if("/item".equals(action)) {
	    	 //retrieve item
	    	 List<Item> list=itemservice.findAll();
	    	 //add list
	    	 req.setAttribute("itemlist", list);
	    	 //invoke othe site	    	 
	    	req.setAttribute("title", "items");
	    	getServletContext().getRequestDispatcher("/item.jsp").forward(req, resp);
	   		 
	     }else if("/add_item".equals(action) || "/edit_item".equals(action)) {
	    		String itemid=req.getParameter("id");
	    		
	    		if(itemid!=null && !itemid.isEmpty()) {
	    			//get existing item
	    			Item i=itemservice.findById(Integer.parseInt(itemid));
	    			
	    			//add to req
	    			req.setAttribute("item",i);
	    			
	    		}
	    		req.setAttribute("title", "additem");
	    		getServletContext().getRequestDispatcher("/add_item.jsp").forward(req,resp);
	    		
	     }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String price=req.getParameter("price");
		String expired=req.getParameter("expried");
		String catId=req.getParameter("category");
		String itemid=req.getParameter("itemid");
		//create item obj
		Item item=new Item();
		if(itemid !=null && !itemid.isEmpty()) {
			item=itemservice.findById(Integer.parseInt(itemid));
		}
		item.setItem_name(name);
		item.setPrice(Integer.parseInt(price));
		item.setExpired_date(expired!=null ? LocalDate.parse(expired) : null);
		item.setCategory(catservice.findById(Integer.parseInt(catId)));
		//save it to db
		itemservice.save(item);
		//invoke other site
		resp.sendRedirect(req.getContextPath().concat("/item"));
	}
}
