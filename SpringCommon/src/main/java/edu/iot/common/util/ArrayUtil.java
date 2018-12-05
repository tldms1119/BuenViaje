package edu.iot.common.util;

public class ArrayUtil {

	public static void swap(Object[] arr, int i, int j) {
		Object temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
