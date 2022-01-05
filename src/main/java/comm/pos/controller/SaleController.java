package comm.pos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.pos.model.entity.Sale;
import comm.pos.model.entity.SaleDetail;
import comm.pos.model.entity.SaleDetailJSON;

import comm.pos.model.service.SaleService;
import comm.pos.utilities.JsonConverter;
@WebServlet(urlPatterns = {"/sale_history","/sale-detail","/search"},loadOnStartup = 1)
public class SaleController extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;
	
	private SaleService saleservice;
	@Override
	public void init() throws ServletException {	
		super.init();	
		Object object=getServletContext().getAttribute("EntityManagerFactory");
	    if(object==null) {
	    	emf=Persistence.createEntityManagerFactory("javaEE-14");
	    	getServletContext().setAttribute("EntityManagerFactory", object);
	    }
	  
	    saleservice=new  SaleService(emf.createEntityManager()); 
		  	
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
		
		
	}
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	  String path = req.getServletPath();
    	  if("/search".equals(path)) {
    		    String from = req.getParameter("from");
    			String to = req.getParameter("to");
    			List <Sale> list=saleservice.searchWithDate(from,to);
    			 req.setAttribute("title", "salehistory");
    			req.setAttribute("sale", list);    	
    			getServletContext().getRequestDispatcher("/sale_history.jsp").forward(req, resp);
    	  		
    	  }else	if ("/sale-detail".equals(path)) {
  			getSaleDetail(req,resp);
  		}else if ("/sale_history".equals(path)) {  			
  			
  			List<Sale> list = saleservice.findAll();
  			 req.setAttribute("title", "salehistory");
  			req.setAttribute("sale", list);
  			getServletContext().getRequestDispatcher("/sale_history.jsp").forward(req, resp);
  		
  		}
    	 
    }
	private void getSaleDetail(HttpServletRequest req, HttpServletResponse resp) {
       resp.setContentType("application/json;charset=UTF-8");		
		String saleId = req.getParameter("id");
		System.out.println("-------------------------------");
		System.out.println("Sale id using ajax = "+saleId);
		
		Sale sale = saleservice.findById(Integer.parseInt(saleId));
		System.out.println("-------------------------------");
		System.out.println("Saledetail id using ajax = "+sale.getDetaiList().get(0).getItem().getItem_name());
		List<SaleDetailJSON> details=new ArrayList<>();
		for(SaleDetail sd:sale.getDetaiList()) {
			SaleDetailJSON s=new SaleDetailJSON(sd.getItem(),sd.getSubQty());
			details.add(s);
		}
		
		var converter = new JsonConverter(); 
		String output = converter.convertToJson(details);
		System.out.println("-------------------------------");
		System.out.println("Sale Detail = "+ output);
		//resp.getWriter().print(output);
		try {
			resp.getWriter().print(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
