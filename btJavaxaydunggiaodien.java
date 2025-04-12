package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class btJavaxaydunggiaodien extends Application {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button importButton;

    @FXML
    private Label messageLabel;

    // Static class để kết nối cơ sở dữ liệu
    private static class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/lgs";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        public static Connection getConnection() throws SQLException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Không tìm thấy driver JDBC: " + e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/login.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 400); // Tăng kích thước cho phù hợp FXML mới
            primaryStage.setScene(scene);
            primaryStage.setTitle("Đăng Nhập");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    messageLabel.setText("Đăng nhập thành công!");
                    showUserListAlert(); // Hiển thị danh sách qua Alert
                } else {
                    messageLabel.setText("Mật khẩu không đúng!");
                }
            } else {
                messageLabel.setText("Tài khoản không tồn tại!");
            }
        } catch (SQLException e) {
            messageLabel.setText("Lỗi: " + e.getMessage());
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                messageLabel.setText("Tài khoản đã tồn tại!");
                return;
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, hashedPassword);
            insertStmt.executeUpdate();

            messageLabel.setText("Đăng ký thành công!");
            showUserListAlert(); // Hiển thị danh sách qua Alert
        } catch (SQLException e) {
            messageLabel.setText("Lỗi: " + e.getMessage());
        }
    }

    @FXML
    private void handleExport() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT username FROM users";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            while (rs.next()) {
                Element userElement = doc.createElement("user");
                rootElement.appendChild(userElement);

                Element usernameElement = doc.createElement("username");
                usernameElement.appendChild(doc.createTextNode(rs.getString("username")));
                userElement.appendChild(usernameElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("users.xml"));
            transformer.transform(source, result);

            messageLabel.setText("Xuất danh sách người dùng ra users.xml thành công!");
        } catch (Exception e) {
            messageLabel.setText("Lỗi khi xuất XML: " + e.getMessage());
        }
    }

    @FXML
    private void handleImport() {
        try {
            File xmlFile = new File("users.xml");
            if (!xmlFile.exists()) {
                messageLabel.setText("File users.xml không tồn tại!");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList userList = doc.getElementsByTagName("user");
            try (Connection conn = DatabaseConnection.getConnection()) {
                for (int i = 0; i < userList.getLength(); i++) {
                    Element user = (Element) userList.item(i);
                    String username = user.getElementsByTagName("username").item(0).getTextContent();

                    String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                    checkStmt.setString(1, username);
                    ResultSet rs = checkStmt.executeQuery();
                    rs.next();
                    if (rs.getInt(1) == 0) {
                        String hashedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
                        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                        insertStmt.setString(1, username);
                        insertStmt.setString(2, hashedPassword);
                        insertStmt.executeUpdate();
                    }
                }
            }

            messageLabel.setText("Nhập dữ liệu từ users.xml thành công!");
            showUserListAlert(); // Hiển thị danh sách qua Alert
        } catch (Exception e) {
            messageLabel.setText("Lỗi khi nhập XML: " + e.getMessage());
        }
    }

    private void showUserListAlert() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT username FROM users";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            StringBuilder userList = new StringBuilder("Danh sách người dùng:\n");
            while (rs.next()) {
                userList.append(rs.getString("username")).append("\n");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Danh Sách Người Dùng");
            alert.setHeaderText(null);
            alert.setContentText(userList.toString());
            alert.showAndWait();
        } catch (SQLException e) {
            messageLabel.setText("Lỗi khi hiển thị danh sách: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}