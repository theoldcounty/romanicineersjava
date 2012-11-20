package net.fileupload;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	MultipartFile file;
	
	public void setFile(MultipartFile file){
		this.file=file;
	}
	public MultipartFile getFile(){
		return file;
	}
}
