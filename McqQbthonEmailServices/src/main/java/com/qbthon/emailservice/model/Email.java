package com.qbthon.emailservice.model;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class Email {

	String emailType;
	String Subject;
	String from;
	ArrayList<String> to;
	private ArrayList<MultipartFile> attachments;
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public ArrayList<String> getTo() {
		return to;
	}
	public void setTo(ArrayList<String> to) {
		this.to = to;
	}
	public ArrayList<MultipartFile> getAttachments() {
		return attachments;
	}
	public void setAttachments(ArrayList<MultipartFile> attachments) {
		this.attachments = attachments;
	}
	
	

	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}



	String Body;
	
	
}
