package BaiTap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriteFile {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String sourceFile = "a.txt";
        String targetFile = "b.txt";

        // Đọc nội dung từ file a.txt
        try (FileInputStream fis = new FileInputStream(sourceFile)) {
            // Đọc toàn bộ nội dung file
            byte[] data = new byte[fis.available()];
            fis.read(data);
            
            // Chuyển byte thành chuỗi và hiển thị
            String content = new String(data);
            System.out.println("Nội dung file a.txt:");
            System.out.println(content);

            // Ghi nội dung vào file b.txt
        }
    }
}