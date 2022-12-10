package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDAO {
	final String QUESTION_INSERT = "insert into questions(title,content,member_id,anonymous,category,views,edit_time)"
							+ "values(?,?,?,?,?,0, now());";
	
	final String QUESTION_SELECT = "select * from questions where id = ?";
	final String QUESTION_ALL_SELECT = "select * from questions;";
	final String DELETE_Q = "delete from questions where id = ?";
	final String GET_QUESTION_COUNT = "select count(*) from questions where member_id=?;";
	final String TOP_CATEGORY = "select member_id, category, count(*) as count from questions where member_id = ? and category is not null group by category order by count desc limit 3;";
	final String QUESTION_TITLE = "select title from questions where member_id =?;";
	
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
			stmt.setString(5, question.getcategory());
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
		return -1;
	}
	
	String SELECT_QUESTION = "select questions.id, title, content, member_id, anonymous, category, views, edit_time, nickname from questions,members where questions.member_id = members.id and questions.id = ?;";
	public QuestionDTO selectQuestion(String questionid) throws SQLException{
		QuestionDTO dto = new QuestionDTO();
		
		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(SELECT_QUESTION);
			stmt.setString(1, questionid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				dto.setQuestion_id(rs.getInt("id"));
				dto.setQuestion_title(rs.getString("title"));
				dto.setQuestion_contnet(rs.getString("content"));
				dto.setMemeber_id(rs.getString("member_id"));
				dto.setAnonymous(rs.getBoolean("anonymous"));
				dto.setcategory(rs.getString("category"));
				dto.setViews(rs.getInt("views"));
				dto.setEdit_time(rs.getTimestamp("edit_time"));
				dto.setMember_nickname(rs.getString("nickname"));
			}else { //湲� �뾾�쓬
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
				dto.setcategory(rs.getString("category"));
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
				dto.setcategory(rs.getString("category"));
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
	
	
	final String QUESTION_LIST_CATEGORY_SEARCH = "select * from questions where category = ? and (title like ? or content like ?) order by edit_time desc limit ?, ?;";
	public ArrayList<QuestionDTO> selectSearchQuestionList(int start, int limit, String category, String keyword) throws SQLException{
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_LIST_CATEGORY_SEARCH);
			stmt.setString(1, category);
			stmt.setString(2, "%"+keyword+"%");
			stmt.setString(3, "%"+keyword+"%");
			stmt.setInt(4, start);
			stmt.setInt(5, limit);
			
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
				dto.setcategory(rs.getString("category"));
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
	
	final String QUESTION_LIST_SEARCH = "select * from questions where title like ? or content like ? order by edit_time desc limit ?, ?;";
	public ArrayList<QuestionDTO> selectSearchQuestionList(int start, int limit, String keyword) throws SQLException{
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_LIST_SEARCH);
			stmt.setString(1, "%"+keyword+"%");
			stmt.setString(2, "%"+keyword+"%");
			stmt.setInt(3, start);
			stmt.setInt(4, limit);
			
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
				dto.setcategory(rs.getString("category"));
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
	
	final String QUESTION_COUNT_CATEGORY_SEARCH = "select count(*) from questions where category = ? and (title like ? or content like ?)";
	public int selectQuestionCount(String category, String keyword) throws SQLException{
		int count = 0;
		
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_COUNT_CATEGORY_SEARCH);
			stmt.setString(1, category);
			stmt.setString(2, "%"+keyword+"%");
			stmt.setString(3, "%"+keyword+"%");
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
	final String QUESTION_COUNT_SEARCH = "select count(*) from questions where title like ? or content like ?";
	public int selectQuestionCount(String keyword) throws SQLException{
		int count = 0;
		
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_COUNT_SEARCH);
			stmt.setString(1, "%"+keyword+"%");
			stmt.setString(2, "%"+keyword+"%");
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
	
	
	public void UpdateQuestionViews(String questionid) throws SQLException{

		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement("update questions set views = views+1 where id = ?;");
			stmt.setString(1, questionid);
			stmt.executeUpdate();
		
		} catch (Exception e) {
			System.err.println(e);
		}finally {
			JDBCutil.close(stmt, conn);
		}

	}
	
	public ArrayList<QuestionDTO> selectPostList() throws SQLException{
		ArrayList<QuestionDTO> aList = new ArrayList<QuestionDTO>();
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_ALL_SELECT); //3
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				QuestionDTO rd = new QuestionDTO();
				rd.setQuestion_id(Integer.parseInt(rs.getString("id")));
				rd.setQuestion_title(rs.getString("title"));
				rd.setMemeber_id(rs.getString("member_id"));
				rd.setcategory(rs.getString("category"));
				aList.add(rd);
				
				

			}
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(stmt, conn);
		}
		return aList;
	}

	
	public void DeleteQuestion(String report_q) throws SQLException{
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(DELETE_Q);
			stmt.setString(1, report_q);
			System.out.println(stmt.executeUpdate());
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			JDBCutil.close(stmt, conn);
		}
	}
	
	
	
	public int getQuestionCount(String memberId) throws SQLException{
		int count = 0;
		
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(GET_QUESTION_COUNT);
			stmt.setString(1, memberId);
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
	
	
	public ArrayList<String> favoriteCategory(String memberId) throws SQLException{
		ArrayList<String> fav = new ArrayList<String>();
		
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(TOP_CATEGORY);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				fav.add(rs.getString("category"));
				System.out.println(rs.getString("category"));
							}
			return fav;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		return null;
	}
	

	final String QUESTION_INFO = "select * from questions where member_id=?;";
	public ArrayList<QuestionDTO> selectQuestionInfo(String memberId) throws SQLException{
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(QUESTION_INFO);
			stmt.setString(1, memberId);
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
				dto.setcategory(rs.getString("category"));
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
}
	