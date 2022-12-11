package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerDAO {

	final String ANSWER_SELECT = "select answers.id, title, content, member_id, anonymous, vote, question_id, edit_time, nickname from answers,members where answers.member_id = members.id and question_id = ? order by vote desc, edit_time desc;";
	final String GET_ANSWER_COUNT = "select count(*) from answers where member_id=?;";
	final String GET_VOTE = "select sum(vote) from answers where member_id=?;";
	final String ANSWER_TITLE = "select title from answers where member_id =?;";
	final String ANSWER_INFO  = "select * from answers where member_id=?;";
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	ArrayList<AnswerDTO> AnswerList = new ArrayList<AnswerDTO>();
	
	public ArrayList<AnswerDTO> selectAnswers(int question_id) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(ANSWER_SELECT);
			stmt.setInt(1, question_id);
			
			
			rs = stmt.executeQuery();
			while(rs.next()){
				AnswerDTO adto = new AnswerDTO();
				adto.setId(rs.getInt("id"));
				adto.setTitle(rs.getString("title"));
				adto.setContent(rs.getString("content"));
				adto.setMember_id(rs.getString("member_id"));
				adto.setAnonymous(rs.getBoolean("anonymous"));
				adto.setVote(rs.getInt("vote"));
				adto.setQuestion_id(rs.getInt("question_id"));
				adto.setEdit_time(rs.getTimestamp("edit_time"));
				adto.setMember_nickname(rs.getString("nickname"));
				AnswerList.add(adto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}
		
		return AnswerList;
	}
	
	final String SELECT_ANSWER_COUNT = "SELECT count(*) FROM questions INNER JOIN answers ON questions.id = answers.question_id where questions.id = ?;";
	public int SelectAnswerCount(String answer_id) throws SQLException{
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(SELECT_ANSWER_COUNT);
			stmt.setString(1, answer_id);
			rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt("count(*)");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}
		
		return 0;
	}
	
	final String UPDATE_VOTE_UP = "update answers set vote = vote + 1  where id = ?;";
	final String UPDATE_VOTE_DOWN = "update answers set vote = vote - 1  where id = ?;";
	final String SELECT_ANSWER_VOTE = "select vote from answers where id = ?;";
	public int updateVote(String answer_id, String isUp) throws SQLException{
		try{
			conn = JDBCutil.getConnection();
			if(isUp.equals("1")) stmt = conn.prepareStatement(UPDATE_VOTE_UP);
			else 	 stmt = conn.prepareStatement(UPDATE_VOTE_DOWN);
			stmt.setString(1, answer_id);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SELECT_ANSWER_VOTE);
			stmt.setString(1, answer_id);
			rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt("vote");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}
		
		return 0;
	}
	
	final String INSERT_ANSWER = "insert into answers(title,content,member_id,anonymous,vote,question_id,edit_time) values(?,?,?,?,0,?,now());";
	public void insertAnswer(AnswerDTO answer) throws SQLException{
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(INSERT_ANSWER);
			stmt.setString(1, answer.getTitle());
			stmt.setString(2, answer.getContent());
			stmt.setString(3, answer.getMember_id());
			stmt.setBoolean(4, answer.isAnonymous());
			stmt.setInt(5, answer.getQuestion_id());
			stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}

	}
	
	public int getAnswerCount(String memberId) throws SQLException{
		int count = 0;
		
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(GET_ANSWER_COUNT);
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
	
	public int getVoteCount(String memberId) throws SQLException{
		int count = 0;
		
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(GET_VOTE);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("sum(vote)");
			}
			return count;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		
		return 0;
	}
	

	
public ArrayList<AnswerDTO> selectAnswerInfo(String memberId) throws SQLException{
		
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(ANSWER_INFO);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				AnswerDTO adto = new AnswerDTO();
				adto.setId(rs.getInt("id"));
				adto.setTitle(rs.getString("title"));
				adto.setContent(rs.getString("content"));
				adto.setMember_id(rs.getString("member_id"));
				adto.setAnonymous(rs.getBoolean("anonymous"));
				adto.setVote(rs.getInt("vote"));
				adto.setQuestion_id(rs.getInt("question_id"));
				adto.setEdit_time(rs.getTimestamp("edit_time"));
				AnswerList.add(adto);
				System.out.println("while");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		
		return AnswerList;
	}


	public String SELECT_ANSWER_TO_Q = "select question_id from answers where id = ?";
	public int selectQidByAnswer(String answer_id) throws SQLException{
		try{
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(SELECT_ANSWER_TO_Q);
			stmt.setString(1, answer_id);
			rs = stmt.executeQuery();
			if(rs.next()){
				return rs.getInt("question_id");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(rs, stmt, conn);
		}
		
		return 0;
	}
	
	public String DELETE_ANSWER = "delete from answers where id = ?;";
	public int deleteAnswer(String id) {
		try{
		 	conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(DELETE_ANSWER);
			stmt.setString(1, id);
			return stmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}
		return 0;
	}
	
}
