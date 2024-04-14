package com.mala.wifi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.mala.wifi.util.DateUtil;
import com.mala.wifi.util.SqliteUtil;
import com.mala.wifi.vo.HistoryVO;
import com.mala.wifi.vo.UserVO;

public class HistoryDAO {
	
	private static HistoryDAO instance = null;

	private HistoryDAO() {
		if (instance == null)
			instance = this;
	}
	
	public static HistoryDAO getInstance() {
		if (instance == null)
			return new HistoryDAO();
		return instance;
	}
	
	public int createNewHistory(int uid, double x, double y) {
		String sql = "INSERT INTO history(uid, cdate, x, y) VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, DateUtil.toTextFromNow());
			pstmt.setDouble(3, x);
			pstmt.setDouble(4, y);
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int deleteHistory(int hid) {
		String sql = "delete from history where hid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hid);
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<HistoryVO> getHistories(int uid) {
		String sql = "SELECT * FROM history WHERE uid = ? ORDER BY hid DESC LIMIT 20";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<HistoryVO> histories = new ArrayList<>();
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				histories.add(HistoryVO.builder()
						.hid(rs.getInt("hid"))
						.uid(rs.getInt("uid"))
						.cdate(DateUtil.toDate(rs.getString("cdate")))
						.x(rs.getFloat("x"))
						.y(rs.getFloat("y"))
						.build());
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return histories;
	}
}
