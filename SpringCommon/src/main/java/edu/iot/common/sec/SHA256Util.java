package edu.iot.common.sec;

import java.security.MessageDigest;
import java.util.Random;

public class SHA256Util {
	public static String generateSalt() {
		byte[] salt = new byte[8];
		
		Random random = new Random();
		random.nextBytes(salt);				// salt에 랜덤한 바이트 배열을 채워줌
		
		return byteToHexString(salt);
	}
	
	public static String getEncrypt(String source, String salt) {
		byte[] bytes = (source + salt).getBytes();
		String result = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);
			
			byte[] byteData = md.digest();
			result = byteToHexString(byteData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*public static String getEncrypt(String source, byte[] salt) {
		
		String result = "";
		
		byte[] a = source.getBytes();
		byte[] bytes = new byte[a.length + salt.length];
		
		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(salt, 0, bytes, a.length, salt.length);
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);
			
			byte[] byteData = md.digest();
			result = byteToHexString(byteData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}*/
	
	public static String byteToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(String.format("%02x", bytes[i]));
		}
		return sb.toString();
	}
}
