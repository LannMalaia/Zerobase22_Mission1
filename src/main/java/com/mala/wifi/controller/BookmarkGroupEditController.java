package com.mala.wifi.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

@WebServlet("/bookmark-group-edit")
public class BookmarkGroupEditController extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pGid = req.getParameter("gid");
		int gid = Integer.parseInt(pGid);
		BookmarkGroupVO group = BookmarkGroupDAO.getInstance().getGroup(gid);
		
		req.setAttribute("group", group);
		req.getRequestDispatcher("/jsp/bookmark-group-edit.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pGid = req.getParameter("gid");
			String pName = req.getParameter("name");
			String pLevel = req.getParameter("level");
			
			int gid = Integer.parseInt(pGid);
			int level = Integer.parseInt(pLevel);
			BookmarkGroupDAO.getInstance().updateGroup(gid, pName, level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("./bookmark-group");
	}

}
