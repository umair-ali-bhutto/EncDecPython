package com.ag.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author umair.ali
 * @since 25-June-2024
 * @version 1.0
 * 
 */
public class EncDecUtil {
	public static void main(String[] args) throws Exception {

		String data = "DATA";
		String key_hex = "KEY";
		String iv_hex = "0000000000000000";
		String filePath = "/mnt/8EFED7B1FED79037/UBUNTU-BACKUP/VS-GIT/EncDecPython/EncDecUtil.py";

		System.out.println("Encrypted Data:\n" + Encrypt(data, key_hex, iv_hex, filePath));

		System.out.println("Decrypted Data:\n" + Decrypt(data, key_hex, iv_hex, filePath));
	}

	public static String Encrypt(String data, String key_hex, String iv_hex, String filePath) {
		String result = "";
		try {
			Process process = Runtime.getRuntime()
					.exec("python " + filePath + " encrypt " + data + " " + key_hex + " " + iv_hex);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String res;
			while ((res = reader.readLine()) != null) {
				result = res;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String Decrypt(String data, String key_hex, String iv_hex, String filePath) {
		String result = "";
		try {
			Process process = Runtime.getRuntime()
					.exec("python " + filePath + " decrypt " + data + " " + key_hex + " " + iv_hex);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String res;
			while ((res = reader.readLine()) != null) {
				result = res;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
