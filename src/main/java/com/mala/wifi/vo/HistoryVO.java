package com.mala.wifi.vo;

import java.util.Date;

import com.mala.wifi.util.DateUtil;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HistoryVO {
	private int hid;
	private int uid;
	private Date cdate;
	private double x; // 경도(lnt)
	private double y; // 위도(lat)
	
	public String formattedCDate() {
		return DateUtil.toText(cdate);
	}
}
