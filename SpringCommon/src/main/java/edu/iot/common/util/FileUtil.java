package edu.iot.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static List<String> readLines(File file){
		return readLines(file, "utf-8");
	}
	
	public static List<String> readLines(File file, String charset){
		List<String> stringList = new ArrayList<>();
		try (
			BufferedReader br = new BufferedReader(	// 연결 스트림을 fileReader로 하면 문자셋을 변환해서 읽어들일 수 없음
					new InputStreamReader(new FileInputStream(file), charset));
		){
			String lineString;
			while((lineString = br.readLine()) != null) {
				stringList.add(lineString);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stringList;
	}
	
	public static void serialize(String fpath, Object obj) throws Exception {
		try (
				FileOutputStream fos = new FileOutputStream(fpath);
				ObjectOutputStream oos = new ObjectOutputStream(fos)
		){
			oos.writeObject(obj);
			oos.flush();
		}
	}
	
	public static Object deserialize(String fpath) throws Exception {
		try (
				FileInputStream fis = new FileInputStream(fpath);
				ObjectInputStream ois = new ObjectInputStream(fis)
		){
			return ois.readObject();
		}
	}
	
	public static String readAll(String path) throws Exception {
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			int ch;
			while((ch=br.read())!=-1) {
				sb.append((char)ch);
			}
		} 
		return sb.toString();
	}
	
	public static String readResource(String path) throws Exception {
		path = FileUtil.class.getResource(path).getPath(); 
		// System.out.println(path);
		return FileUtil.readAll(path); 
	}
	
	public static byte[] bytes(File file) throws Exception { // 테이블에 저장할 때
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
			int data;
			while((data=bis.read()) != -1) {			
				out.write(data);
			}
			return out.toByteArray();
		}
	}
	
	public static void save(File file, byte[] data) throws Exception {
		try(FileOutputStream fos = new FileOutputStream(file)){
			fos.write(data);
		}
	}
	
	public static void copy(String path, OutputStream out) throws Exception {
		try(
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(path));
			BufferedOutputStream bos = new BufferedOutputStream(out)
			){
			int data;
			while((data = bis.read()) != -1) {
				bos.write(data);
			}
			bos.flush();
		}
	}
}
