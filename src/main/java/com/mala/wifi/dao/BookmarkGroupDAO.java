package com.mala.wifi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mala.wifi.controller.BookmarkGroupController;
import com.mala.wifi.util.DateUtil;
import com.mala.wifi.util.SqliteUtil;
import com.mala.wifi.vo.BookmarkGroupVO;
import com.mala.wifi.vo.UserVO;

public class BookmarkGroupDAO {
	
	private static BookmarkGroupDAO instance = null;

	private BookmarkGroupDAO() {
		if (instance == null)
			instance = this;
	}
	
	public static BookmarkGroupDAO getInstance() {
		if (instance == null)
			return new BookmarkGroupDAO();
		return instance;
	}
	
	public int createNewGroup(int uid, String name, int level) {
		String sql = "INSERT INTO bookmark_group(uid, name, level, cdate) VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, name);
			pstmt.setInt(3, level);
			pstmt.setString(4, DateUtil.toTextFromNow());
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int updateGroup(int gid, String name, int level) {
		String sql = "UPDATE bookmark_group SET name = ?, level = ?, udate = ? WHERE gid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, level);
			pstmt.setString(3, DateUtil.toTextFromNow());
			pstmt.setInt(4, gid);
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int deleteGroup(int gid) {
		String sql = "delete from bookmark_group where gid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<BookmarkGroupVO> getGroups(int uid) {
		String sql = "SELECT * FROM bookmark_group WHERE uid = ? ORDER BY level";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BookmarkGroupVO> groups = new ArrayList<>();
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Date udate = null;
				if (rs.getString("udate") != null)
					udate = DateUtil.toDate(rs.getString("udate"));
				groups.add(BookmarkGroupVO.builder()
						.gid(rs.getInt("gid"))
						.uid(rs.getInt("uid"))
						.name(rs.getString("name"))
						.level(rs.getInt("level"))
						.cdate(DateUtil.toDate(rs.getString("cdate")))
						.udate(udate)
						.build());
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groups;
	}

	public BookmarkGroupVO getGroup(int gid) {
		String sql = "SELECT * FROM bookmark_group WHERE gid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BookmarkGroupVO group = null;
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				group = BookmarkGroupVO.builder()
				.gid(rs.getInt("gid"))
				.name(rs.getString("name"))
				.level(rs.getInt("level"))
				.build();
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}
}
