package Week7;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OrderManagementGUI {
	private JFrame frame;
	private JTextField customerIdField;
	private JTextField productIdField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextArea orderHistoryArea;
	private CustomerDAO customerDAO;
	private ProductDAO productDAO;
	private OrderDAO orderDAO;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				OrderManagementGUI window = new OrderManagementGUI();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public OrderManagementGUI() {
		customerDAO = new CustomerDAO();
		productDAO = new ProductDAO();
		orderDAO = new OrderDAO();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setLocale(null);
		frame.getContentPane().setBackground(new Color(0, 128, 0));
		frame.setTitle("Order Management");
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setBounds(30, 30, 100, 25);
		frame.getContentPane().add(lblCustomerId);

		customerIdField = new JTextField();
		customerIdField.setBounds(140, 30, 200, 25);
		frame.getContentPane().add(customerIdField);
		customerIdField.setColumns(10);

		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setBounds(30, 70, 100, 25);
		frame.getContentPane().add(lblProductId);

		productIdField = new JTextField();
		productIdField.setBounds(140, 70, 200, 25);
		frame.getContentPane().add(productIdField);
		productIdField.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(30, 110, 100, 25);
		frame.getContentPane().add(lblQuantity);

		quantityField = new JTextField();
		quantityField.setBounds(140, 110, 200, 25);
		frame.getContentPane().add(quantityField);
		quantityField.setColumns(10);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(30, 150, 100, 25);
		frame.getContentPane().add(lblPrice);

		priceField = new JTextField();
		priceField.setBounds(140, 150, 200, 25);
		frame.getContentPane().add(priceField);
		priceField.setColumns(10);

		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.setBounds(140, 190, 200, 25);
		frame.getContentPane().add(btnAddOrder);

		JLabel lblOrderHistory = new JLabel("Order History:");
		lblOrderHistory.setBounds(30, 230, 100, 25);
		frame.getContentPane().add(lblOrderHistory);

		orderHistoryArea = new JTextArea();
		orderHistoryArea.setEditable(false);
		orderHistoryArea.setBounds(140, 230, 200, 100);
		frame.getContentPane().add(orderHistoryArea);

		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int customerId = Integer.parseInt(customerIdField.getText());
					int productId = Integer.parseInt(productIdField.getText());
					int quantity = Integer.parseInt(quantityField.getText());
					BigDecimal price = new BigDecimal(priceField.getText());

					Product product = productDAO.getProductById(productId);
					if (product != null && product.getStock() >= quantity) {
						Order newOrder = new Order();
						newOrder.setCustomerId(customerId);
						newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
						newOrder.setTotalAmount(price.multiply(BigDecimal.valueOf(quantity)));

						OrderItem orderItem = new OrderItem();
						orderItem.setProductId(productId);
						orderItem.setQuantity(quantity);
						orderItem.setPrice(price);

						orderDAO.createOrder(newOrder, List.of(orderItem));
						orderHistoryArea.setText("Order added successfully!");
					} else {
						orderHistoryArea.setText("Insufficient stock or invalid product.");
					}
				} catch (Exception ex) {
					orderHistoryArea.setText("Error: " + ex.getMessage());
				}
			}
		});

		// Load order history for a customer (example: view history)
		JButton btnViewHistory = new JButton("View Order History");
		btnViewHistory.setBounds(140, 310, 200, 25);
		frame.getContentPane().add(btnViewHistory);

		btnViewHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int customerId = Integer.parseInt(customerIdField.getText());
					List<Order> orders = orderDAO.getOrdersByCustomer(customerId);
					StringBuilder history = new StringBuilder();
					for (Order order : orders) {
						history.append("Order ID: ").append(order.getOrderId()).append("\n");
						history.append("Total Amount: ").append(order.getTotalAmount()).append("\n");
						history.append("Date: ").append(order.getOrderDate()).append("\n\n");
					}
					orderHistoryArea.setText(history.toString());
				} catch (Exception ex) {
					orderHistoryArea.setText("Error: " + ex.getMessage());
				}
			}
		});
	}
}
