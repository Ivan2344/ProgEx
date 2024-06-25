package frauas.zimmermann.prgx;

import java.sql.Timestamp;


public class Soap {

	private int id;
	private int EAN;
	private String title;
	private String category;
	private double price;
	private Timestamp created_at;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEAN() {
		return EAN;
	}
	public void setEAN(int eAN) {
		EAN = eAN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Timestamp getCreatedAt() {
        return created_at;
	}

	public void setCreatedAt(Timestamp created_at) {
		this.created_at = new Timestamp(System.currentTimeMillis());
	}
}