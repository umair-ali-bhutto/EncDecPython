package com.ag.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * A utility class for encryption and decryption using an external Python
 * script. The Python script performs Triple DES encryption and decryption.
 * 
 * @author umair.ali
 * @since 25-June-2024
 * @version 1.0
 * 
 */
public class EncDecUtil {
	public static void main(String[] args) throws Exception {

		String data = "DATA";
		String key_hex = "My Secret Key";
		String iv_hex = "0000000000000000";
		String filePath = "/mnt/8EFED7B1FED79037/UBUNTU-BACKUP/VS-GIT/EncDecPython/EncDecUtil.py";

		// Perform encryption
		System.out.println("Encrypted Data:\n" + Encrypt(data, key_hex, iv_hex, filePath));

		// Perform decryption
		System.out.println("Decrypted Data:\n" + Decrypt(data, key_hex, iv_hex, filePath));
	}

	/**
     * Encrypts the given data using the specified key and IV by invoking a Python script.
     * 
     * @param data The data to be encrypted.
     * @param key_hex The encryption key in hexadecimal format.
     * @param iv_hex The initialization vector (IV).
     * @param filePath The file path of the Python script.
     * @return The encrypted data in hexadecimal format.
     */
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

	/**
     * Decrypts the given data using the specified key and IV by invoking a Python script.
     * 
     * @param data The data to be decrypted in hexadecimal format.
     * @param key_hex The decryption key in hexadecimal format.
     * @param iv_hex The initialization vector (IV) in hexadecimal format.
     * @param filePath The file path of the Python script.
     * @return The decrypted data in hexadecimal format.
     */
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
