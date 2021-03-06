package com.wxshop.goods;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * WcGoodsIn entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_goods_in", catalog = "wxcrm")
public class WcGoodsIn implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wgiId;
	private Integer wgiWgsId;
	private Integer wgiWcsId;
	private String wgiInTime;
	private Double wgiInNum;
	private Double wgiInPrice;
	private Integer wgiInAdmin;
	private Double wgiSurplusNum;
	private String wgiLoc;
	private String wgiDesc;
	private String wgiStatus;
	private Integer wgiRegistor;
	private String wgiRegistdate;
	
	private String currentPage;
	private String pageSize;
	private String[] wgiIds;
	
	private String wgiWgfId_Q;
	private String wgiWgsId_Q;
	private String wgiInNumMin_Q;
	private String wgiInNumMax_Q;
	private String wgiSurplusNumMin_Q;
	private String wgiSurplusNumMax_Q;
	private String wgiLoc_Q;
	private String wgiInTimeStart_Q;
	private String wgiInTimeEnd_Q;

	// Constructors

	/** default constructor */
	public WcGoodsIn() {
	}

	/** minimal constructor */
	public WcGoodsIn(Integer wgiWgsId, Integer wgiWcsId) {
		this.wgiWgsId = wgiWgsId;
		this.wgiWcsId = wgiWcsId;
	}

	/** full constructor */
	public WcGoodsIn(Integer wgiWgsId, Integer wgiWcsId, String wgiInTime,
			Double wgiInNum, Double wgiInPrice, Integer wgiInAdmin,
			Double wgiSurplusNum, String wgiLoc, String wgiDesc,
			String wgiStatus, Integer wgiRegistor, String wgiRegistdate) {
		this.wgiWgsId = wgiWgsId;
		this.wgiWcsId = wgiWcsId;
		this.wgiInTime = wgiInTime;
		this.wgiInNum = wgiInNum;
		this.wgiInPrice = wgiInPrice;
		this.wgiInAdmin = wgiInAdmin;
		this.wgiSurplusNum = wgiSurplusNum;
		this.wgiLoc = wgiLoc;
		this.wgiDesc = wgiDesc;
		this.wgiStatus = wgiStatus;
		this.wgiRegistor = wgiRegistor;
		this.wgiRegistdate = wgiRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WGI_ID", unique = true, nullable = false)
	public Integer getWgiId() {
		return this.wgiId;
	}

	public void setWgiId(Integer wgiId) {
		this.wgiId = wgiId;
	}

	@Column(name = "WGI_WGS_ID", nullable = false)
	public Integer getWgiWgsId() {
		return this.wgiWgsId;
	}

	public void setWgiWgsId(Integer wgiWgsId) {
		this.wgiWgsId = wgiWgsId;
	}

	@Column(name = "WGI_WCS_ID", nullable = false)
	public Integer getWgiWcsId() {
		return this.wgiWcsId;
	}

	public void setWgiWcsId(Integer wgiWcsId) {
		this.wgiWcsId = wgiWcsId;
	}

	@Column(name = "WGI_IN_TIME", length = 19)
	public String getWgiInTime() {
		return this.wgiInTime;
	}

	public void setWgiInTime(String wgiInTime) {
		this.wgiInTime = wgiInTime;
	}

	@Column(name = "WGI_IN_NUM", precision = 11)
	public Double getWgiInNum() {
		return this.wgiInNum;
	}

	public void setWgiInNum(Double wgiInNum) {
		this.wgiInNum = wgiInNum;
	}

	@Column(name = "WGI_IN_PRICE", precision = 11)
	public Double getWgiInPrice() {
		return this.wgiInPrice;
	}

	public void setWgiInPrice(Double wgiInPrice) {
		this.wgiInPrice = wgiInPrice;
	}

	@Column(name = "WGI_IN_ADMIN")
	public Integer getWgiInAdmin() {
		return this.wgiInAdmin;
	}

	public void setWgiInAdmin(Integer wgiInAdmin) {
		this.wgiInAdmin = wgiInAdmin;
	}

	@Column(name = "WGI_SURPLUS_NUM", precision = 11)
	public Double getWgiSurplusNum() {
		return this.wgiSurplusNum;
	}

	public void setWgiSurplusNum(Double wgiSurplusNum) {
		this.wgiSurplusNum = wgiSurplusNum;
	}

	@Column(name = "WGI_LOC", length = 100)
	public String getWgiLoc() {
		return this.wgiLoc;
	}

	public void setWgiLoc(String wgiLoc) {
		this.wgiLoc = wgiLoc;
	}

	@Column(name = "WGI_DESC", length = 200)
	public String getWgiDesc() {
		return this.wgiDesc;
	}

	public void setWgiDesc(String wgiDesc) {
		this.wgiDesc = wgiDesc;
	}

	@Column(name = "WGI_STATUS", length = 20)
	public String getWgiStatus() {
		return this.wgiStatus;
	}

	public void setWgiStatus(String wgiStatus) {
		this.wgiStatus = wgiStatus;
	}

	@Column(name = "WGI_REGISTOR")
	public Integer getWgiRegistor() {
		return this.wgiRegistor;
	}

	public void setWgiRegistor(Integer wgiRegistor) {
		this.wgiRegistor = wgiRegistor;
	}

	@Column(name = "WGI_REGISTDATE", length = 19)
	public String getWgiRegistdate() {
		return this.wgiRegistdate;
	}

	public void setWgiRegistdate(String wgiRegistdate) {
		this.wgiRegistdate = wgiRegistdate;
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
	public String[] getWgiIds() {
		return wgiIds;
	}

	public void setWgiIds(String[] wgiIds) {
		this.wgiIds = wgiIds;
	}
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@Transient
	public String getWgiWgfId_Q() {
		return wgiWgfId_Q;
	}

	public void setWgiWgfId_Q(String wgiWgfIdQ) {
		wgiWgfId_Q = wgiWgfIdQ;
	}
	@Transient
	public String getWgiWgsId_Q() {
		return wgiWgsId_Q;
	}

	public void setWgiWgsId_Q(String wgiWgsIdQ) {
		wgiWgsId_Q = wgiWgsIdQ;
	}
	@Transient
	public String getWgiInNumMin_Q() {
		return wgiInNumMin_Q;
	}

	public void setWgiInNumMin_Q(String wgiInNumMinQ) {
		wgiInNumMin_Q = wgiInNumMinQ;
	}
	@Transient
	public String getWgiInNumMax_Q() {
		return wgiInNumMax_Q;
	}

	public void setWgiInNumMax_Q(String wgiInNumMaxQ) {
		wgiInNumMax_Q = wgiInNumMaxQ;
	}
	@Transient
	public String getWgiSurplusNumMin_Q() {
		return wgiSurplusNumMin_Q;
	}

	public void setWgiSurplusNumMin_Q(String wgiSurplusNumMinQ) {
		wgiSurplusNumMin_Q = wgiSurplusNumMinQ;
	}
	@Transient
	public String getWgiSurplusNumMax_Q() {
		return wgiSurplusNumMax_Q;
	}

	public void setWgiSurplusNumMax_Q(String wgiSurplusNumMaxQ) {
		wgiSurplusNumMax_Q = wgiSurplusNumMaxQ;
	}
	@Transient
	public String getWgiLoc_Q() {
		return wgiLoc_Q;
	}

	public void setWgiLoc_Q(String wgiLocQ) {
		wgiLoc_Q = wgiLocQ;
	}
	@Transient
	public String getWgiInTimeStart_Q() {
		return wgiInTimeStart_Q;
	}

	public void setWgiInTimeStart_Q(String wgiInTimeStartQ) {
		wgiInTimeStart_Q = wgiInTimeStartQ;
	}
	@Transient
	public String getWgiInTimeEnd_Q() {
		return wgiInTimeEnd_Q;
	}

	public void setWgiInTimeEnd_Q(String wgiInTimeEndQ) {
		wgiInTimeEnd_Q = wgiInTimeEndQ;
	}
	@Transient
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}