package com.lyonguyen.news.bean;

public class SubjectCV {
	private String subject;
	private String subjectAndSpace;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubjectAndSpace() {
		return subjectAndSpace;
	}
	public void setSubjectAndSpace(String subjectAndSpace) {
		this.subjectAndSpace = subjectAndSpace;
	}
	public SubjectCV(String subject, String subjectAndSpace) {
		super();
		this.subject = subject;
		this.subjectAndSpace = subjectAndSpace;
	}
	public SubjectCV() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
