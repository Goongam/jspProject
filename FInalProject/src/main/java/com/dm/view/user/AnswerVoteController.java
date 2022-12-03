package com.dm.view.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dm.common.AnswerDAO;


@WebServlet("/AnswerVote.do")
public class AnswerVoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getParameter("AnswerId");
		String isUp = (String)request.getParameter("isUp");
		
		AnswerDAO adao = new AnswerDAO();
		try {
			int voteCount = adao.updateVote(id, isUp);
			
			
			JSONObject data = new JSONObject();
			data.put("voteCount", voteCount);


			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
