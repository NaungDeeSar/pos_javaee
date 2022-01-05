package comm.pos.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class SaleDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	//private int subPrice;
	private int subQty;
	@ManyToOne(optional = false)	
	@JoinColumn(name = "sale_id", referencedColumnName = "id")
	private Sale sale;
	@ManyToOne	
	@JoinColumn(name = "Item_id", referencedColumnName = "id")
	private Item Item;
	
	private static final long serialVersionUID = 1L;

	public SaleDetail() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubQty() {
		return subQty;
	}
	public void setSubQty(int subQty) {
		this.subQty = subQty;
	}
	
	public Item getItem() {
		return Item;
	}
	public void setItem(Item item) {
		Item = item;
	}
	
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
