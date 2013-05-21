package net.oldcounty.controller;

import net.oldcounty.dao.ImageDao;
import net.oldcounty.model.Image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		ServletOutputStream out = response.getOutputStream();
		try {
			String imageId = request.getParameter("image_id");
			String width=request.getParameter("width");
			String height=request.getParameter("height");			
			Image userImage = ImageDao.getInstance().getUserImage(imageId);
			response.reset();
			response.setContentType("image/"+userImage.getFormat());
			if (width!= null){
				BufferedImage bufferedImage= ImageIO.read(new ByteArrayInputStream(userImage.getImage()));
				BufferedImage scaledImage = getScaledImage(bufferedImage, Integer.parseInt(width), Integer.parseInt(height));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write( scaledImage, "jpg", baos );
				baos.flush();
				byte[] imageInByte = baos.toByteArray();
				userImage.setImage(imageInByte);
				baos.close();
			}
			out.write(userImage.getImage(), 0, userImage.getImage().length);
			out.flush();
		} finally {
			out.close();
		}
	}
	
	public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
	    int imageWidth  = image.getWidth();
	    int imageHeight = image.getHeight();

	    double scaleX = (double)width/imageWidth;
	    double scaleY = (double)height/imageHeight;
	    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
	    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

	    return bilinearScaleOp.filter(
	        image,
	        new BufferedImage(width, height, image.getType()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
