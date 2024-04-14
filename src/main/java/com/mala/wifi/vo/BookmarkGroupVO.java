package com.mala.wifi.vo;

import java.util.Date;

import com.mala.wifi.util.DateUtil;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookmarkGroupVO {
	private int gid;
	private int uid;
	private String name;
	private int level;
	private Date cdate;
	private Date udate;

	public String formattedCDate() {
		return DateUtil.toText(cdate);
	}
	public String formattedUDate() {
		if (udate == null)
			return "";
		return DateUtil.toText(udate);
	}
}
