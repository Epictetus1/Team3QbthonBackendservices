package com.qbthon.emailservice.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.qbthon.emailservice.model.Email;

@Service
public class EmailTemplateService {

	@Autowired 
	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	AmazonDynamoDB amazonDynamoDB;
	
	public String UpdateTemplate(Email email) {
	
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		Table table = dynamoDB.getTable("emailTemplate");
		
		Map<String, String> expressionAttributeNames = new HashMap<String, String>();
		expressionAttributeNames.put("#S", "subject");
		expressionAttributeNames.put("#B", "body");
		expressionAttributeNames.put("#t", "to");
		
		Map<String, Object> expressionAttributeValues = new HashMap<String, Object>();
		expressionAttributeValues.put(":val1",
		   email.getSubject());
		expressionAttributeValues.put(":val2", email.getBody()); 
		expressionAttributeValues.put(":val3",email.getTo()); 

		UpdateItemOutcome outcome =  table.updateItem(
			    "emailType",          // key attribute name
			    email.getEmailType(),           // key attribute value
			    "set #s = :val1 set #B = :val2 set #t = :val3" , // UpdateExpression
			    expressionAttributeNames,
			    expressionAttributeValues);
		return "updated emailtemplate for "+email.getEmailType()+outcome.getUpdateItemResult();
	}
	
	
	public String CreateEmailTemplate(Email email) throws IOException {
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		MultipartFile bodyMessagepart =email.getAttachments().get(0);
		
		
		
		 
		Table table = dynamoDB.getTable("emailTemplates");
		
		Item item = new Item().withPrimaryKey("emailType", email.getEmailType())
				.withString("subject", email.getSubject())
				
				.withString("fromaddr",email.getFrom())
				.withString("body", new String(bodyMessagepart.getBytes()));
		
		PutItemOutcome outcome = table.putItem(item);
		String path = "src/main/resources/"+email.getEmailType();
		File file = new File(path);
		
		
		file.mkdir();
		
		System.out.println("file path is "+file.getAbsolutePath());
		for(int i=1;i<email.getAttachments().size();i++) {
			MultipartFile attachment = email.getAttachments().get(i);
			
			String fileName = attachment.getOriginalFilename();
            InputStream is = attachment.getInputStream();
            Files.copy(is, Paths.get(file.getAbsolutePath() +"/"+ fileName),
                    StandardCopyOption.REPLACE_EXISTING);
		}
		return "created Template"+outcome.getPutItemResult();
		
		
		
	}
	
	public ArrayList<String> getAllTemplates(){
		JsonParserUtil jsonParse = new JsonParserUtil();
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		ArrayList<String> templates = new ArrayList<String>();
		Table emailTemplateTable = dynamoDB.getTable("emailTemplates"); 
		ScanSpec scanSpec = new ScanSpec().withProjectionExpression("emailType");
		 ItemCollection<ScanOutcome> items = emailTemplateTable.scan(scanSpec);
		 Iterator<Item> iter = items.iterator();
         while (iter.hasNext()) {
             Item item = iter.next();
             String torip = item.toString();
				/*
				 * String json = jsonParse.getJsonAttribute(item.toString(),"Item");
				 * System.out.println("json output is "+json);
				 */
            templates.add(torip.substring(torip.indexOf("=")+1,torip.indexOf("}")));
             
             //templates.add();
         }
			
         
         return templates;
		
	}
	
	public Email getEmailDataForTemplate(String emailType,String eventId) {
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		Table table = dynamoDB.getTable("event_details");
		GetItemSpec spec = new GetItemSpec()
			    .withPrimaryKey("eventId", eventId);
		spec.withProjectionExpression("Associates");
		
		Email email =new Email();
		JsonParserUtil jsonParse = new JsonParserUtil();
		
		email.setTo(jsonParse.publishEmailidsFromEventObject(table.getItem(spec).toJSON()));
		
		Table emailTemplateTable = dynamoDB.getTable("emailTemplate");
		GetItemSpec emailspec = new GetItemSpec()
			    .withPrimaryKey("emailType", emailType).
			    withProjectionExpression("body");
		email.setBody(emailTemplateTable.getItem(emailspec).toJSONPretty());
		
		emailspec = new GetItemSpec()
			    .withPrimaryKey("emailType", emailType).
			    withProjectionExpression("fromaddr");
		email.setFrom(emailTemplateTable.getItem(emailspec).toJSONPretty());
		
		emailspec = new GetItemSpec()
			    .withPrimaryKey("emailType", emailType).
			    withProjectionExpression("subject");
		email.setSubject(emailTemplateTable.getItem(emailspec).toJSONPretty());
		
		return email;
		
		
	}
	
	public Email getEmailTemplate(String emailType) {
		Email email =new Email();
		JsonParserUtil util = new JsonParserUtil();
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
		Table emailTemplateTable = dynamoDB.getTable("emailTemplates");
		GetItemSpec emailspec = new GetItemSpec()
			    .withPrimaryKey("emailType", emailType).
			    withProjectionExpression("body");
		email.setBody(util.getJsonAttribute(emailTemplateTable.getItem(emailspec).toJSONPretty(),"body"));
		
		emailspec = new GetItemSpec()
			    .withPrimaryKey("emailType", emailType).
			    withProjectionExpression("fromaddr");
		email.setFrom(emailTemplateTable.getItem(emailspec).toJSONPretty());
		
		emailspec = new GetItemSpec()
			    .withPrimaryKey("emailType", emailType).
			    withProjectionExpression("subject");
		email.setSubject(util.getJsonAttribute(emailTemplateTable.getItem(emailspec).toJSONPretty(),"subject"));
		
		return email;
		
		
	}
	
	
}
