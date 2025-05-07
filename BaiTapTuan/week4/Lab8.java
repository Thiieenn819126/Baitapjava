package Week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lab8 {
	private static final String TU_KHOA = "tsonngungu";

	public static void main(String[] args) {
		String[] danhSachTep = { "dest.txt", "source.txt" };
		ExecutorService executor = Executors.newFixedThreadPool(danhSachTep.length);
		ConcurrentHashMap<String, Integer> ketQuaTimKiem = new ConcurrentHashMap<>();
		for (String tep : danhSachTep) {
			executor.execute(() -> {
				int soLanXuatHien = timTuKhoaTrongTep(tep, TU_KHOA);
				ketQuaTimKiem.put(tep, soLanXuatHien);
			});
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		ketQuaTimKiem.forEach((tep, soLan) -> {
			System.out.println("Từ khóa '" + TU_KHOA + "' xuất hiện " + soLan + " lần trong tệp " + tep);
		});
	}

	private static int timTuKhoaTrongTep(String tenTep, String tuKhoa) {
		int dem = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(tenTep))) {
			String dong;
			while ((dong = reader.readLine()) != null) {
				dem += dong.split(tuKhoa, -1).length - 1;
			}
		} catch (IOException e) {
			System.err.println("Lỗi khi đọc tệp: " + tenTep);
		}
		return dem;
	}
}
