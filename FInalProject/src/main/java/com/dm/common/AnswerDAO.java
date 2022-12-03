package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerDAO {

	final String ANSWER_SELECT = "select * from answers where question_id = ? order by vote desc, edit_time desc;";
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	ArrayList<AnswerDTO> AnswerList = new ArrayList<AnswerDTO>();;
	
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
				AnswerList.add(adto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCutil.close(stmt, conn);
		}
		
		return AnswerList;
	}
	
	final String UPDATE_VOTE_UP = "update answers set vote = vote + 1  where id = ?;";
	final String UPDATE_VOTE_DOWN = "update answers set vote = vote - 1  where id = ?;";
	final String SELECT_ANSWER_VOTE = "select vote from answers where id = ?;";
	public int updateVote(String answer_id, String isUp) throws SQLException{
			System.out.println(isUp);
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
			
			return -789;
		}
}
