package frauas.zimmermann.prgx;

import java.sql.Date;
import java.sql.Timestamp;

public class Customers {
	int id;
	String address;
	String email;
	String password;
	String name;
	String city;
	Date birth_date;
    Timestamp created_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getBirthDate() {
	    return birth_date;
	    }

	public void setBirthDate(Date birth_date) {
	    this.birth_date = birth_date;
	    }

	public Timestamp getCreatedAt() {
	    return created_at;
	 }

	public void setCreatedAt(Timestamp created_at) {
	    this.created_at = created_at;
	 }
	
}
