package com.mala.wifi.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GPS {
	private double lat;
	private double lnt;
	
	public static double getDistanceKM(GPS a, GPS b) {
		final float EARTH_RADIUS_KM = 6371;

		double latGap = Math.toRadians(b.getLat() - a.getLat());
		double lntGap = Math.toRadians(b.getLnt() - a.getLnt());
		
		double aa = Math.pow(Math.sin(latGap / 2), 2) +
					Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat())) *
					Math.pow(Math.sin(lntGap / 2), 2);
		double cc = 2 * Math.atan2(Math.sqrt(aa), Math.sqrt(1 - aa));
		
		return EARTH_RADIUS_KM * cc;
	}
}
