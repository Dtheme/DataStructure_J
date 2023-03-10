package com.dzw.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Asserts {
	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("用例未通过");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class Files {

		public static void writeToFile(String filePath, Object data) {
			writeToFile(filePath, data, false);
		}

		public static void writeToFile(String filePath, Object data, boolean append) {
			if (filePath == null || data == null) return;

			try {
				File file = new File(filePath);
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}

				try (FileWriter writer = new FileWriter(file, append);
					 BufferedWriter out = new BufferedWriter(writer) ) {
					out.write(data.toString());
					out.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
