package com.mala.wifi.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mala.wifi.dao.HistoryDAO;
import com.mala.wifi.dao.WifiDAO;
import com.mala.wifi.data.DistancedWifi;
import com.mala.wifi.data.GPS;
import com.mala.wifi.manager.UserManager;
import com.mala.wifi.manager.WifiAPIManager;
import com.mala.wifi.vo.HistoryVO;
import com.mala.wifi.vo.UserVO;
import com.mala.wifi.vo.WifiVO;

@WebServlet("/wifi-get")
public class WifiGetController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<WifiVO> list = new ArrayList<>();
		GPS gps = null;
		
		String uid = req.getSession().getId();
		
		try {
			double lat = Double.parseDouble(req.getParameter("lat"));
			double lnt = Double.parseDouble(req.getParameter("lnt"));
			
			gps = new GPS(lat, lnt);
			list = WifiDAO.getInstance().getWifisNear(gps);
			
		} catch (NumberFormatException e) {
			System.out.println("GetWifi - 잘못된 숫자 표기를 받았습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (gps == null)
			return;
		
		PriorityQueue<DistancedWifi> queue = new PriorityQueue<>((a, b) -> { 
			return a.getDistanceKm() - b.getDistanceKm() < 0 ? -1 : 1;
			});
		for (WifiVO wifi : list) {
			double dist = GPS.getDistanceKM(gps, new GPS(wifi.getLat(), wifi.getLnt()));
			queue.offer(new DistancedWifi(dist, wifi));
		}
		
		ArrayList<DistancedWifi> result = new ArrayList<>();
		for (int i = 0; i < 20 && queue.size() > 0; i++) {
			result.add(queue.poll());
			System.out.println("주변 위치를 가져옴 - " + result.get(i).getWifi().getMain_nm());
		}
		
		UserVO user = UserManager.getInstance().getUser(uid);
		HistoryDAO.getInstance().createNewHistory(user.getId(), gps.getLnt(), gps.getLat());
		
		req.setAttribute("wifiList", result);
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}
}
