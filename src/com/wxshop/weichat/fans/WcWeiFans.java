package com.wxshop.weichat.fans;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WcWeiFans entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_WEI_FANS", catalog = "wxcrm")
public class WcWeiFans implements java.io.Serializable {

	// Fields

	private Integer wacId;
	private Integer wacWecId;
	private String wacAppid;
	private String wacOpenid;
	private Integer wacSubscribe;
	private String wacNickName;
	private String wacSex;
	private String wacLanguage;
	private String wacCity;
	private String wacProvince;
	private String wacCountry;
	private String wacHeadImgUrl;
	private String wacSubscribeTime;
	private String wacStatus;
	private String wacRegistorDate;

	// Constructors

	/** default constructor */
	public WcWeiFans() {
	}

	/** full constructor */
	public WcWeiFans(Integer wacWecId, String wacAppid, String wacOpenid,
			Integer wacSubscribe, String wacNickName, String wacSex,
			String wacLanguage, String wacCity, String wacProvince,
			String wacCountry, String wacHeadImgUrl,
			String wacSubscribeTime, String wacStatus,
			String wacRegistorDate) {
		this.wacWecId = wacWecId;
		this.wacAppid = wacAppid;
		this.wacOpenid = wacOpenid;
		this.wacSubscribe = wacSubscribe;
		this.wacNickName = wacNickName;
		this.wacSex = wacSex;
		this.wacLanguage = wacLanguage;
		this.wacCity = wacCity;
		this.wacProvince = wacProvince;
		this.wacCountry = wacCountry;
		this.wacHeadImgUrl = wacHeadImgUrl;
		this.wacSubscribeTime = wacSubscribeTime;
		this.wacStatus = wacStatus;
		this.wacRegistorDate = wacRegistorDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WAC_ID", unique = true, nullable = false)
	public Integer getWacId() {
		return this.wacId;
	}

	public void setWacId(Integer wacId) {
		this.wacId = wacId;
	}

	@Column(name = "WAC_WEC_ID")
	public Integer getWacWecId() {
		return this.wacWecId;
	}

	public void setWacWecId(Integer wacWecId) {
		this.wacWecId = wacWecId;
	}

	@Column(name = "WAC_APPID", length = 200)
	public String getWacAppid() {
		return this.wacAppid;
	}

	public void setWacAppid(String wacAppid) {
		this.wacAppid = wacAppid;
	}

	@Column(name = "WAC_OPENID", length = 200)
	public String getWacOpenid() {
		return this.wacOpenid;
	}

	public void setWacOpenid(String wacOpenid) {
		this.wacOpenid = wacOpenid;
	}

	@Column(name = "WAC_SUBSCRIBE")
	public Integer getWacSubscribe() {
		return this.wacSubscribe;
	}

	public void setWacSubscribe(Integer wacSubscribe) {
		this.wacSubscribe = wacSubscribe;
	}

	@Column(name = "WAC_NICK_NAME", length = 400)
	public String getWacNickName() {
		return this.wacNickName;
	}

	public void setWacNickName(String wacNickName) {
		this.wacNickName = wacNickName;
	}

	@Column(name = "WAC_SEX", length = 1)
	public String getWacSex() {
		return this.wacSex;
	}

	public void setWacSex(String wacSex) {
		this.wacSex = wacSex;
	}

	@Column(name = "WAC_LANGUAGE", length = 20)
	public String getWacLanguage() {
		return this.wacLanguage;
	}

	public void setWacLanguage(String wacLanguage) {
		this.wacLanguage = wacLanguage;
	}

	@Column(name = "WAC_CITY", length = 100)
	public String getWacCity() {
		return this.wacCity;
	}

	public void setWacCity(String wacCity) {
		this.wacCity = wacCity;
	}

	@Column(name = "WAC_PROVINCE", length = 100)
	public String getWacProvince() {
		return this.wacProvince;
	}

	public void setWacProvince(String wacProvince) {
		this.wacProvince = wacProvince;
	}

	@Column(name = "WAC_COUNTRY", length = 100)
	public String getWacCountry() {
		return this.wacCountry;
	}

	public void setWacCountry(String wacCountry) {
		this.wacCountry = wacCountry;
	}

	@Column(name = "WAC_HEAD_IMG_URL", length = 400)
	public String getWacHeadImgUrl() {
		return this.wacHeadImgUrl;
	}

	public void setWacHeadImgUrl(String wacHeadImgUrl) {
		this.wacHeadImgUrl = wacHeadImgUrl;
	}

	@Column(name = "WAC_SUBSCRIBE_TIME", length = 19)
	public String getWacSubscribeTime() {
		return this.wacSubscribeTime;
	}

	public void setWacSubscribeTime(String wacSubscribeTime) {
		this.wacSubscribeTime = wacSubscribeTime;
	}

	@Column(name = "WAC_STATUS", length = 1)
	public String getWacStatus() {
		return this.wacStatus;
	}

	public void setWacStatus(String wacStatus) {
		this.wacStatus = wacStatus;
	}

	@Column(name = "WAC_REGISTOR_DATE", length = 19)
	public String getWacRegistorDate() {
		return this.wacRegistorDate;
	}

	public void setWacRegistorDate(String wacRegistorDate) {
		this.wacRegistorDate = wacRegistorDate;
	}

}