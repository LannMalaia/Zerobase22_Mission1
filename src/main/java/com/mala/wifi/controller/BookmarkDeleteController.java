package com.mala.wifi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mala.wifi.dao.BookmarkDAO;
import com.mala.wifi.dao.BookmarkGroupDAO;
import com.mala.wifi.dao.GroupedBookmarkDAO;
import com.mala.wifi.data.GroupedBookmark;
import com.mala.wifi.vo.BookmarkGroupVO;

@WebServlet("/bookmark-delete")
public class BookmarkDeleteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pBid = req.getParameter("bid");
		String pGid = req.getParameter("gid");
		int bid = Integer.parseInt(pBid);
		int gid = Integer.parseInt(pGid);
		GroupedBookmark bookmark = GroupedBookmarkDAO.getInstance().getGroupedBookmark(bid, gid);
		
		req.setAttribute("bookmark", bookmark);
		req.getRequestDispatcher("/jsp/bookmark-delete.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pBid = req.getParameter("bid");
			int bid = Integer.parseInt(pBid);
			BookmarkDAO.getInstance().deleteBookmark(bid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("./bookmark");
	}

}
