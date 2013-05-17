package net.oldcounty.controller;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.mongodb.BasicDBObject;

import javax.mail.internet.MimeMessage;

import java.io.StringWriter;

public class SimpleEmailService {
   private static JavaMailSender mailSender;
   private static VelocityEngine velocityEngine;

   public void setMailSender(JavaMailSender mailSender) {
      this.mailSender = mailSender;
   }

   public void setVelocityEngine(VelocityEngine velocityEngine) {
      this.velocityEngine = velocityEngine;
   }
   
   public static void generateEmail(final String templateType, final BasicDBObject user) {
      MimeMessagePreparator preparator = new MimeMessagePreparator() {
         public void prepare(MimeMessage mimeMessage) throws Exception {
        	 System.out.println("EMAIL TIME");
        	 String velocityTemplateName = null;
        	 String subject = null;
        	 
        	if(templateType.equals("forgotPasswordTemplate")){
        		 velocityTemplateName = "forgot_password";
        		 subject = "Forgot Password";
        		 user.put("imgPath", "http://robot-oi772f3re:8080/springApp21/resources/images/emails/forgot_password/");
        	}
        	
        	if(templateType.equals("perfectMatchTemplate")){
        		velocityTemplateName = "perfect_match";
        		subject = "Perfect Match";
        		user.put("imgPath", "http://robot-oi772f3re:8080/springApp21/resources/images/emails/perfect_match/");
        	}
        	
        	if(templateType.equals("registerTemplate")){
        		velocityTemplateName = "registration_confirmation";
        		subject = "Registration";
        		user.put("imgPath", "http://robot-oi772f3re:8080/springApp21/resources/images/emails/registration_confirmation/");
        	}        	
        	
        	MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            	message.setTo("info@fusionrobotdesign.com");
            	message.setFrom("hello@romanicneers.com"); // could be parameterized...
            
            	
				message.setSubject(subject);
            	
            VelocityContext model = new VelocityContext();
            	model.put("user", user);            
            
            String text = applyTemplate(model, velocityTemplateName+".vm");
            	message.setText(text, true);
         }
      };
      mailSender.send(preparator);
   }
   
   public static String applyTemplate(VelocityContext data, String templateName){                  
       StringWriter sw= new StringWriter();        
       Template t = velocityEngine.getTemplate("net/oldcounty/velocity/templates/"+templateName);
       t.merge(data, sw);
       return sw.toString();                        
   }
}