package com.mala.wifi.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVO {
	private int id;
	private String token;
}
