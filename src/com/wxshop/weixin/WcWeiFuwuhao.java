package com.wxshop.weixin;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * WcWeiFuwuhao entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_WEI_FUWUHAO", catalog = "wxcrm")
public class WcWeiFuwuhao implements java.io.Serializable {

	// Fields

	private Integer fwhId;
	private String fwhAppName;
	private String fwhAppId;
	private String fwhAppSecret;
	private String fwhRederectUrl;
	private String fwhToken;
	private String fwhEncodingAesKey;
	private String fwhAesType;
	private String fwhAppType;
	private String fwhCusType;
	private String fwhAccountType;
	private Integer fwhEnterId;
	private Integer fwhDefaultMsg;
	private Integer fwhSubscribeMsg;
	private String fwhStatus;
	private String fwhDesc;
	private Integer fwhRegistor;
	private String fwhRegistdate;
	
	private String currentPage;
	private String pageSize;


	// Constructors

	/** default constructor */
	public WcWeiFuwuhao() {
	}

	/** minimal constructor */
	public WcWeiFuwuhao(String fwhAppId, String fwhAppSecret) {
		this.fwhAppId = fwhAppId;
		this.fwhAppSecret = fwhAppSecret;
	}

	/** full constructor */
	public WcWeiFuwuhao(String fwhAppName, String fwhAppId,
			String fwhAppSecret, String fwhRederectUrl, String fwhToken,
			String fwhEncodingAesKey, String fwhAesType, String fwhAppType,
			String fwhCusType, String fwhAccountType, Integer fwhEnterId,
			Integer fwhDefaultMsg, Integer fwhSubscribeMsg, String fwhStatus,
			String fwhDesc, Integer fwhRegistor, String fwhRegistdate) {
		this.fwhAppName = fwhAppName;
		this.fwhAppId = fwhAppId;
		this.fwhAppSecret = fwhAppSecret;
		this.fwhRederectUrl = fwhRederectUrl;
		this.fwhToken = fwhToken;
		this.fwhEncodingAesKey = fwhEncodingAesKey;
		this.fwhAesType = fwhAesType;
		this.fwhAppType = fwhAppType;
		this.fwhCusType = fwhCusType;
		this.fwhAccountType = fwhAccountType;
		this.fwhEnterId = fwhEnterId;
		this.fwhDefaultMsg = fwhDefaultMsg;
		this.fwhSubscribeMsg = fwhSubscribeMsg;
		this.fwhStatus = fwhStatus;
		this.fwhDesc = fwhDesc;
		this.fwhRegistor = fwhRegistor;
		this.fwhRegistdate = fwhRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FWH_ID", unique = true, nullable = false)
	public Integer getFwhId() {
		return this.fwhId;
	}

	public void setFwhId(Integer fwhId) {
		this.fwhId = fwhId;
	}

	@Column(name = "FWH_APP_NAME", length = 100)
	public String getFwhAppName() {
		return this.fwhAppName;
	}

	public void setFwhAppName(String fwhAppName) {
		this.fwhAppName = fwhAppName;
	}

	@Column(name = "FWH_APP_ID", nullable = false, length = 80)
	public String getFwhAppId() {
		return this.fwhAppId;
	}

	public void setFwhAppId(String fwhAppId) {
		this.fwhAppId = fwhAppId;
	}

	@Column(name = "FWH_APP_SECRET", nullable = false, length = 100)
	public String getFwhAppSecret() {
		return this.fwhAppSecret;
	}

	public void setFwhAppSecret(String fwhAppSecret) {
		this.fwhAppSecret = fwhAppSecret;
	}

	@Column(name = "FWH_REDERECT_URL", length = 100)
	public String getFwhRederectUrl() {
		return this.fwhRederectUrl;
	}

	public void setFwhRederectUrl(String fwhRederectUrl) {
		this.fwhRederectUrl = fwhRederectUrl;
	}

	@Column(name = "FWH_TOKEN", length = 200)
	public String getFwhToken() {
		return this.fwhToken;
	}

	public void setFwhToken(String fwhToken) {
		this.fwhToken = fwhToken;
	}

	@Column(name = "FWH_ENCODING_AES_KEY", length = 200)
	public String getFwhEncodingAesKey() {
		return this.fwhEncodingAesKey;
	}

	public void setFwhEncodingAesKey(String fwhEncodingAesKey) {
		this.fwhEncodingAesKey = fwhEncodingAesKey;
	}

	@Column(name = "FWH_AES_TYPE", length = 1)
	public String getFwhAesType() {
		return this.fwhAesType;
	}

	public void setFwhAesType(String fwhAesType) {
		this.fwhAesType = fwhAesType;
	}

	@Column(name = "FWH_APP_TYPE", length = 1)
	public String getFwhAppType() {
		return this.fwhAppType;
	}

	public void setFwhAppType(String fwhAppType) {
		this.fwhAppType = fwhAppType;
	}

	@Column(name = "FWH_CUS_TYPE", length = 1)
	public String getFwhCusType() {
		return this.fwhCusType;
	}

	public void setFwhCusType(String fwhCusType) {
		this.fwhCusType = fwhCusType;
	}

	@Column(name = "FWH_ACCOUNT_TYPE", length = 1)
	public String getFwhAccountType() {
		return this.fwhAccountType;
	}

	public void setFwhAccountType(String fwhAccountType) {
		this.fwhAccountType = fwhAccountType;
	}

	@Column(name = "FWH_ENTER_ID")
	public Integer getFwhEnterId() {
		return this.fwhEnterId;
	}

	public void setFwhEnterId(Integer fwhEnterId) {
		this.fwhEnterId = fwhEnterId;
	}

	@Column(name = "FWH_DEFAULT_MSG")
	public Integer getFwhDefaultMsg() {
		return this.fwhDefaultMsg;
	}

	public void setFwhDefaultMsg(Integer fwhDefaultMsg) {
		this.fwhDefaultMsg = fwhDefaultMsg;
	}

	@Column(name = "FWH_SUBSCRIBE_MSG")
	public Integer getFwhSubscribeMsg() {
		return this.fwhSubscribeMsg;
	}

	public void setFwhSubscribeMsg(Integer fwhSubscribeMsg) {
		this.fwhSubscribeMsg = fwhSubscribeMsg;
	}

	@Column(name = "FWH_STATUS", length = 20)
	public String getFwhStatus() {
		return this.fwhStatus;
	}

	public void setFwhStatus(String fwhStatus) {
		this.fwhStatus = fwhStatus;
	}

	@Column(name = "FWH_DESC", length = 200)
	public String getFwhDesc() {
		return this.fwhDesc;
	}

	public void setFwhDesc(String fwhDesc) {
		this.fwhDesc = fwhDesc;
	}

	@Column(name = "FWH_REGISTOR")
	public Integer getFwhRegistor() {
		return this.fwhRegistor;
	}

	public void setFwhRegistor(Integer fwhRegistor) {
		this.fwhRegistor = fwhRegistor;
	}

	@Column(name = "FWH_REGISTDATE", length = 19)
	public String getFwhRegistdate() {
		return this.fwhRegistdate;
	}

	public void setFwhRegistdate(String fwhRegistdate) {
		this.fwhRegistdate = fwhRegistdate;
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