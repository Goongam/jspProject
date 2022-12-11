package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class VoteDAO {
	final String QUERY = "select * from answers where question_id = ? order by vote desc, edit_time desc;";
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	final String INSERT_VOTE = "INSERT INTO votes (answer_id, type, member_id) SELECT ?, ?, ? FROM DUAL WHERE NOT EXISTS (SELECT * FROM votes WHERE answer_id = ? and member_id = ?);";
	final String SELECT_VOTE = "with up as (select count(*) as downcount from answers, votes where answers.id = votes.answer_id and type = 'down' and answers.id = ? ), down as ( select count(*) as upcount from answers, votes where answers.id = votes.answer_id and type = 'up'  and answers.id = ? ) select upcount - downcount as vote from up, down;";
	final String UPDATE_VOTE_SET_VAL = "with up as (select count(*) as downcount, answer_id as answerid from votes where type = 'down' and answer_id = ?),down as (select count(*) as upcount from votes where type = 'up'  and answer_id = ?) select upcount - downcount into @a from up, down;";
	final String UPDATE_VOTE = "update answers set vote = @a  where id = ?;";

	public JSONObject insertVote(String answer_id, String isUp, String memberId) throws SQLException {

		JSONObject insertResult = new JSONObject();
		int count = 0;
		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(INSERT_VOTE);

			stmt.setString(1, answer_id);
			stmt.setString(2, isUp);
			stmt.setString(3, memberId);
			stmt.setString(4, answer_id);
			stmt.setString(5, memberId);
			int insertSuccess = stmt.executeUpdate();
			if (insertSuccess == 0) {// 중복입력
				insertResult.put("result", "already_vote");
				return insertResult;
			}

			stmt = conn.prepareStatement(UPDATE_VOTE_SET_VAL);
			stmt.setString(1, answer_id);
			stmt.setString(2, answer_id);
			stmt.executeUpdate();

			stmt = conn.prepareStatement(UPDATE_VOTE);
			stmt.setString(1, answer_id);
			stmt.executeUpdate();

			stmt = conn.prepareStatement(SELECT_VOTE);
			stmt.setString(1, answer_id);
			stmt.setString(2, answer_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("vote");
			}

			insertResult.put("result", "success");
			insertResult.put("voteCount", count);
			return insertResult;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs == null)
				JDBCutil.close(stmt, conn);
			else
				JDBCutil.close(rs, stmt, conn);
		}

		insertResult.put("result", "insert Error");
		return insertResult;
	}

	public int selectVote(String answer_id) throws SQLException {

		int count = 0;

		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(SELECT_VOTE);
			stmt.setString(1, answer_id);
			stmt.setString(2, answer_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("vote");
			}

			return count;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(rs, stmt, conn);
		}

		return 0;
	}
}
