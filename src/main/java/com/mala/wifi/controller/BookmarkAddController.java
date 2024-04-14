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

import com.mala.wifi.dao.BookmarkDAO;
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

@WebServlet("/bookmark-add")
public class BookmarkAddController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pGid = req.getParameter("gid");
			String pMgr_no = req.getParameter("mgr_no");
			int gid = Integer.parseInt(pGid);

			if (BookmarkDAO.getInstance().getBookmark(gid, pMgr_no) == null) {
				UserVO user = UserManager.getInstance().getUser(req.getSession().getId());
				WifiVO wifi = WifiDAO.getInstance().getWifi(pMgr_no);
				BookmarkDAO.getInstance().createBookmark(gid, user.getId(), pMgr_no, wifi.getMain_nm());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("./bookmark");
	}

}
