package Week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lab6 {
	private static final String OUTPUT_FILE = "output.txt";
	private static final Object lock = new Object();

	public static void main(String[] args) {
		String[] danhSachTep = { "source.txt", "dest.txt" };
		ExecutorService executor = Executors.newFixedThreadPool(danhSachTep.length);
		for (String tenTep : danhSachTep) {
			executor.execute(() -> docVaGhiTep(tenTep));
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		System.out.println("Tất cả luồng đã hoàn thành");
	}

	private static void docVaGhiTep(String tenTep) {
		try (BufferedReader reader = new BufferedReader(new FileReader(tenTep))) {
			StringBuilder noiDung = new StringBuilder();
			String dong;
			while ((dong = reader.readLine()) != null) {
				noiDung.append(dong).append("\n");
			}
			synchronized (lock) {
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
					writer.write("-- Nội dung từ " + tenTep + " --\n");
					writer.write(noiDung.toString());
					writer.write("\n");
				}
			}

		} catch (IOException e) {
			System.err.println("Lỗi khi đọc tệp: " + tenTep);
		}
	}
}
