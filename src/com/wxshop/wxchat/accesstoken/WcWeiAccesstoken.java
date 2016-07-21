package com.wxshop.wxchat.accesstoken;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WcWeiAccesstoken entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_WEI_ACCESSTOKEN", catalog = "wxcrm")
public class WcWeiAccesstoken implements java.io.Serializable {

	// Fields

	private Integer watId;
	private Integer watWecId;
	private String watAppid;
	private String watToken;
	private String watExpiresIn;
	private String watCreatTime;
	private String watStatus;

	// Constructors

	/** default constructor */
	public WcWeiAccesstoken() {
	}

	/** full constructor */
	public WcWeiAccesstoken(Integer watWecId, String watAppid, String watToken,
			String watExpiresIn, String watCreatTime, String watStatus) {
		this.watWecId = watWecId;
		this.watAppid = watAppid;
		this.watToken = watToken;
		this.watExpiresIn = watExpiresIn;
		this.watCreatTime = watCreatTime;
		this.watStatus = watStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WAT_ID", unique = true, nullable = false)
	public Integer getWatId() {
		return this.watId;
	}

	public void setWatId(Integer watId) {
		this.watId = watId;
	}

	@Column(name = "WAT_WEC_ID")
	public Integer getWatWecId() {
		return this.watWecId;
	}

	public void setWatWecId(Integer watWecId) {
		this.watWecId = watWecId;
	}

	@Column(name = "WAT_APPID", length = 80)
	public String getWatAppid() {
		return this.watAppid;
	}

	public void setWatAppid(String watAppid) {
		this.watAppid = watAppid;
	}

	@Column(name = "WAT_TOKEN", length = 200)
	public String getWatToken() {
		return this.watToken;
	}

	public void setWatToken(String watToken) {
		this.watToken = watToken;
	}

	@Column(name = "WAT_EXPIRES_IN", length = 19)
	public String getWatExpiresIn() {
		return this.watExpiresIn;
	}

	public void setWatExpiresIn(String watExpiresIn) {
		this.watExpiresIn = watExpiresIn;
	}

	@Column(name = "WAT_CREAT_TIME", length = 19)
	public String getWatCreatTime() {
		return this.watCreatTime;
	}

	public void setWatCreatTime(String watCreatTime) {
		this.watCreatTime = watCreatTime;
	}

	@Column(name = "WAT_STATUS", length = 1)
	public String getWatStatus() {
		return this.watStatus;
	}

	public void setWatStatus(String watStatus) {
		this.watStatus = watStatus;
	}

}