package com.mala.wifi.data;

import com.mala.wifi.vo.WifiVO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DistancedWifi {
	private double distanceKm;
	private WifiVO wifi;
	
	public String distance() {
		String str = "" + distanceKm;
		return str.substring(0, Math.min(str.length(), 6));
	}
	
	public String lat() {
		String str = "" + wifi.getLat();
		return str.substring(0, Math.min(str.length(), 9));
	}
	
	public String lnt() {
		String str = "" + wifi.getLnt();
		return str.substring(0, Math.min(str.length(), 9));
	}
}
