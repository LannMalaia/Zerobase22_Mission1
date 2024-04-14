package com.mala.wifi.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mala.wifi.dao.BookmarkGroupDAO;
import com.mala.wifi.dao.HistoryDAO;
import com.mala.wifi.dao.UserDAO;
import com.mala.wifi.dao.WifiDAO;
import com.mala.wifi.data.DistancedWifi;
import com.mala.wifi.manager.UserManager;
import com.mala.wifi.vo.BookmarkGroupVO;
import com.mala.wifi.vo.HistoryVO;
import com.mala.wifi.vo.UserVO;
import com.mala.wifi.vo.WifiVO;

@WebServlet("/detail")
public class DetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserVO user = UserManager.getInstance().getUser(req.getSession().getId());
		String mgr_no = req.getParameter("mgr_no");
		WifiVO wifi = WifiDAO.getInstance().getWifi(mgr_no);
		DistancedWifi data = new DistancedWifi(0.0, wifi);
		List<BookmarkGroupVO> groups = BookmarkGroupDAO.getInstance().getGroups(user.getId());
		
		req.setAttribute("data", data);
		req.setAttribute("groups", groups);
		
		req.getRequestDispatcher("/jsp/detail.jsp").forward(req, resp);
	}
}
