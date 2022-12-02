package com.dm.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dm.common.JDBCutil;
import com.mysql.cj.protocol.Resultset;

public class QuestionDAO {
	final String QUESTION_INSERT = "insert into questions(title,content,member_id,anonymous,category_id,views,edit_time)"
							+ "values(?,?,?,?,?,0, now());";
	
	final String QUESTION_SELECT = "select * from questions where id = ?";
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	ArrayList<QuestionDTO> alist;
	public int insertQuestion(QuestionDTO question) throws SQLException{
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_INSERT);
			stmt.setString(1, question.getQuestion_title());
			stmt.setString(2, question.getQuestion_contnet());
			stmt.setString(3, question.getMemeber_id());
			stmt.setBoolean(4, question.getAnonymous());
			stmt.setInt(5, question.getcategory_id());
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement("select last_insert_id();");
			rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt("last_insert_id()");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}
		return 0;
	}
	
	public QuestionDTO selectQuestion(String questionid) throws SQLException{
		QuestionDTO dto = new QuestionDTO();
		
		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement("select * from questions where id = "+questionid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				dto.setQuestion_title(rs.getString("title"));
				dto.setQuestion_contnet(rs.getString("content"));
				dto.setMemeber_id(rs.getString("member_id"));
				dto.setAnonymous(rs.getBoolean("anonymous"));
				dto.setcategory_id(rs.getInt("category_id"));
				dto.setViews(rs.getInt("views"));
				dto.setEdit_time(rs.getTimestamp("edit_time"));
			}else { //글 없음
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(e);
//			return null;
		}finally {
			JDBCutil.close(rs, stmt, conn);
		}
		
		
		return dto;
	}
	
	final String QUESTION_LIST = "select * from questions";
	public ArrayList<QuestionDTO> selectQuestionList() throws SQLException{
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_LIST);
			rs = stmt.executeQuery();
			alist = new ArrayList<QuestionDTO>();
			while(rs.next()) {
				//id, title, content, member_id, anonymous, category_id, views, edit_time
				QuestionDTO dto = new QuestionDTO();
				dto.setQuestion_id(rs.getInt("id"));
				dto.setQuestion_title(rs.getString("title"));
				dto.setQuestion_contnet(rs.getString("content"));
				dto.setMemeber_id(rs.getString("member_id"));
				dto.setAnonymous(rs.getBoolean("anonymous"));
				dto.setcategory_id(rs.getInt("category_id"));
				dto.setViews(rs.getInt("views"));
				dto.setEdit_time(rs.getTimestamp("edit_time"));
				
				
				alist.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		
		return alist;
	}
	
	final String QUESTION_LIST_PAGING = "select * from questions order by edit_time desc limit ?, ?;";
	public ArrayList<QuestionDTO> selectQuestionListPaging(int start, int limit) throws SQLException{
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_LIST_PAGING);
			stmt.setInt(1, start);
			stmt.setInt(2, limit);
			rs = stmt.executeQuery();
			
			alist = new ArrayList<QuestionDTO>();
			while(rs.next()) {
				//id, title, content, member_id, anonymous, category_id, views, edit_time
				QuestionDTO dto = new QuestionDTO();
				dto.setQuestion_id(rs.getInt("id"));
				dto.setQuestion_title(rs.getString("title"));
				dto.setQuestion_contnet(rs.getString("content"));
				dto.setMemeber_id(rs.getString("member_id"));
				dto.setAnonymous(rs.getBoolean("anonymous"));
				dto.setcategory_id(rs.getInt("category_id"));
				dto.setViews(rs.getInt("views"));
				dto.setEdit_time(rs.getTimestamp("edit_time"));
				
				
				alist.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		
		return alist;
	}
	
	
	final String QUESTION_COUNT = "select count(*) from questions;";
	public int selectQuestionCount() throws SQLException{
		int count = 0;
		
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_COUNT);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}

		return count;
	}
//	public ArrayList<QuestionDTO> selectMemberList() throws SQLException{
//		try{
//		 	conn = JDBCutil.getConnection();
//			stmt = conn.prepareStatement(USER_LIST);
//			rs = stmt.executeQuery();
//			alist = new ArrayList<QuestionDTO>();
//			while(rs.next()) {
//				QuestionDTO rd = new QuestionDTO();
//				rd.setMemberid(rs.getString("memberid"));
//				rd.setPassword(rs.getString("password"));
//				rd.setName(rs.getString("name"));
//				rd.setEmail(rs.getString("email"));
//				alist.add(rd);
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			JDBCutil.close(rs, stmt, conn);
//		}
//		
//		return alist;
//	}
}
