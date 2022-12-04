package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDAO {

	
	final String USER_INSERT = "insert into membertbl values(?,?,?,?);";
	final String USER_LIST = "select * from membertbl;";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void insertMember(RegisterDTO mem) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			pstmt = conn.prepareStatement(USER_INSERT); //3
			pstmt.setString(1, mem.getMemberid());
			pstmt.setString(2, mem.getPassword());
			pstmt.setString(3, mem.getName());
			pstmt.setString(4, mem.getEmail());
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(rs, pstmt, conn);
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
				rd.setMemberid(rs.getString("memberid"));
				rd.setPassword(rs.getString("password"));
				rd.setName(rs.getString("name"));
				rd.setEmail(rs.getString("email"));
				System.out.println(rd.getEmail());
				aList.add(rd);
				
				

			}
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(pstmt, conn);
		}
		return aList;
	}
	
	public ArrayList<RegisterDTO> selectList() throws SQLException{
		ArrayList<RegisterDTO> aList = new ArrayList<RegisterDTO>();
		try{
			conn = JDBCutil.getConnection();
			pstmt = conn.prepareStatement(USER_LIST); //3
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				RegisterDTO rd = new RegisterDTO();
				rd.setMemberid(rs.getString("memberid"));
				rd.setPassword(rs.getString("password"));
				rd.setName(rs.getString("name"));
				rd.setEmail(rs.getString("email"));
				System.out.println(rd.getEmail());
				aList.add(rd);
				
				

			}
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(pstmt, conn);
		}
		return aList;
	}
}
