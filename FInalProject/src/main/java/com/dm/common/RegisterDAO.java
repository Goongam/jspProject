package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDAO {

	
	final String USER_INSERT = "insert into members values(?,?,?,?,now(),?,?);";
	final String USER_LIST = "select * from members;";
	final String Login = "select password from members where id=?;";
	final String ADMIN_CHECK = "select is_admin from members where id=?;";
	final String DELETE_M = "delete from members where id = ?";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void insertMember(RegisterDTO mem) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			pstmt = conn.prepareStatement(USER_INSERT); //3
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getNickname());
			pstmt.setString(3, mem.getPassword());
			pstmt.setBoolean(4, false);
			pstmt.setInt(5, 0);
			pstmt.setString(6, null);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(pstmt, conn);
		}

	}
	
	public ArrayList<RegisterDTO> selectMemberList() throws SQLException{
		ArrayList<RegisterDTO> aList = new ArrayList<RegisterDTO>();
		try{
			conn = JDBCutil.getConnection();
			pstmt = conn.prepareStatement(USER_LIST); //3
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				RegisterDTO rd = new RegisterDTO();
				rd.setId(rs.getString("id"));
				rd.setPassword(rs.getString("password"));
				rd.setNickname(rs.getString("nickname"));
				aList.add(rd);
				
				

			}
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(rs,pstmt, conn);
		}
		return aList;
	}
	
	
	public int login(String id, String pw) throws SQLException{
		try {
			conn = JDBCutil.getConnection();
		    pstmt = conn.prepareStatement(Login);
		    pstmt.setString(1, id);
		    rs = pstmt.executeQuery();
		    if(rs.next()) {
			    if(rs.getString("password").equals(pw)) {
			    	return 1;
			    }
			    else {
			    	return 0;
			    }
		    }
		}catch(Exception e){
		    e.printStackTrace();
		}
		finally {
			
			JDBCutil.close(rs,pstmt, conn);	
		}
		return -1;
	}
	
	
	final String SELECT_ID_CHECK = "select * from members where id = ?;";
	public int CheckId(String id) throws SQLException{
		try {
			conn = JDBCutil.getConnection();
		    pstmt = conn.prepareStatement(SELECT_ID_CHECK);
		    pstmt.setString(1, id);
		    rs = pstmt.executeQuery();
		    
		    if(rs.next()) {
			    return 1;
		    }
		    
		    return 0;
		}catch(Exception e){
		    e.printStackTrace();
		}
		finally {
			
			JDBCutil.close(rs,pstmt, conn);	
		}
		return -1;
	}
	
	public String isAdmin(String memberId) throws SQLException{
		try{
			conn = JDBCutil.getConnection();
			pstmt = conn.prepareStatement(ADMIN_CHECK);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return "admin";
			}
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(rs, pstmt, conn);
		}
		return null;
	}
		
	
	public void DeleteMember(String memberId) throws SQLException{
		try{
			conn = JDBCutil.getConnection();
			pstmt = conn.prepareStatement(DELETE_M);
			pstmt.setString(1, memberId);
			System.out.println(pstmt.executeUpdate());
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(pstmt, conn);
		}
	

	}
}
