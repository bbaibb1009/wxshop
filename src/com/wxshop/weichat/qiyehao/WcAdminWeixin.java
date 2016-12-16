package com.wxshop.weichat.qiyehao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WcAdminWeixin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_ADMIN_WEIXIN", catalog = "wxcrm")
public class WcAdminWeixin implements java.io.Serializable {

	// Fields

	private Integer aweId;
	private Integer aweAdminId;
	private String aweUserId;
	private String aweStatus;
	private Integer aweRegistor;
	private String aweRegistDate;

	// Constructors

	/** default constructor */
	public WcAdminWeixin() {
	}

	/** full constructor */
	public WcAdminWeixin(Integer aweAdminId, String aweUserId,
			String aweStatus, Integer aweRegistor, String aweRegistDate) {
		this.aweAdminId = aweAdminId;
		this.aweUserId = aweUserId;
		this.aweStatus = aweStatus;
		this.aweRegistor = aweRegistor;
		this.aweRegistDate = aweRegistDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AWE_ID", unique = true, nullable = false)
	public Integer getAweId() {
		return this.aweId;
	}

	public void setAweId(Integer aweId) {
		this.aweId = aweId;
	}

	@Column(name = "AWE_ADMIN_ID")
	public Integer getAweAdminId() {
		return this.aweAdminId;
	}

	public void setAweAdminId(Integer aweAdminId) {
		this.aweAdminId = aweAdminId;
	}

	@Column(name = "AWE_USER_ID", length = 80)
	public String getAweUserId() {
		return this.aweUserId;
	}

	public void setAweUserId(String aweUserId) {
		this.aweUserId = aweUserId;
	}

	@Column(name = "AWE_STATUS", length = 20)
	public String getAweStatus() {
		return this.aweStatus;
	}

	public void setAweStatus(String aweStatus) {
		this.aweStatus = aweStatus;
	}

	@Column(name = "AWE_REGISTOR")
	public Integer getAweRegistor() {
		return this.aweRegistor;
	}

	public void setAweRegistor(Integer aweRegistor) {
		this.aweRegistor = aweRegistor;
	}

	@Column(name = "AWE_REGIST_DATE", length = 19)
	public String getAweRegistDate() {
		return this.aweRegistDate;
	}

	public void setAweRegistDate(String aweRegistDate) {
		this.aweRegistDate = aweRegistDate;
	}

}