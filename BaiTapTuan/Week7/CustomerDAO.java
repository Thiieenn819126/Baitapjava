package Week7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

	public List<Customer> getAllCustomers() {
		List<Customer> list = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {
			while (rs.next()) {
				Customer c = new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"), rs.getString("address"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Customer getCustomerById(int id) {
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers WHERE customer_id = ?")) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"), rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
