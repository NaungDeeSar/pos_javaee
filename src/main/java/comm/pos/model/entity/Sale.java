package comm.pos.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@NamedQuery(name="Sale.getAll",query="SELECT s FROM Sale s  ORDER BY s.id DESC")

public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate saleDate;	
	@OneToMany(mappedBy = "sale", cascade = { PERSIST, MERGE, REMOVE })
	private List<SaleDetail> detaiList=new ArrayList<>();
	 public void addSaleItem(SaleDetail s) {
		 s.setSale(this);
		 detaiList.add(s);
	 }
	 public int getsubTotal() {
		return  detaiList.stream().mapToInt(sd->sd.getSubQty() * sd.getItem().getPrice()).sum();
	 }
	 public double getTax() {
		 return (getsubTotal() * 0.05);
	 }
	 public double getTotal() {
		 return (getsubTotal() +getTax());
	 }
		public int getTotalQty() {
			return detaiList.stream().mapToInt(sd->sd.getSubQty()).sum();
		}
	public Sale() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public LocalDate getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}
	
	
	public List<SaleDetail> getDetaiList() {
		return detaiList;
	}
	public void setDetaiList(List<SaleDetail> detaiList) {
		this.detaiList = detaiList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
