package com.wxshop.shop.dept;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * WcShopDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_SHOP_DEPT", catalog = "wxcrm")
public class WcShopDept implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wdpId;
	private String wdpName;
	private Integer wdpLevel;
	private Integer wdpOrder;
	private Integer wdpParentId;
	private Integer wdpAdminId;
	private Integer wdpTxlId;
	private String wdpStatus;
	private String wdpDesc;
	private Integer wdpRegistor;
	private String wdpRegistdate;
	
	private String currentPage;
	private String pageSize;
	private String[] wdpIds;
	
	private String wdpParentName;
	private String wdpAdminName;
	// Constructors

	/** default constructor */
	public WcShopDept() {
	}

	/** minimal constructor */
	public WcShopDept(String wdpName) {
		this.wdpName = wdpName;
	}

	/** full constructor */
	public WcShopDept(String wdpName, Integer wdpLevel, Integer wdpOrder,
			Integer wdpParentId, Integer wdpAdminId, Integer wdpTxlId,
			String wdpStatus, String wdpDesc, Integer wdpRegistor,
			String wdpRegistdate) {
		this.wdpName = wdpName;
		this.wdpLevel = wdpLevel;
		this.wdpOrder = wdpOrder;
		this.wdpParentId = wdpParentId;
		this.wdpAdminId = wdpAdminId;
		this.wdpTxlId = wdpTxlId;
		this.wdpStatus = wdpStatus;
		this.wdpDesc = wdpDesc;
		this.wdpRegistor = wdpRegistor;
		this.wdpRegistdate = wdpRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WDP_ID", unique = true, nullable = false)
	public Integer getWdpId() {
		return this.wdpId;
	}

	public void setWdpId(Integer wdpId) {
		this.wdpId = wdpId;
	}

	@Column(name = "WDP_NAME", nullable = false, length = 200)
	public String getWdpName() {
		return this.wdpName;
	}

	public void setWdpName(String wdpName) {
		this.wdpName = wdpName;
	}

	@Column(name = "WDP_LEVEL")
	public Integer getWdpLevel() {
		return this.wdpLevel;
	}

	public void setWdpLevel(Integer wdpLevel) {
		this.wdpLevel = wdpLevel;
	}

	@Column(name = "WDP_ORDER")
	public Integer getWdpOrder() {
		return this.wdpOrder;
	}

	public void setWdpOrder(Integer wdpOrder) {
		this.wdpOrder = wdpOrder;
	}

	@Column(name = "WDP_PARENT_ID")
	public Integer getWdpParentId() {
		return this.wdpParentId;
	}

	public void setWdpParentId(Integer wdpParentId) {
		this.wdpParentId = wdpParentId;
	}

	@Column(name = "WDP_ADMIN_ID")
	public Integer getWdpAdminId() {
		return this.wdpAdminId;
	}

	public void setWdpAdminId(Integer wdpAdminId) {
		this.wdpAdminId = wdpAdminId;
	}

	@Column(name = "WDP_TXL_ID")
	public Integer getWdpTxlId() {
		return this.wdpTxlId;
	}

	public void setWdpTxlId(Integer wdpTxlId) {
		this.wdpTxlId = wdpTxlId;
	}

	@Column(name = "WDP_STATUS", length = 20)
	public String getWdpStatus() {
		return this.wdpStatus;
	}

	public void setWdpStatus(String wdpStatus) {
		this.wdpStatus = wdpStatus;
	}

	@Column(name = "WDP_DESC", length = 200)
	public String getWdpDesc() {
		return this.wdpDesc;
	}

	public void setWdpDesc(String wdpDesc) {
		this.wdpDesc = wdpDesc;
	}

	@Column(name = "WDP_REGISTOR")
	public Integer getWdpRegistor() {
		return this.wdpRegistor;
	}

	public void setWdpRegistor(Integer wdpRegistor) {
		this.wdpRegistor = wdpRegistor;
	}

	@Column(name = "WDP_REGISTDATE", length = 19)
	public String getWdpRegistdate() {
		return this.wdpRegistdate;
	}

	public void setWdpRegistdate(String wdpRegistdate) {
		this.wdpRegistdate = wdpRegistdate;
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
	
	@Transient
	public String[] getWdpIds() {
		return wdpIds;
	}

	public void setWdpIds(String[] wdpIds) {
		this.wdpIds = wdpIds;
	}
	@Transient
	public String getWdpParentName() {
		return wdpParentName;
	}

	public void setWdpParentName(String wdpParentName) {
		this.wdpParentName = wdpParentName;
	}
	@Transient
	public String getWdpAdminName() {
		return wdpAdminName;
	}

	public void setWdpAdminName(String wdpAdminName) {
		this.wdpAdminName = wdpAdminName;
	}
	
	

}