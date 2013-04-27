package net.oldcounty.controller;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.mongodb.BasicDBObject;

import javax.mail.internet.MimeMessage;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class SimpleRegistrationService {

   private static JavaMailSender mailSender;
   private static VelocityEngine velocityEngine;

   public void setMailSender(JavaMailSender mailSender) {
      this.mailSender = mailSender;
   }

   public void setVelocityEngine(VelocityEngine velocityEngine) {
      this.velocityEngine = velocityEngine;
   }

   /*
   public void sendMail() {
	   sendConfirmationEmail();
   }*/
   
   public static void sendConfirmationEmail() {
      MimeMessagePreparator preparator = new MimeMessagePreparator() {
         public void prepare(MimeMessage mimeMessage) throws Exception {
            
        	 BasicDBObject user = new BasicDBObject();
        	 	user.put("emailAddress", "info@fusionrobotdesign.com");
        	 	user.put("userName", "Robbys");
        	 	user.put("imgPath", "C:/Documents and Settings/Fusion/Workspaces/MyEclipse 10/romanicineersjava/WebContent/WEB-INF/velocity");
        	 
        	MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            	message.setTo("info@fusionrobotdesign.com");
            	message.setFrom("hello@romanicneers.com"); // could be parameterized...
            
            VelocityContext model = new VelocityContext();
            	model.put("user", user);            
            
            String text = applyTemplate(model,"registration-confirmation.vm");
            	message.setText(text, true);
         }
      };
      mailSender.send(preparator);
   }
   public static String applyTemplate(VelocityContext data, String templateName){
                  
       StringWriter sw= new StringWriter();        
       Template t = velocityEngine.getTemplate( "net/oldcounty/velocity/templates/"+templateName );
       t.merge(data, sw);
       return sw.toString();                        
   }
   
   
}