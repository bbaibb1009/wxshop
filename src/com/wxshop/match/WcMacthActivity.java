package com.wxshop.match;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * WcMacthActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_MACTH_ACTIVITY")
public class WcMacthActivity implements java.io.Serializable {

	// Fields

	private Integer wtaId;
	private String wtaType;
	private Integer wtaWmaId;
	private String wtaDept;
	private String wtaLocJosn;
	private String wtaTitle;
	private String wtaContent;
	private String wtaSort;
	private String wtaRegistEndDate;
	private String wtaDidianStart;
	private String wtaJiheStarttime;
	private String wtaDidianEnd;
	private String wtaJiheEndtime;
	private String wtaFeeType;
	private Double wtaFee;
	private Integer wtaAdmin;
	private String wtaFeeStartTime;
	private String wtaFeeEndTime;
	private String wtaDesc;
	private String wtaStatus;
	private Integer wtaRegistor;
	private String wtaRegistDate;
	
	private Integer wtaImg;
	
	private String currentPage;
	private String pageSize;
	private String[] wtaIds;

	// Constructors

	/** default constructor */
	public WcMacthActivity() {
	}

	/** full constructor */
	public WcMacthActivity(String wtaType, Integer wtaWmaId, String wtaDept,
			String wtaLocJosn, String wtaTitle, String wtaContent,
			String wtaSort, String wtaRegistEndDate, String wtaDidianStart,
			String wtaJiheStarttime, String wtaDidianEnd,
			String wtaJiheEndtime, String wtaFeeType, Double wtaFee,
			Integer wtaAdmin, String wtaFeeStartTime,
			String wtaFeeEndTime, String wtaDesc, String wtaStatus,
			Integer wtaRegistor, String wtaRegistDate) {
		this.wtaType = wtaType;
		this.wtaWmaId = wtaWmaId;
		this.wtaDept = wtaDept;
		this.wtaLocJosn = wtaLocJosn;
		this.wtaTitle = wtaTitle;
		this.wtaContent = wtaContent;
		this.wtaSort = wtaSort;
		this.wtaRegistEndDate = wtaRegistEndDate;
		this.wtaDidianStart = wtaDidianStart;
		this.wtaJiheStarttime = wtaJiheStarttime;
		this.wtaDidianEnd = wtaDidianEnd;
		this.wtaJiheEndtime = wtaJiheEndtime;
		this.wtaFeeType = wtaFeeType;
		this.wtaFee = wtaFee;
		this.wtaAdmin = wtaAdmin;
		this.wtaFeeStartTime = wtaFeeStartTime;
		this.wtaFeeEndTime = wtaFeeEndTime;
		this.wtaDesc = wtaDesc;
		this.wtaStatus = wtaStatus;
		this.wtaRegistor = wtaRegistor;
		this.wtaRegistDate = wtaRegistDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WTA_ID", unique = true, nullable = false)
	public Integer getWtaId() {
		return this.wtaId;
	}

	public void setWtaId(Integer wtaId) {
		this.wtaId = wtaId;
	}

	@Column(name = "WTA_TYPE", length = 1)
	public String getWtaType() {
		return this.wtaType;
	}

	public void setWtaType(String wtaType) {
		this.wtaType = wtaType;
	}

	@Column(name = "WTA_WMA_ID")
	public Integer getWtaWmaId() {
		return this.wtaWmaId;
	}

	public void setWtaWmaId(Integer wtaWmaId) {
		this.wtaWmaId = wtaWmaId;
	}

	@Column(name = "WTA_DEPT", length = 1)
	public String getWtaDept() {
		return this.wtaDept;
	}

	public void setWtaDept(String wtaDept) {
		this.wtaDept = wtaDept;
	}

	@Column(name = "WTA_LOC_JOSN", length = 200)
	public String getWtaLocJosn() {
		return this.wtaLocJosn;
	}

	public void setWtaLocJosn(String wtaLocJosn) {
		this.wtaLocJosn = wtaLocJosn;
	}

	@Column(name = "WTA_TITLE", length = 100)
	public String getWtaTitle() {
		return this.wtaTitle;
	}

	public void setWtaTitle(String wtaTitle) {
		this.wtaTitle = wtaTitle;
	}

	@Column(name = "WTA_CONTENT", length = 65535)
	public String getWtaContent() {
		return this.wtaContent;
	}

	public void setWtaContent(String wtaContent) {
		this.wtaContent = wtaContent;
	}

	@Column(name = "WTA_SORT", length = 1)
	public String getWtaSort() {
		return this.wtaSort;
	}

	public void setWtaSort(String wtaSort) {
		this.wtaSort = wtaSort;
	}

	@Column(name = "WTA_REGIST_END_DATE", length = 19)
	public String getWtaRegistEndDate() {
		return this.wtaRegistEndDate;
	}

	public void setWtaRegistEndDate(String wtaRegistEndDate) {
		this.wtaRegistEndDate = wtaRegistEndDate;
	}

	@Column(name = "WTA_DIDIAN_START", length = 200)
	public String getWtaDidianStart() {
		return this.wtaDidianStart;
	}

	public void setWtaDidianStart(String wtaDidianStart) {
		this.wtaDidianStart = wtaDidianStart;
	}

	@Column(name = "WTA_JIHE_STARTTIME", length = 19)
	public String getWtaJiheStarttime() {
		return this.wtaJiheStarttime;
	}

	public void setWtaJiheStarttime(String wtaJiheStarttime) {
		this.wtaJiheStarttime = wtaJiheStarttime;
	}

	@Column(name = "WTA_DIDIAN_END", length = 200)
	public String getWtaDidianEnd() {
		return this.wtaDidianEnd;
	}

	public void setWtaDidianEnd(String wtaDidianEnd) {
		this.wtaDidianEnd = wtaDidianEnd;
	}

	@Column(name = "WTA_JIHE_ENDTIME", length = 19)
	public String getWtaJiheEndtime() {
		return this.wtaJiheEndtime;
	}

	public void setWtaJiheEndtime(String wtaJiheEndtime) {
		this.wtaJiheEndtime = wtaJiheEndtime;
	}

	@Column(name = "WTA_FEE_TYPE", length = 1)
	public String getWtaFeeType() {
		return this.wtaFeeType;
	}

	public void setWtaFeeType(String wtaFeeType) {
		this.wtaFeeType = wtaFeeType;
	}

	@Column(name = "WTA_FEE", precision = 11)
	public Double getWtaFee() {
		return this.wtaFee;
	}

	public void setWtaFee(Double wtaFee) {
		this.wtaFee = wtaFee;
	}

	@Column(name = "WTA_ADMIN")
	public Integer getWtaAdmin() {
		return this.wtaAdmin;
	}

	public void setWtaAdmin(Integer wtaAdmin) {
		this.wtaAdmin = wtaAdmin;
	}

	@Column(name = "WTA_FEE_START_TIME", length = 19)
	public String getWtaFeeStartTime() {
		return this.wtaFeeStartTime;
	}

	public void setWtaFeeStartTime(String wtaFeeStartTime) {
		this.wtaFeeStartTime = wtaFeeStartTime;
	}

	@Column(name = "WTA_FEE_END_TIME", length = 19)
	public String getWtaFeeEndTime() {
		return this.wtaFeeEndTime;
	}

	public void setWtaFeeEndTime(String wtaFeeEndTime) {
		this.wtaFeeEndTime = wtaFeeEndTime;
	}

	@Column(name = "WTA_DESC", length = 300)
	public String getWtaDesc() {
		return this.wtaDesc;
	}

	public void setWtaDesc(String wtaDesc) {
		this.wtaDesc = wtaDesc;
	}

	@Column(name = "WTA_STATUS", length = 10)
	public String getWtaStatus() {
		return this.wtaStatus;
	}

	public void setWtaStatus(String wtaStatus) {
		this.wtaStatus = wtaStatus;
	}

	@Column(name = "WTA_REGISTOR")
	public Integer getWtaRegistor() {
		return this.wtaRegistor;
	}

	public void setWtaRegistor(Integer wtaRegistor) {
		this.wtaRegistor = wtaRegistor;
	}

	@Column(name = "WTA_REGIST_DATE", length = 19)
	public String getWtaRegistDate() {
		return this.wtaRegistDate;
	}

	public void setWtaRegistDate(String wtaRegistDate) {
		this.wtaRegistDate = wtaRegistDate;
	}
	@Column(name = "WTA_IMG")
	public Integer getWtaImg() {
		return wtaImg;
	}

	public void setWtaImg(Integer wtaImg) {
		this.wtaImg = wtaImg;
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
	public String[] getWtaIds() {
		return wtaIds;
	}

	public void setWtaIds(String[] wtaIds) {
		this.wtaIds = wtaIds;
	}

	
	
}