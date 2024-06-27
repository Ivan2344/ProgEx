package frauas.zimmermann.prgx;

import java.sql.Date;

public class Orders {
	private int id;
    private int user_id;
    private int employee_id;
    private Date order_date;
    private String status;
    private int total;
 //   private int subtotal;
    private float tax;
    private int discount;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
//	public int getSubtotal() {
//		return subtotal;
//	}
//	public void setSubtotal(int subtotal) {
//		this.subtotal = subtotal;
//	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
