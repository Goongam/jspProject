package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
	final String QUESTION_INSERT = "select * from categorys";

	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	ArrayList<CategoryDTO> alist = new ArrayList<CategoryDTO>(); 
	public ArrayList<CategoryDTO> selectQuestion() throws SQLException{
		
		
		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_INSERT);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCategory_name(rs.getString("category_name"));
				dto.setImg_url(rs.getString("img_url"));
				
				alist.add(dto);
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e);
//			return null;
		}finally {
			JDBCutil.close(rs, stmt, conn);
		}
		
		
		return alist;
	}
}
