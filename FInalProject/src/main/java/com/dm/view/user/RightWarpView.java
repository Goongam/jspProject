package com.dm.view.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dm.common.CategoryDAO;
import com.dm.common.CategoryDTO;

/**
 * Servlet implementation class RightWarpView
 */
@WebServlet("/RightWarp.do")
public class RightWarpView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryDAO cateDAO = new CategoryDAO();
			ArrayList<CategoryDTO> catelist = cateDAO.selectQuestion();
			//HttpSession session =  request.getSession();
			//session.setAttribute("catelist", catelist);
			
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			ArrayList<String> catelistName = new ArrayList<String>();
			for(CategoryDTO cdto : catelist) {
				jsonArray.add(cdto.getCategory_name());
			}

			json.put("catelist", jsonArray);
			
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
