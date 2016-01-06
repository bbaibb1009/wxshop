package com.wxshop.member;

import java.util.List;
import java.util.Map;

import com.wxshop.util.Page;

public interface IWeiMemberService {
	public void addWeiMember(LzWeiMember member);
	public Page queryWeiMember(LzWeiMember member);
	public boolean checkOpenIdExsit(String openId,Integer wecId);
	public void  delWeiMember(String[] wmbIds);
	public LzWeiMember getWeiMemberByOpenId(String openid,String appId);
	public LzWeiMember getWeiMemberByid(Integer wmbId);
	public void updateWeiMember(LzWeiMember weimember);
	public String getMemberCardId();
	public List<Map<String,Object>> getMemNameAllByWei(Integer wcsId);
	public Page queryWeiMemberByWcsId(LzWeiMember member);
}
