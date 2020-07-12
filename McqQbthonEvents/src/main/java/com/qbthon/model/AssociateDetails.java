package com.qbthon.model;

import java.util.Date;
import java.util.List;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;


public class AssociateDetails {
	
	private String associateId;
	
	private String associateName;
	
	private String buName;
	
	private String accountName;
	
	private String grade;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private List<String> skillList;
	
	private String role;
	
	

	
	public AssociateDetails() {
		super();
	}

	public AssociateDetails(String associateId, String associateName, String buName, String accountName, String grade,
			List<String> skillList, String role, Date createdDate) {
		super();
		this.associateId = associateId;
		this.associateName = associateName;
		this.buName = buName;
		this.accountName = accountName;
		this.grade = grade;
		this.skillList = skillList;
		this.role = role;
		
	}
	
	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	
	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	
	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}

	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	
	public List<String> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<String> skillList) {
		this.skillList = skillList;
	}

	
	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "{\"associateId:\"" + "\""+associateId +"\""+ ", \"associateName:\"" + associateName + ", buName:"
				+ buName + ", accountName:" + accountName + ", grade:" + grade + ", email:" + email + ", skillList:"
				+ skillList + ", role:" + role + "}";
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
}
