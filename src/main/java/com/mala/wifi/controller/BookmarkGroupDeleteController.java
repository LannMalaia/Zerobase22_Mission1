package com.mala.wifi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mala.wifi.dao.BookmarkGroupDAO;
import com.mala.wifi.dao.GroupedBookmarkDAO;
import com.mala.wifi.data.GroupedBookmark;
import com.mala.wifi.vo.BookmarkGroupVO;

@WebServlet("/bookmark-group-delete")
public class BookmarkGroupDeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String pGid = req.getParameter("gid");
			int gid = Integer.parseInt(pGid);
			BookmarkGroupVO group = BookmarkGroupDAO.getInstance().getGroup(gid);
			
			req.setAttribute("group", group);
			req.getRequestDispatcher("/jsp/bookmark-group-delete.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("./bookmark-group");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String pGid = req.getParameter("gid");
			int gid = Integer.parseInt(pGid);
			BookmarkGroupDAO.getInstance().deleteGroup(gid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("./bookmark-group");
	}

}
