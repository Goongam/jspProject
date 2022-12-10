package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dm.common.QuestionDAO;
import com.dm.common.QuestionDTO;
import com.dm.common.RegisterDAO;
import com.dm.common.RegisterDTO;
import com.dm.common.ReportDAO;
import com.dm.common.ReportDTO;

@WebServlet("/list.do")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RegisterDAO rdao = new RegisterDAO();
		QuestionDAO pdao = new QuestionDAO();
		ReportDAO repDao = new ReportDAO();
		
		try {
			ArrayList<RegisterDTO> mList = rdao.selectMemberList();
			session.setAttribute("vlist", mList);
			ArrayList<QuestionDTO> pList = pdao.selectPostList();
			session.setAttribute("plist", pList);
			ArrayList<ReportDTO> reportList = repDao.select_report();
			session.setAttribute("reportList", reportList);
			
			HashMap<Integer, String> id_title = new HashMap<Integer, String>(); //id - title
			for(ReportDTO report : reportList) {
				report.setTitle(repDao.selectTitleById(report.getArticle_id(), report.getType()));
			}
			session.setAttribute("ReportTitleMap", id_title);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
