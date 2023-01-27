package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	private Integer id;
	private String name;
	
	public Customer(ResultSet rs) {
		try {
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
		} catch (SQLException e) {
			System.out.println("Cstomer dto error");
		}
	}
	
	public Customer() {
		
	}
	
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	
	
}
