package com.qbthon.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="event_associates")
public class Eventassociates {

	
	private String Id;
	@DynamoDBHashKey(attributeName = "Id")
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	private String eventId;
	private String eventName;
	private String associatename;
	private String associateEmail;
	private String nominated;
	
	@DynamoDBAttribute
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	@DynamoDBAttribute
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@DynamoDBAttribute
	public String getAssociatename() {
		return associatename;
	}
	public void setAssociatename(String associatename) {
		this.associatename = associatename;
	}
	@DynamoDBAttribute
	public String getAssociateEmail() {
		return associateEmail;
	}
	public void setAssociateEmail(String associateEmail) {
		this.associateEmail = associateEmail;
	}
	
	@DynamoDBAttribute
	public String getNominated() {
		return nominated;
	}
	public void setNominated(String nominated) {
		this.nominated = nominated;
	}
	
	
}
