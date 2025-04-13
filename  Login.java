package universityms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Login extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public Login() {
		getContentPane().setLayout(null);
		setSize(450,300);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(184, 32, 75, 35);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(58, 91, 332, 29);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(58, 164, 332, 29);
		getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(58, 68, 45, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("pass");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(58, 145, 45, 13);
		getContentPane().add(lblNewLabel_1_1);
	}
	
	public static void main(String[] args) {
		Login a=new Login();
		a.setVisible(true);
	}
}
