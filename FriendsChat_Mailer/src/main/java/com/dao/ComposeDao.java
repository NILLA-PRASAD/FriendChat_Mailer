package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.formatter.Formatter;



public class ComposeDao {
	public static int save(String sender,String receiver,String subject,String message){
		int status=0;
		try{
			Connection con=ConProviderDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into frndschat_message(sender,receiver,subject,message,trash,messagedate) values(?,?,?,?,?,?)");
			ps.setString(1,sender);
			ps.setString(2,receiver);
			ps.setString(3,subject);
			ps.setString(4,message);
			ps.setString(5,"no");
			ps.setDate(6,Formatter.getCurrentDate());
			
			status=ps.executeUpdate();
						
		}catch(Exception e){System.out.println(e);}
				
		return status;
	}
}
