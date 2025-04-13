package universityms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	private Student student =new Student();
    private static final String URL = "jdbc:mysql://localhost:3306/universityms";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
public static String generateEmail(String name) {
    String[] parts = name.toLowerCase().split(" ");
    String email = parts[parts.length - 1] + parts[0].charAt(0) + "@vku.udn.vn";
    return email;
}

    public static void createStudent(String name, int age, double gpa,int ID) {
        String email = generateEmail(name);
        String sql = "INSERT INTO student (StudentID,Name, Age, Email, GPA) VALUES (?, ?, ?, ?,?)";
        
        try (Connection conn = DatabaseConnection.connect();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setInt(1,ID);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, email);
            stmt.setDouble(5, gpa);
            stmt.executeUpdate();
            System.out.println("Thêm sinh viên thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    
    public static void getAllStudents() {
        String sql = "SELECT * FROM student";
        
        try (Connection conn = DatabaseConnection.connect(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("StudentID") + ", Name: " + rs.getString("Name") +",Age" +rs.getInt("Age")+
                        ", Email: " + rs.getString("Email") + ", GPA: " + rs.getDouble("GPA"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
