package comm.pos.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
@Entity
@Table(name="items")
@NamedQuery(name="Item.getAll",query="SELECT i FROM Item i  ORDER BY i.item_name DESC")

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private String item_name;
	private int price;
	private  LocalDate expired_date;
	@ManyToOne(fetch = LAZY, optional = false)
	@JoinColumn(name = "catId")	
	private Category category;
	@OneToMany(mappedBy = "Item")
	private List<SaleDetail> order_detail;
	
	public Item() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public LocalDate getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(LocalDate expired_date) {
		this.expired_date = expired_date;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<SaleDetail> getOrder_detail() {
		return order_detail;
	}
	public void setOrder_detail(List<SaleDetail> order_detail) {
		this.order_detail = order_detail;
	}
	
   
}
