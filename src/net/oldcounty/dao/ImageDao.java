package net.oldcounty.dao;

import net.oldcounty.model.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ImageDao {
	private static ImageDao imageDAO=null;
	private Connection con;
	private static final String connectionURL = "jdbc:mysql://localhost:3306/imageservlet";
	
	private ImageDao(){	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(connectionURL, "imageservlet", "imageservlet");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
	}
	public static ImageDao getInstance(){
		if (imageDAO == null){
			imageDAO=new ImageDao();
		}
		return imageDAO;
	}
	
	public Image getUserImage(String id){
		Statement st1;
		try {
			st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("select image from"
					+ " pictures where id='"+id+"'");
			String imgLen = "";
			if (rs1.next()) {
				imgLen = rs1.getString(1);			
			}
			rs1 = st1.executeQuery("select * from pictures where id='"+id+"'");
			if (rs1.next()) {
				int len = imgLen.length();
				byte[] rb = new byte[len];
				InputStream readImg = rs1.getBinaryStream("image");
				int index = readImg.read(rb, 0, len);			
				
				Image userImage = new Image();
				userImage.setImage(rb);
				userImage.setId(rs1.getInt("id"));
				userImage.setName(rs1.getString("name"));
				userImage.setUserId(rs1.getString("userId"));
				userImage.setFormat(rs1.getString("type"));
				st1.close();
				return userImage;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

		
	}
	
	public static List<DBObject> saveUserImage(Image userImage) {
		
		List<DBObject> response = new ArrayList<DBObject>();
		
		/*
		PreparedStatement pst;
		try {
			pst = con.prepareCall("insert into pictures(userid,name,type,image) values (?,?,?,?)");
			pst.setInt(1, userImage.getUserId());
			 pst.setString(2,userImage.getName());
			 pst.setString(3,userImage.getFormat());
			 pst.setBinaryStream(4, new ByteArrayInputStream(userImage.getImage()));
			 pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		BasicDBObject results = new BasicDBObject();
		
		results.put("response", "OK");
		response.add(results);	
    	
		return response;
	}
}
