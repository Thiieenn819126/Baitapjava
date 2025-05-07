package Week7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

	public int createOrder(Order order, List<OrderItem> items) {
		String orderSQL = "INSERT INTO orders (customer_id, order_date, total_amount) VALUES (?, ?, ?)";
		String itemSQL = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection()) {
			conn.setAutoCommit(false);

			try (PreparedStatement ps = conn.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS)) {
				ps.setInt(1, order.getCustomerId());
				ps.setTimestamp(2, order.getOrderDate());
				ps.setBigDecimal(3, order.getTotalAmount());
				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int orderId = rs.getInt(1);
					for (OrderItem item : items) {
						try (PreparedStatement itemStmt = conn.prepareStatement(itemSQL)) {
							itemStmt.setInt(1, orderId);
							itemStmt.setInt(2, item.getProductId());
							itemStmt.setInt(3, item.getQuantity());
							itemStmt.setBigDecimal(4, item.getPrice());
							itemStmt.executeUpdate();
						}
					}
					conn.commit();
					return orderId;
				}
			} catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Order> getOrdersByCustomer(int customerId) {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT * FROM orders WHERE customer_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order(rs.getInt("order_id"), rs.getInt("customer_id"), rs.getTimestamp("order_date"),
						rs.getBigDecimal("total_amount"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
