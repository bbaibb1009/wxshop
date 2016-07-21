package com.wxshop.wxchat.accesstoken;

import java.text.ParseException;

public interface IWeiAccessTokenService {

	public String getCurrentAccessTokenStr(String appId)throws ParseException;
	public String updAccessToken(String appId);
}
