package com.mala.wifi.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mala.wifi.dao.HistoryDAO;
import com.mala.wifi.manager.UserManager;
import com.mala.wifi.vo.HistoryVO;
import com.mala.wifi.vo.UserVO;

@WebServlet("/history")
public class HistoryController extends HttpServlet {

	private static int joinCount = 0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getSession().getId();
		UserVO user = UserManager.getInstance().getUser(uid);
		List<HistoryVO> histories = HistoryDAO.getInstance().getHistories(user.getId());
		
		req.setAttribute("histories", histories);
		req.getRequestDispatcher("/jsp/history.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("기록 delete");
		try {
			int hid = Integer.parseInt(req.getParameter("id"));
			HistoryDAO.getInstance().deleteHistory(hid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		doGet(req, resp);
	}
}
