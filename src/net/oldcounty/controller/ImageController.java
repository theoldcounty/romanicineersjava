package net.oldcounty.controller;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;

import net.oldcounty.dao.ImageDao;
import net.oldcounty.model.UserImage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {

	@RequestMapping("/uploadimage")
	public ModelAndView imageUpload(HttpServletRequest request,
			@RequestPart(value = "image", required = false) MultipartFile file,
			@RequestParam(value = "userId", required = false) String userId) {
		String filename = file.getOriginalFilename();
		String format = filename.substring(filename.lastIndexOf(".") + 1);
		InputStream filecontent;
		try {
			filecontent = file.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = filecontent.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			byte[] image = buffer.toByteArray();
			UserImage userImage=new UserImage();
			
			userImage.setFormat(format);
			userImage.setName(filename);
			userImage.setImage(image);
			userImage.setUserId(userId);
			ImageDao.getInstance().saveUserImage(userImage);
			
			System.out.println("file name:" + filename);
			System.out.println("format:" + format);
			System.out.println("image:" + image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return new ModelAndView("");
		return new ModelAndView("jsp/gallery/imagestore");
	}

	@RequestMapping("/imageform")
	public ModelAndView imageform(
			HttpServletRequest request
		) {
		return new ModelAndView("jsp/gallery/imagestore");
	}

	@RequestMapping("/retrieveimage")
	protected static ResponseEntity<byte[]> retrieveImage(
			@RequestParam(value = "image_id", required = false) String imageId,
			@RequestParam(value = "width", required = false) Integer width,
			@RequestParam(value = "height", required = false) Integer height) {

		try {
			UserImage userImage = ImageDao.getInstance().getImage(imageId);
			final HttpHeaders headers = new HttpHeaders();

			if (userImage.getFormat().equals("png")) {
				headers.setContentType(MediaType.IMAGE_PNG);
			}
			if (userImage.getFormat().equals("jpg")) {
				headers.setContentType(MediaType.IMAGE_JPEG);
			}
			if (userImage.getFormat().equals("gif")) {
				headers.setContentType(MediaType.IMAGE_GIF);
			}
			
			BufferedImage bufferedImage = ImageIO
					.read(new ByteArrayInputStream(userImage.getImage()));
		
			int originalHeight = bufferedImage.getHeight();
			int originalWidth = bufferedImage.getWidth();
						
			//if width is not null but height is null - get percentage height	
			if (width != null && height == null) {
				double ratio = ((double) width) / originalWidth;
				height = new Double(originalHeight * ratio).intValue();		
			}
			
			//if height is not null but width is null - get percentage width
			if (width == null && height != null) {
				double ratio = ((double) height) / originalHeight;
				width = new Double(originalWidth * ratio).intValue();						
			}
			
			//if width is not null and width not null
			if (width == null && height == null) {				
				height = originalHeight;
				width = originalWidth;		
			}			
								
			BufferedImage scaledImage = getScaledImage(bufferedImage, width, height);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(scaledImage, userImage.getFormat(), baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			userImage.setImage(imageInByte);
			baos.close();
			
			return new ResponseEntity<byte[]>(userImage.getImage(), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return null;

	}

	public static BufferedImage getScaledImage(BufferedImage image, int width,
			int height) throws IOException {
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();

		double scaleX = (double) width / imageWidth;
		double scaleY = (double) height / imageHeight;
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(
				scaleX, scaleY);
		AffineTransformOp bilinearScaleOp = new AffineTransformOp(
				scaleTransform, AffineTransformOp.TYPE_BILINEAR);

		return bilinearScaleOp.filter(image, new BufferedImage(width, height,
				image.getType()));
	}

}