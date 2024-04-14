package com.mala.wifi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mala.wifi.controller.BookmarkGroupController;
import com.mala.wifi.data.GroupedBookmark;
import com.mala.wifi.util.DateUtil;
import com.mala.wifi.util.SqliteUtil;
import com.mala.wifi.vo.BookmarkGroupVO;
import com.mala.wifi.vo.BookmarkVO;
import com.mala.wifi.vo.UserVO;

public class GroupedBookmarkDAO {
	
	private static GroupedBookmarkDAO instance = null;

	private GroupedBookmarkDAO() {
		if (instance == null)
			instance = this;
	}
	
	public static GroupedBookmarkDAO getInstance() {
		if (instance == null)
			return new GroupedBookmarkDAO();
		return instance;
	}
	
	public ArrayList<GroupedBookmark> getGroupedBookmarks(int uid) {
		String sql = "SELECT B.bid, B.gid, A.name AS 'gname', B.name AS 'wname', B.cdate, B.mgr_no, A.level"
				+ "	FROM bookmark_group AS A"
				+ "	INNER JOIN bookmark AS B"
				+ "	ON A.gid = B.gid"
				+ "	WHERE B.uid = ?"
				+ " ORDER BY A.level, B.bid";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<GroupedBookmark> bookmarks = new ArrayList<>();
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bookmarks.add(GroupedBookmark.builder()
						.bid(rs.getInt("bid"))
						.gid(rs.getInt("gid"))
						.groupName(rs.getString("gname"))
						.wifiName(rs.getString("wname"))
						.wifiMgrNo(rs.getString("mgr_no"))
						.cdate(DateUtil.toDate(rs.getString("cdate")))
						.build());
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookmarks;
	}
	
	public GroupedBookmark getGroupedBookmark(int bid, int gid) {
		String sql = "SELECT B.bid, B.gid, A.name AS 'gname', B.name AS 'wname', B.cdate, B.mgr_no, A.level"
				+ "	FROM bookmark_group AS A"
				+ "	INNER JOIN bookmark AS B"
				+ "	ON A.gid = B.gid"
				+ "	WHERE B.bid = ? AND B.gid = ?"
				+ " ORDER BY A.level, B.bid";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		GroupedBookmark bookmark = null;
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.setInt(2, gid);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bookmark = GroupedBookmark.builder()
						.bid(rs.getInt("bid"))
						.gid(rs.getInt("gid"))
						.groupName(rs.getString("gname"))
						.wifiName(rs.getString("wname"))
						.wifiMgrNo(rs.getString("mgr_no"))
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
