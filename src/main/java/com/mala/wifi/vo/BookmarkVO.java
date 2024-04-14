package com.mala.wifi.vo;

import java.util.Date;

import com.mala.wifi.util.DateUtil;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookmarkVO {
	private int bid;
	private int gid;
	private int uid;
	private String mgr_no;
	private String name;
	private Date cdate;

	public String formattedCDate() {
		return DateUtil.toText(cdate);
	}
}
