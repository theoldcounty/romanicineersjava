
/*PrivateMessage.java
 * 
 * model for private message
 * 
 * setSenderUserId
 * getSenderUserId 
 * setRecepientUserId
 * getRecepientUserId
 * getMessage
 * setMessage
 * getDate
 * setDate
 * 
 * 
 * */


package net.oldcounty.model;


public class PrivateMessage {
	
	String senderUserId; //the user id who this message is for
	String recepientUserId; //the user id who this message is for
	String message; //the message
	String subject; //the subject
	String date; //the date
	

	public String getSenderUserId() {
		return senderUserId;
	}
	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}	
	
	public String getRecepientUserId() {
		return recepientUserId;
	}	
	public void setRecepientUserId(String recepientUserId){
		this.recepientUserId = recepientUserId;
	}
	

	public void setMessage(String message) {
		this.message = message;
	}	
	public String getMessage() {
		return message;
	}	

	public void setSubject(String message) {
		this.subject = subject;
	}	
	public String getSubject() {
		return subject;
	}	
}
