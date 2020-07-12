package com.qbthon.emailservice.service;

import java.io.File;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.qbthon.emailservice.model.Email;

@Service("emailService")
public class EmailService 
{
    @Autowired
    private JavaMailSender mailSender;
      
    
  
    /**
     * This method will send compose and send the message 
     * @throws MessagingException 
     * */
    public void sendMail(ArrayList<String> to, String subject, String body, File[] attachments) throws MessagingException 
    {
       // SimpleMailMessage message = new SimpleMailMessage();
        
        MimeMessage message = mailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
        helper.setSubject(subject);
        helper.setText(body,true);
        //FileSystemResource file = new FileSystemResource(new File("src/main/resources/createEvent"));
        for (File file : attachments) {
			FileSystemResource fr = new FileSystemResource(file);
			helper.addAttachment(file.getName(), fr);
		}
        for(int i=0;i<to.size();i++) {
        	 helper.setTo(to.get(i));
        	 mailSender.send(message);
        }
       
        
       
    }
    
    public void sendMail(Email email) {
    	SimpleMailMessage message = new SimpleMailMessage();
    	for(int i=0;i<email.getTo().size();i++) {
    		message.setTo(email.getTo().get(i));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            mailSender.send(message);
    	}
    	
		/*
		 * public void sendEmailWithAttachment() throws MessagingException, IOException
		 * {
		 * 
		 * MimeMessage msg = mailSender.createMimeMessage();
		 * 
		 * // true = multipart message MimeMessageHelper helper = new
		 * MimeMessageHelper(msg, true);
		 * helper.setTo("sukanya.subramanian84@gmail.com");
		 * 
		 * helper.setSubject("Testing from Spring Boot");
		 * 
		 * // default = text/plain //helper.setText("Check attachment for image!");
		 * 
		 * // true = text/html FileSystemResource file = new FileSystemResource(new
		 * File(path)); helper.
		 * setText("<html>\r\n<head>\r\n<style>\r\ntable {\r\n  font-family: arial, sans-serif;\r\n  border-collapse: collapse;\r\n  width: 50%;\r\n  background-color: #000080;\r\n  color:white\r\n}\r\n</style>\r\n</head>\r\n<body>\r\n<label><h2 style=\"color:#000080\">Cognizant</h1><span><h2 style=\"color:#25D366\">Academy</h2></span></label>\r\n\r\n<h1 style=\"background-color:#25D366;color:white\"> The Wait is Over now!.Here are the Skill areas</h1>\r\n<table>\r\n<tr>\r\n<td>maven</td>\r\n<td>angular</td>\r\n</tr>\r\n<tr>\r\n<td>spring</td>\r\n<td>css</td>\r\n</tr>\r\n<tr>\r\n<td>jenkins</td>\r\n<td>docker</td>\r\n</tr>\r\n<tr>\r\n<td>Gradle</td>\r\n<td>Groovy</td>\r\n</tr>\r\n<tr>\r\n<td>Jasmine</td>\r\n<td>Chef</td>\r\n</tr>\r\n<tr>\r\n<td>Spring security</td>\r\n<td>Core Java8</td>\r\n</tr>\r\n</table>\r\n<br>\r\n<h1 style=\"color:#25D366\">Go ahead and <a style=\"color:brown\" href=\"\">Nominate</a></h1>\r\n<h1 style=\"color:#25D366\">Seek new Skills through QBThon!</h1>\r\n<br>\r\n\r\n<h1 style=\"color:#EF7215\">Date - 13Th July</h1>\r\n\r\n<h1 style=\"color:#EF7215\">Time - 10AM to 2 PM</h1>\r\n\r\n<textarea style =\"background-color:#000080;color:white;font-size: 20px;font-family: arial, sans-serif\"id=\"w3review\" name=\"w3review\" rows=\"8\" cols=\"80\">\r\n  What happens on the EVENT day?\r\n   1.Create Mcqs\r\n   2. Get Iinstant clariications\r\n   3. Update Mcqs\r\n   4. Validationon same day\r\n   5. Get to know the worth of e-vouchers\r\n  \r\n  </textarea>\r\n\r\n</body>\r\n</html>\r\n"
		 * , true);
		 * 
		 * helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
		 * 
		 * mailSender.send(msg);
		 * 
		 * }
		 */
    }
  
    /**
     * This method will send a pre-configured message
     * */
   
}