package comm.pos.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import comm.pos.model.entity.Category;


public class CategoryService {
	private EntityManager em;
	public  CategoryService (EntityManager em) {
		this.em=em;
	}
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("category.getAll",Category.class).getResultList();
	}
	public Category findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Category.class, id);
	}

	

	
	
}
