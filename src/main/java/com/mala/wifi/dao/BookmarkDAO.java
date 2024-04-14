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
import com.mala.wifi.vo.BookmarkVO;
import com.mala.wifi.vo.UserVO;

public class BookmarkDAO {
	
	private static BookmarkDAO instance = null;

	private BookmarkDAO() {
		if (instance == null)
			instance = this;
	}
	
	public static BookmarkDAO getInstance() {
		if (instance == null)
			return new BookmarkDAO();
		return instance;
	}
	
	public int createBookmark(int gid, int uid, String mgr_no, String name) {
		String sql = "INSERT INTO bookmark(gid, uid, mgr_no, name, cdate) VALUES(?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			pstmt.setInt(2, uid);
			pstmt.setString(3, mgr_no);
			pstmt.setString(4, name);
			pstmt.setString(5, DateUtil.toTextFromNow());
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int deleteBookmark(int bid) {
		String sql = "delete from bookmark where bid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<BookmarkVO> getBookmarks(int uid) {
		String sql = "SELECT * FROM bookmark WHERE uid = ? ORDER BY bid";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BookmarkVO> bookmarks = new ArrayList<>();
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bookmarks.add(BookmarkVO.builder()
						.bid(rs.getInt("bid"))
						.gid(rs.getInt("gid"))
						.uid(rs.getInt("uid"))
						.mgr_no(rs.getString("mgr_no"))
						.name(rs.getString("name"))
						.cdate(DateUtil.toDate(rs.getString("cdate")))
						.build());
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookmarks;
	}
	
	public BookmarkVO getBookmark(int gid, String mgr_no) {
		String sql = "SELECT * FROM bookmark WHERE gid = ? AND mgr_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BookmarkVO bookmark = null;
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			pstmt.setString(2, mgr_no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bookmark = BookmarkVO.builder()
						.bid(rs.getInt("bid"))
						.gid(rs.getInt("gid"))
						.uid(rs.getInt("uid"))
						.mgr_no(rs.getString("mgr_no"))
						.name(rs.getString("name"))
						.cdate(DateUtil.toDate(rs.getString("cdate")))
						.build();
			}
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookmark;
	}
}
