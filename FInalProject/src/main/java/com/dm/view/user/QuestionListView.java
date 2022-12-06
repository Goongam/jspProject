package com.dm.view.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dm.common.CategoryDAO;
import com.dm.common.CategoryDTO;
import com.dm.common.QuestionDAO;
import com.dm.common.QuestionDTO;


@WebServlet("/index.do")
public class QuestionListView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Paging paging = new Paging();
		ArrayList<QuestionDTO> qlist = null;
		
		int current_page = 1;
		if(request.getParameter("p") != null)
			current_page = Integer.parseInt(request.getParameter("p"));
		paging.setCurrent_page(current_page);
		
		
		int list_num = 10;
		if(request.getParameter("list_num") != null)
			list_num = Integer.parseInt(request.getParameter("list_num"));
		paging.setList_num(list_num);
		
		
		
		
		try {
			
			QuestionDAO dao = new QuestionDAO();
			qlist = dao.selectQuestionListPaging((current_page-1)*paging.getList_num(), paging.getList_num());
			paging.setAll_question_count(dao.selectQuestionCount());
			
			paging.setDisplay_page(10);
			
			
			paging.setLast_page(((int) Math.ceil(current_page / (double) paging.getDisplay_page())) * paging.getDisplay_page()); 
		 	paging.setStart_page(paging.getLast_page() - (paging.getDisplay_page() - 1));
		 	paging.setTotal_page((int) Math.ceil(paging.getAll_question_count() / (double) paging.getList_num()));
		 	if (paging.getLast_page() > paging.getTotal_page()) {
				paging.setLast_page(paging.getTotal_page());
			}
		 	

		} catch (SQLException e) {

			e.printStackTrace();
		}
		

		session.setAttribute("page_data", paging);
		session.setAttribute("qlist", qlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
