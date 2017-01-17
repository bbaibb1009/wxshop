package com.wxshop.wxchat.qrcode;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pudding.weichat.qrcode.WxQrCodeUtil;

import com.wxshop.common.IMemcachedService;



@Service
@Transactional
public class QrCodeService implements IQrCodeService {
	
	@Autowired
	public IMemcachedService memcachedService;

	public Map<String, Object> getLoginWeiQrcode(String sessionID,String accessToken) {
		// TODO 根据sessionid 换取临时的二维码
		Map<String,Object> map = new HashMap<String,Object>();
		String sceneId = memcachedService.setSceneIdBySession(sessionID);
		JSONObject obj = WxQrCodeUtil.getQrUrlJson(accessToken, 180, "QR_SCENE", sceneId);
		map.put("ticket", (String)obj.getString("ticket"));
		map.put("url", (String)obj.getString("url"));
		return map;
	}
	
	

	
}
