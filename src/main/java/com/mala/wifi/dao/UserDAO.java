package com.mala.wifi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mala.wifi.util.SqliteUtil;
import com.mala.wifi.vo.UserVO;

public class UserDAO {
	
	private static UserDAO instance = null;

	private UserDAO() {
		if (instance == null)
			instance = this;
	}
	
	public static UserDAO getInstance() {
		if (instance == null)
			return new UserDAO();
		return instance;
	}
	
	public int createNewUser(String token) {
		String sql = "INSERT INTO user(token) VALUES(?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, token);
			
			int n = pstmt.executeUpdate();
			SqliteUtil.close(conn, pstmt, null);
			return n;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public UserVO getUser(String token) {
		String sql = "SELECT * FROM user WHERE token = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserVO user = null;
		try {
			conn = SqliteUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, token);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = UserVO.builder()
						.id(rs.getInt("id"))
						.token(rs.getString("token"))
						.build();
			}
			
			SqliteUtil.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
