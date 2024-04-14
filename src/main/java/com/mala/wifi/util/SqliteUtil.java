package com.mala.wifi.util;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			String url = "jdbc:sqlite:src/database/wifi.db";
			conn = DriverManager.getConnection(url);
			
			System.out.println("JDBC 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	

	public static void close(Connection con)
	{
		try
		{ if (con != null) con.close(); }
		catch (SQLException e)
		{ e.printStackTrace(); }
	}
	public static void close(Statement stmt)
	{
		try
		{ if (stmt != null) stmt.close(); }
		catch (SQLException e)
		{ e.printStackTrace(); }
	}
	public static void close(ResultSet rs)
	{

		try
		{ if (rs != null) rs.close(); }
		catch (SQLException e)
		{ e.printStackTrace(); }
	}
	public static void close(Connection con, Statement stmt, ResultSet rs)
	{
		try
		{
			if (con != null) con.close();
			if (stmt != null) stmt.close();
			if (rs != null) rs.close();
		}
		catch (SQLException e)
		{ e.printStackTrace(); }
	}
}
