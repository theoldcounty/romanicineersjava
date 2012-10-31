package net.fileupload;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import net.fileupload.FileUpload;

public class FileUploadValidator implements Validator{
	@Override
	public boolean supports(Class clazz){
		return FileUpload.class.isAssignableFrom(clazz);
	 }	
	@Override
	public void validate(Object target, Errors errors){
	  FileUpload fileUpload = (FileUpload)target;
	  if(fileUpload.getFile().getSize()==0){
		   errors.rejectValue("file", "error.empty.file", "Please Select File.");
		}
	  System.out.println(fileUpload.getFile().getSize());
	  
	  if(fileUpload.getFile().getSize() > 2000000){
		   errors.rejectValue("file", "error.empty.file", "File size more than 2000000 bytes ");
		}
	}
}
