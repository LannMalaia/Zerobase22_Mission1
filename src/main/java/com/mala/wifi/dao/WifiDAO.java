package com.mala.wifi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.mala.wifi.data.GPS;
import com.mala.wifi.util.DateUtil;
import com.mala.wifi.util.SqliteUtil;
import com.mala.wifi.vo.WifiVO;

public class WifiDAO {
	
	private static WifiDAO instance = null;

	private WifiDAO() {
		if (instance == null)
			instance = this;
	}
	
	public static WifiDAO getInstance() {
		if (instance == null)
			return new WifiDAO();
		return instance;
	}

	public int clearAll() {
		String sql = "DELETE FROM wifi";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public void insertAll(List<WifiVO> list) {
		String sql = "INSERT INTO wifi(mgr_no, wrdofc, main_nm, adres1, adres2, instl_floor, instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, remars3, lat, lnt, work_dttm) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			int batchCounter = 0;
			for (WifiVO wifi : list) {
				pstmt.setString(1, wifi.getMgr_no());
				pstmt.setString(2, wifi.getWrdofc());
				pstmt.setString(3, wifi.getMain_nm());
				pstmt.setString(4, wifi.getAdres1());
				pstmt.setString(5, wifi.getAdres2());
				pstmt.setString(6, wifi.getInstl_floor());
				pstmt.setString(7, wifi.getInstl_ty());
				pstmt.setString(8, wifi.getInstl_mby());
				pstmt.setString(9, wifi.getSvc_se());
				pstmt.setString(10, wifi.getCmcwr());
				pstmt.setString(11, wifi.getCnstc_year());
				pstmt.setString(12, wifi.getInout_door());
				pstmt.setString(13, wifi.getRemars3());
				pstmt.setDouble(14, wifi.getLat());
				pstmt.setDouble(15, wifi.getLnt());
				pstmt.setString(16, wifi.getWork_dttm());
				pstmt.addBatch();
				pstmt.clearParameters();
				
				if (batchCounter++ % 1000 == 0) {
					pstmt.executeBatch();
					pstmt.clearBatch();
					conn.commit();
					System.out.println("저장 ... " + batchCounter);
				}
			}
			pstmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			SqliteUtil.close(conn, pstmt, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<WifiVO> getWifisNear(GPS gps) {
		final double gap = 0.01;
		String sql = "SELECT * FROM wifi WHERE lat >= ? and lat <= ? and lnt >= ? and lnt <= ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<WifiVO> wifis = new ArrayList<>();
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, gps.getLat() - gap);
			pstmt.setDouble(2, gps.getLat() + gap);
			pstmt.setDouble(3, gps.getLnt() - gap);
			pstmt.setDouble(4, gps.getLnt() + gap);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				wifis.add(WifiVO.builder()
						.mgr_no(rs.getString(1))
						.wrdofc(rs.getString(2))
						.main_nm(rs.getString(3))
						.adres1(rs.getString(4))
						.adres2(rs.getString(5))
						.instl_floor(rs.getString(6))
						.instl_ty(rs.getString(7))
						.instl_mby(rs.getString(8))
						.svc_se(rs.getString(9))
						.cmcwr(rs.getString(10))
						.cnstc_year(rs.getString(11))
						.inout_door(rs.getString(12))
						.remars3(rs.getString(13))
						.lat(rs.getDouble(14))
						.lnt(rs.getDouble(15))
						.work_dttm(rs.getString(16))
						.build());
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wifis;
	}
	
	public WifiVO getWifi(String mgr_no) {
		String sql = "SELECT * FROM wifi WHERE mgr_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		WifiVO wifi = null;
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mgr_no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				wifi = WifiVO.builder()
						.mgr_no(rs.getString(1))
						.wrdofc(rs.getString(2))
						.main_nm(rs.getString(3))
						.adres1(rs.getString(4))
						.adres2(rs.getString(5))
						.instl_floor(rs.getString(6))
						.instl_ty(rs.getString(7))
						.instl_mby(rs.getString(8))
						.svc_se(rs.getString(9))
						.cmcwr(rs.getString(10))
						.cnstc_year(rs.getString(11))
						.inout_door(rs.getString(12))
						.remars3(rs.getString(13))
						.lat(rs.getDouble(14))
						.lnt(rs.getDouble(15))
						.work_dttm(rs.getString(16))
						.build();
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wifi;
	}
}
