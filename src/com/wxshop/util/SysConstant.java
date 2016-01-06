package com.wxshop.util;

import javax.servlet.http.HttpServletRequest;

public class SysConstant 
{
	// ������request�еķ�ҳ��ѯ���ķ��ʱ���
	public static final String PAGE_RESULT = "pageResult";
	
	// ������memcached�е����в˵��б�
	public static final String MENU_ALL = "menuAll";

	// ������memcached�е����й���Ա�����б�
	public static final String ADMIN_NAME_ALL = "wxcrmadminNameAll";
	
	// ������memcached�е�IP�����б�
	public static final String IPY_ALL = "wxcrmipyAll";
	
	// ������session�еĹ���Ա��Ϣ
	public static final String ADMIN_INFO = "adminInfoShop";
	
	// ������session�еĹ���Աӵ�е�һ���˵�
	public static final String ADMIN_MENUS_LEVEL1 = "adminMenusLevel1";
	
	// ������session�еĹ���Աӵ�еĶ����˵�
	public static final String ADMIN_MENUS_LEVEL2 = "adminMenusLevel2";
	
	// ������session�еĹ���Աӵ�е���˵�
	public static final String ADMIN_MENUS_LEVEL3 = "adminMenusLevel3";
	
	// ������session�еĵ�ǰѡ�е�һ���˵�id
	public static final String ADMIN_MENU_ID1 = "adminMenuId1";
	
	// ������session�еĵ�ǰѡ�еĶ����˵�id
	public static final String ADMIN_MENU_ID2 = "adminMenuId2";
	
	// ������session�еĵ�ǰѡ�е���˵�id
	public static final String ADMIN_MENU_ID3 = "adminMenuId3";
	
	// cookie����ʱ��
	public static final int COOKIE_AGE = 30 * 24 * 60 * 60;
	
	// ������cookie�еĹ���Ա�û���
	public static final String ADMIN_USERNAME = "wxcrmAdminUsername";
	
	// ������cookie�еĹ���Ա����md5
	public static final String ADMIN_PWD_MD5 = "wxcrmAdminPwdmd5";
	
	// ������cookie�еĹ���Ա����3des
	public static final String ADMIN_PWD_3DES = "wxcrmAdminPwd3des";
	
	// ������cookie�еĹ���Ա��¼ֱ����ת��uri
	public static final String LOGIN_REDIRECT_URI = "wxcrmloginRedirectUri";
	
	// �������ֲ߳̾������е�request����
	public static final ThreadLocal<HttpServletRequest> REQUEST_LOCAL = new ThreadLocal<HttpServletRequest>();
	
	// ϵͳ������ʱ���
	public static final String SYS_STARTUP = "wxcrmsysStartUpTime";
	
	public static final String ADMIN_INFO_COOKIE = "wxcrmadminInfoCookie";
	
	public static final String ACCESS_TOKEN = "wxcrmaccessToken";
	
	public static final String ACCESSTOKEN_EXPIRE = "wxcrmaccessTokenExpire";
	
	public static final String JS_API_TICKET = "wxcrmjsApiTikcet";
	
	public static final String JS_API_TICKET_EXPIRE = "wxcrmjsApiTikcetExpire";
	
	//�����¼��ʽ-΢��
	public static final String OTHERLOG_WEIXIN = "3";
	
	/***************************************�����ǿͻ�ϵͳ����Ҫ�õ��ı���******************************************/
	// ������cookie�еĹ���Ա�û���
	public static final String ADMIN_USERNAME_WX = "wxcrmAdminUsernameCust";
	
	// ������cookie�еĹ���Ա����md5
	public static final String ADMIN_PWD_MD5_WX = "wxcrmAdminPwdmd5Cust";
	
	// ������cookie�еĹ���Ա����md5
	public static final String ADMIN_PWD_3DES_WX = "wxcrmAdminPwd3desCust";
	
	// ������cookie�еĹ���Ա��¼ֱ����ת��uri
	public static final String LOGIN_REDIRECT_URI_WX = "wxcrmloginRedirectUriCust";
	
	// ������session�еĹ���Ա��Ϣ
	//public static final String ADMIN_INFO_WX = "adminInfoCust";
	
	public static final String ADMIN_INFO_COOKIE_WX = "wxcrmadminInfoCookieCust";
	
	// ������session�еĹ���Աӵ�е�һ���˵�
	public static final String ADMIN_MENUS_LEVEL1_WX = "adminMenusLevel1Cust";
	
	// ������session�еĹ���Աӵ�еĶ����˵�
	public static final String ADMIN_MENUS_LEVEL2_WX = "adminMenusLevel2Cust";
	
	// ������session�еĹ���Աӵ�е���˵�
	public static final String ADMIN_MENUS_LEVEL3_WX = "adminMenusLevel3Cust";
	
	// ������session�еĵ�ǰѡ�е�һ���˵�id
	public static final String ADMIN_MENU_ID1_WX = "adminMenuId1Cust";
	
	// ������session�еĵ�ǰѡ�еĶ����˵�id
	public static final String ADMIN_MENU_ID2_WX = "adminMenuId2Cust";
	
	// ������session�еĵ�ǰѡ�е���˵�id
	public static final String ADMIN_MENU_ID3_WX = "adminMenuId3Cust";
	
	//��½���̼���Ϣ
	public static final String WEBSITE_INFO_LIST = "webSiteInfoList";
	
	//��½���̼���Ϣ
	public static final String WEBSITE_INFO = "webSiteInfo";
	
	public static String chuku= "1";
	public static String huaizhang = "2";
	public static String xiaoshou = "3";
	
}







