package com.loginservice.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "team3users")
public class Users {
private String username;
@DynamoDBHashKey
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

@DynamoDBAttribute
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@DynamoDBAttribute
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@DynamoDBAttribute
public String getRoles() {
	return roles;
}
public void setRoles(String roles) {
	this.roles = roles;
}
private String password;
private String email;
private String roles;

}
