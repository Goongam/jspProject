package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDAO {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	final String INSERT_REPORTS = "insert into article_reports(article_id, msg, article_type) values (?, ?, ?);";
	public void insertReport(String article_id, String msg, String type) throws SQLException{

		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(INSERT_REPORTS);
			stmt.setString(1, article_id);
			stmt.setString(2, msg);
			stmt.setString(3, type);
			System.out.println(stmt.executeUpdate());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}

	}
	
	
	final String INSERT_REPORT_ANSWER = "insert into answer_reports (answer_id, msg) values (?, ?);";
	public void insertReportAnswer(String answer_id, String msg) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(INSERT_REPORT_ANSWER);
			stmt.setString(1, answer_id);
			stmt.setString(2, msg);
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}

	}
	
	
	final String SELECT_REPORTS = "SELECT * FROM comproject.article_reports order by id desc;";
//	final String SELECT_REPORT_ANSWER = "select * from answer_reports;";
	public ArrayList<ReportDTO> select_report() throws SQLException{
		ArrayList<ReportDTO> reportList = new ArrayList<ReportDTO>();
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(SELECT_REPORTS);
			
			rs = stmt.executeQuery(); //questionReport 목록
			while(rs.next()) {
				ReportDTO rdto = new ReportDTO();
				rdto.setId(rs.getInt("id"));
				rdto.setArticle_id(rs.getString("article_id"));
				rdto.setMsg(rs.getString("msg"));
				rdto.setType(rs.getString("article_type"));
				reportList.add(rdto);
			}
			
//			stmt = conn.prepareStatement(SELECT_REPORT_ANSWER);
//			rs = stmt.executeQuery();
//			while(rs.next()) {
//				ReportDTO rdto = new ReportDTO();
//				rdto.setId(rs.getInt("id"));
//				rdto.setAnswer_id(rs.getString("answer_id"));
//				rdto.setMsg(rs.getString("msg"));
//				rdto.setType("a");
//				reportList.add(rdto);
//			}
			
			return reportList;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		return null;
	}
	
	
	final String SELECT_QTITLE_BY_ID = "SELECT title FROM questions where id = ?;";
	final String SELECT_ATITLE_BY_ID = "SELECT title FROM answers where id = ?;";
	public String selectTitleById(String id, String type) throws SQLException{

		try{
			conn = JDBCutil.getConnection();
			
			if(type.equals("q"))
				stmt = conn.prepareStatement(SELECT_QTITLE_BY_ID);
			else
				stmt = conn.prepareStatement(SELECT_ATITLE_BY_ID);
			
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("title");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		return null;
	}
	
	final String DELETE_REPORT = "delete from article_reports where id = ?;";
	public void deleteReport(String id) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(DELETE_REPORT);
			stmt.setString(1, id);
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}

	}
	
	final String DELETE_ARTICLE = "delete from article_reports where article_id = ? and article_type = ?;";
	public void deleteArticle(String id, String type) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(DELETE_ARTICLE);
			stmt.setString(1, id);
			stmt.setString(2, type);
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}

	}
	
	final String DELETE_ANSWER_IN_Q = "delete article_reports from article_reports inner join answers on article_id = answers.id inner join questions on answers.question_id = questions.id where questions.id = ?;";
	public void deleteAnswerInQ(String qid) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(DELETE_ANSWER_IN_Q);
			stmt.setString(1, qid);
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}

	}
}
