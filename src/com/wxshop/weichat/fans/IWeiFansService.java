package com.wxshop.weichat.fans;


public interface IWeiFansService {
	public void addFansBySubscribe(String openId,String appId);
	public WcWeiFans getFansByOpenId(String openId,String appId);
	public void addFans(WcWeiFans fans);
	public void updFans(WcWeiFans fans);
}
