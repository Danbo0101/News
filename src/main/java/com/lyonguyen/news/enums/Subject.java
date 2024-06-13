package com.lyonguyen.news.enums;

import java.util.ArrayList;
import java.util.List;

import com.lyonguyen.news.bean.SubjectCV;

public enum Subject {
	News,
	Car,
	Sport,
	Education,
	// News,
	Health;
	// SCS_Management,
	// Specialized_News,

	public static String[] getAllSubject() {
		Subject[] values = Subject.values();
		String[] subjects = new String[values.length];

		for (int i = 0; i < values.length; i++) {
			subjects[i] = values[i].name();
		}
		return subjects;
	}

	public static String[] getAllSubjectWithSpace() {
		Subject[] values = Subject.values();
		String[] subjects = new String[values.length];

		for (int i = 0; i < values.length; i++) {
			subjects[i] = replace_(values[i].name());
		}
		return subjects;
	}

	public static List<SubjectCV> getAllSubjectCV() {
		String[] subjectsSp = getAllSubjectWithSpace();
		String[] subjects = getAllSubject();
		List<SubjectCV> cv = new ArrayList<>();
		for (int i = 0; i < subjects.length; i++) {
			cv.add(new SubjectCV(subjects[i], subjectsSp[i]));
		}
		return cv;

	}

	public static String replace_(String s) {
		return s.replace("_", " ");
	}
	// private static String replaceSp(String s) {
	// return s.replace(" ", "_");
	// }

}
