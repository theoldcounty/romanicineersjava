package net.oldcounty.controller;

import net.oldcounty.dao.ImageDao;
import net.oldcounty.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/*
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
*/

/**
 * Servlet implementation class ImageStoreServlet
 */
@WebServlet("/ImageStoreServlet")
@MultipartConfig
public class ImageStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageStoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		 Image userImage=new Image(); 
		 String userId = request.getParameter("userId");
		 Part filePart = request.getPart("image"); // Retrieves <input type="file" name="file">
		 String filename = getFilename(filePart);
		 userImage.setName(filename);
		 userImage.setUserId(userId);
		 userImage.setFormat(filename.substring(filename.lastIndexOf(".")+1));
		 InputStream filecontent = filePart.getInputStream();
		
		 
		 ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		 int nRead;
		 byte[] data = new byte[16384];
		 while ((nRead = filecontent.read(data, 0, data.length)) != -1) {
		   buffer.write(data, 0, nRead);
		 }
		 buffer.flush();
		 userImage.setImage(buffer.toByteArray());
		 ImageDao.getInstance().saveUserImage(userImage);
	}
	
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}

}
