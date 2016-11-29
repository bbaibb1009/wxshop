package com.wxshop.weichat.menu;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * WcWeiMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_WEI_MENU")
public class WcWeiMenu implements java.io.Serializable {

	// Fields

	private Integer wmuId;
	private Integer wmuWecId;
	private String wmuAppId;
	private String wmuJson;
	private String wmuDesc;
	private String wmuStatus;
	private String wmuRegistorDate;
	
	
	private String 	wmuAppSecret;
	private String 	currentPage;
	private String 	pageSize;

	// Constructors

	/** default constructor */
	public WcWeiMenu() {
	}

	/** full constructor */
	public WcWeiMenu(Integer wmuWecId, String wmuAppId, String wmuJson,
			String wmuDesc, String wmuStatus, String wmuRegistorDate) {
		this.wmuWecId = wmuWecId;
		this.wmuAppId = wmuAppId;
		this.wmuJson = wmuJson;
		this.wmuDesc = wmuDesc;
		this.wmuStatus = wmuStatus;
		this.wmuRegistorDate = wmuRegistorDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WMU_ID", unique = true, nullable = false)
	public Integer getWmuId() {
		return this.wmuId;
	}

	public void setWmuId(Integer wmuId) {
		this.wmuId = wmuId;
	}

	@Column(name = "WMU_WEC_ID")
	public Integer getWmuWecId() {
		return this.wmuWecId;
	}

	public void setWmuWecId(Integer wmuWecId) {
		this.wmuWecId = wmuWecId;
	}

	@Column(name = "WMU_APP_ID", length = 200)
	public String getWmuAppId() {
		return this.wmuAppId;
	}

	public void setWmuAppId(String wmuAppId) {
		this.wmuAppId = wmuAppId;
	}

	@Column(name = "WMU_JSON", length = 65535)
	public String getWmuJson() {
		return this.wmuJson;
	}

	public void setWmuJson(String wmuJson) {
		this.wmuJson = wmuJson;
	}

	@Column(name = "WMU_DESC", length = 200)
	public String getWmuDesc() {
		return this.wmuDesc;
	}

	public void setWmuDesc(String wmuDesc) {
		this.wmuDesc = wmuDesc;
	}

	@Column(name = "WMU_STATUS", length = 1)
	public String getWmuStatus() {
		return this.wmuStatus;
	}

	public void setWmuStatus(String wmuStatus) {
		this.wmuStatus = wmuStatus;
	}

	@Column(name = "WMU_REGISTOR_DATE", length = 19)
	public String getWmuRegistorDate() {
		return this.wmuRegistorDate;
	}

	public void setWmuRegistorDate(String wmuRegistorDate) {
		this.wmuRegistorDate = wmuRegistorDate;
	}

	@Transient
	public String getWmuAppSecret() {
		return wmuAppSecret;
	}

	public void setWmuAppSecret(String wmuAppSecret) {
		this.wmuAppSecret = wmuAppSecret;
	}
	@Transient
	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	@Transient
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	

}