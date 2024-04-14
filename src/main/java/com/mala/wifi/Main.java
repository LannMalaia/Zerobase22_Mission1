package com.mala.wifi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Main {
	public static void main(String[] args) {
		// ������ �ۼ��ߴ� �ڵ带 �޼ҵ�� ������
		JsonObject json = getJson();
		
		JsonObject publicWifiInfo = json.getAsJsonObject("TbPublicWifiInfo");
		JsonArray row = publicWifiInfo.getAsJsonArray("row");
		for (int i = 0; i < row.size(); i++) {
			JsonObject wifi = row.get(i).getAsJsonObject();
			String name = wifi.getAsJsonPrimitive("X_SWIFI_MAIN_NM").getAsString();
			System.out.println(name);
		}
	}
	
	public static JsonObject getJson() {
		// �����͸� ���� �� OpenAPI�� URL
		String URL = "http://openapi.seoul.go.kr:8088/6b596a784e6a696d3239636c555441/json/TbPublicWifiInfo/1/10/중랑구";
		
		// Ŭ���̾�Ʈ ��ü�� �����
		OkHttpClient client = new OkHttpClient();
		
		// ��û���� �ۼ��Ѵ�
		Request.Builder builder = new Request.Builder().url(URL).get();
		Request request = builder.build();
		
		try {
			// ������ ��û�� ������ ���ƿ��� ������ �����Ѵ�.
			// �ٸ� ������ ���� �ʴ� ���ܰ� ��Ÿ�� �� �ֱ⿡, try-catch�� ����� �Ѵ�.
			Response response = client.newCall(request).execute();
			
			// ���������� ������ �Դٸ� body �κ��� ����Ѵ�.
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
}
