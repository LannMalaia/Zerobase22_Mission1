package com.mala.wifi.manager;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mala.wifi.dao.UserDAO;
import com.mala.wifi.vo.UserVO;
import com.mala.wifi.vo.WifiVO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UserManager {
	private static UserManager instance = null;
	
	private UserManager() {
		if (instance == null)
			instance = this;
	}
	
	public static UserManager getInstance() {
		if (instance == null)
			return new UserManager();
		return instance;
	}
	
	public UserVO getUser(String token) {
		UserVO user = UserDAO.getInstance().getUser(token);
		if (user == null) {
			UserDAO.getInstance().createNewUser(token);
			user = UserDAO.getInstance().getUser(token);
		}
		return user;
	}
}
