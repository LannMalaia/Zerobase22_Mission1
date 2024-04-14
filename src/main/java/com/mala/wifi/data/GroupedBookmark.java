package com.mala.wifi.data;

import java.util.Date;

import com.mala.wifi.util.DateUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupedBookmark {
	private int bid;
	private int gid;
	private String groupName;
	private String wifiName;
	private String wifiMgrNo;
	private Date cdate;
	
	public String formattedCDate() {
		return DateUtil.toText(cdate);
	}
}
