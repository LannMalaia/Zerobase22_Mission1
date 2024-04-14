package com.mala.wifi.manager;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mala.wifi.vo.WifiVO;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WifiAPIManager {
	private static WifiAPIManager instance = null;
	
	private WifiAPIManager() {
		if (instance == null)
			instance = this;
	}
	
	public static WifiAPIManager getInstance() {
		if (instance == null)
			return new WifiAPIManager();
		return instance;
	}
	

	static final int THREAD_COUNT = 8;
	
	public CopyOnWriteArrayList<WifiVO> getWifiDBAsync() {
		CopyOnWriteArrayList<WifiVO> list = new CopyOnWriteArrayList<>();
		
		Thread[] threads = new Thread[THREAD_COUNT];
		for (int i = 0; i < THREAD_COUNT; i++) {
			final int start = i * 1000 + 1, end = i * 1000 + 1000;
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					int s = start;
					int e = end;
					
					while (true) {
						System.out.println(s + "부터 " + e + "까지 부릅니다...");
						JsonObject json = getJson(s, e);
						LinkedList<WifiVO> wifis = JsonToList(json);
						if (wifis.size() > 0)
							list.addAll(wifis);
						else
							break;
						s += THREAD_COUNT * 1000;
						e = s + 999;
					}
				}
			});
			threads[i].start();
		}
		
		// 스레드 작업 대기
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public LinkedList<WifiVO> getWifiDB() {
		LinkedList<WifiVO> list = new LinkedList<>();
		
		int start = 1;
		JsonObject json = getJson(start, start + 999);
		while (json != null) {
			JsonObject publicWifiInfo = json.getAsJsonObject("TbPublicWifiInfo");
			if (publicWifiInfo == null)
				break;
			JsonObject result = publicWifiInfo.getAsJsonObject("RESULT");
			if (!result.getAsJsonPrimitive("CODE").getAsString().equals("INFO-000"))
				break;
			JsonArray row = publicWifiInfo.getAsJsonArray("row");
			for (int i = 0; i < row.size(); i++) {
				JsonObject wifi = row.get(i).getAsJsonObject();
				
				WifiVO data = WifiVO.builder()
						.mgr_no(wifi.getAsJsonPrimitive("X_SWIFI_MGR_NO").getAsString())
						.wrdofc(wifi.getAsJsonPrimitive("X_SWIFI_WRDOFC").getAsString())
						.main_nm(wifi.getAsJsonPrimitive("X_SWIFI_MAIN_NM").getAsString())
						.adres1(wifi.getAsJsonPrimitive("X_SWIFI_ADRES1").getAsString())
						.adres2(wifi.getAsJsonPrimitive("X_SWIFI_ADRES2").getAsString())
						.instl_floor(wifi.getAsJsonPrimitive("X_SWIFI_INSTL_FLOOR").getAsString())
						.instl_ty(wifi.getAsJsonPrimitive("X_SWIFI_INSTL_TY").getAsString())
						.instl_mby(wifi.getAsJsonPrimitive("X_SWIFI_INSTL_MBY").getAsString())
						.svc_se(wifi.getAsJsonPrimitive("X_SWIFI_SVC_SE").getAsString())
						.cmcwr(wifi.getAsJsonPrimitive("X_SWIFI_CMCWR").getAsString())
						.cnstc_year(wifi.getAsJsonPrimitive("X_SWIFI_CNSTC_YEAR").getAsString())
						.inout_door (wifi.getAsJsonPrimitive("X_SWIFI_INOUT_DOOR").getAsString())
						.remars3(wifi.getAsJsonPrimitive("X_SWIFI_REMARS3").getAsString())
						.lat(wifi.getAsJsonPrimitive("LAT").getAsFloat())
						.lnt(wifi.getAsJsonPrimitive("LNT").getAsFloat())
						.work_dttm(wifi.getAsJsonPrimitive("WORK_DTTM").getAsString())
						.build();
				list.add(data);
			}
			start += 1000;
			json = getJson(start, start + 999);
		}
		return list;
	}

	private JsonObject getJson(int start, int end) {
		String URL = "http://openapi.seoul.go.kr:8088/6b596a784e6a696d3239636c555441/json/TbPublicWifiInfo/" + start + "/" + end;
		
		OkHttpClient client = new OkHttpClient();
		
		Request.Builder builder = new Request.Builder().url(URL).get();
		Request request = builder.build();
		
		try {
			Response response = client.newCall(request).execute();
			
			if (response.isSuccessful()) {
				ResponseBody body = response.body();
				if (body != null) {
					JsonObject json = JsonParser.parseString(body.string()).getAsJsonObject();
					return json;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public LinkedList<WifiVO> JsonToList(JsonObject json) {
		LinkedList<WifiVO> list = new LinkedList<>();
		
		JsonObject publicWifiInfo = json.getAsJsonObject("TbPublicWifiInfo");
		if (publicWifiInfo == null)
			return list;
		JsonObject result = publicWifiInfo.getAsJsonObject("RESULT");
		if (!result.getAsJsonPrimitive("CODE").getAsString().equals("INFO-000"))
			return list;
		JsonArray row = publicWifiInfo.getAsJsonArray("row");
		for (int i = 0; i < row.size(); i++) {
			JsonObject wifi = row.get(i).getAsJsonObject();
			
			WifiVO data = WifiVO.builder()
					.mgr_no(wifi.getAsJsonPrimitive("X_SWIFI_MGR_NO").getAsString())
					.wrdofc(wifi.getAsJsonPrimitive("X_SWIFI_WRDOFC").getAsString())
					.main_nm(wifi.getAsJsonPrimitive("X_SWIFI_MAIN_NM").getAsString())
					.adres1(wifi.getAsJsonPrimitive("X_SWIFI_ADRES1").getAsString())
					.adres2(wifi.getAsJsonPrimitive("X_SWIFI_ADRES2").getAsString())
					.instl_floor(wifi.getAsJsonPrimitive("X_SWIFI_INSTL_FLOOR").getAsString())
					.instl_ty(wifi.getAsJsonPrimitive("X_SWIFI_INSTL_TY").getAsString())
					.instl_mby(wifi.getAsJsonPrimitive("X_SWIFI_INSTL_MBY").getAsString())
					.svc_se(wifi.getAsJsonPrimitive("X_SWIFI_SVC_SE").getAsString())
					.cmcwr(wifi.getAsJsonPrimitive("X_SWIFI_CMCWR").getAsString())
					.cnstc_year(wifi.getAsJsonPrimitive("X_SWIFI_CNSTC_YEAR").getAsString())
					.inout_door (wifi.getAsJsonPrimitive("X_SWIFI_INOUT_DOOR").getAsString())
					.remars3(wifi.getAsJsonPrimitive("X_SWIFI_REMARS3").getAsString())
					.lat(wifi.getAsJsonPrimitive("LAT").getAsFloat())
					.lnt(wifi.getAsJsonPrimitive("LNT").getAsFloat())
					.work_dttm(wifi.getAsJsonPrimitive("WORK_DTTM").getAsString())
					.build();
			list.add(data);
		}
		return list;
	}
}
