package com.wxshop.wxchat.qrcode;

import java.util.Map;

public interface IQrCodeService {

	public Map<String,Object> getLoginWeiQrcode(String sessionID,String accessToken) ;

}
