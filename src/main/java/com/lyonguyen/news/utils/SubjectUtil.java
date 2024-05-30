package com.lyonguyen.news.utils;


import com.lyonguyen.news.enums.Subject;





public class SubjectUtil {
	
	public static String[] SearchSubject (String keyWord) {
		
		String[] listSubject = Subject.getAllSubject();
		String[] arr = new String[5]; // Create an array with size 8

		for (int i = 0; i <= 4; i++) {
		    arr[i] = listSubject[i];
		}

		return arr;

}
}
