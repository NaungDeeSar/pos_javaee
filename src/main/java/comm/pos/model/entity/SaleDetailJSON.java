package comm.pos.model.entity;

import java.io.Serializable;

public class SaleDetailJSON implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int subQty;
	private Item item;
	public SaleDetailJSON() {
		
	}
	public SaleDetailJSON(Item item,int qty) {
		this.item=item;
		this.subQty=qty;
	}
	public int getId() {
		return id;
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
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
}