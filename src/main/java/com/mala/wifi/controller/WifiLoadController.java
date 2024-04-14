package com.mala.wifi.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mala.wifi.dao.WifiDAO;
import com.mala.wifi.manager.WifiAPIManager;
import com.mala.wifi.vo.WifiVO;

@WebServlet("/wifi-load")
public class WifiLoadController extends HttpServlet {

	private static boolean updated = false;
	private static int pointCount = 0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WifiAPIManager wifiManager = WifiAPIManager.getInstance();
		if (!updated) {
			List<WifiVO> list = wifiManager.getWifiDBAsync();
			WifiDAO.getInstance().clearAll();
			WifiDAO.getInstance().insertAll(list);
			
			updated = true;
			pointCount = list.size();
		}
		
		req.setAttribute("pointCount", pointCount);
		req.getRequestDispatcher("/jsp/load-wifi.jsp").forward(req, resp);
	}
}
