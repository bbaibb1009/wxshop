package com.wxshop.match;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * WcMatch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WC_MATCH")
public class WcMatch implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wmaId;
	private String wmaName;
	private String wmaPlace;
	private String wmaRuntime;
	private String wmaGameProject;
	private String wmaYear;
	private Integer wmaSessionIndex;
	private Integer wmaLastSessionId;
	private String wmaEmergencyContract;
	private String wmaEmergencyPhone;
	private String wmaStatus;
	private String wmaDesc;
	private Integer wmaRegistor;
	private String wmaRegistDate;
	private String currentPage;
	private String pageSize;

	// Constructors

	/** default constructor */
	public WcMatch() {
	}

	/** full constructor */
	public WcMatch(String wmaName, String wmaPlace, String wmaRuntime,
			String wmaGameProject, String wmaYear, Integer wmaSessionIndex,
			Integer wmaLastSessionId, String wmaEmergencyContract,
			String wmaEmergencyPhone, String wmaStatus, String wmaDesc,
			Integer wmaRegistor, String wmaRegistDate) {
		this.wmaName = wmaName;
		this.wmaPlace = wmaPlace;
		this.wmaRuntime = wmaRuntime;
		this.wmaGameProject = wmaGameProject;
		this.wmaYear = wmaYear;
		this.wmaSessionIndex = wmaSessionIndex;
		this.wmaLastSessionId = wmaLastSessionId;
		this.wmaEmergencyContract = wmaEmergencyContract;
		this.wmaEmergencyPhone = wmaEmergencyPhone;
		this.wmaStatus = wmaStatus;
		this.wmaDesc = wmaDesc;
		this.wmaRegistor = wmaRegistor;
		this.wmaRegistDate = wmaRegistDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WMA_ID", unique = true, nullable = false)
	public Integer getWmaId() {
		return this.wmaId;
	}

	public void setWmaId(Integer wmaId) {
		this.wmaId = wmaId;
	}

	@Column(name = "WMA_NAME", length = 20)
	public String getWmaName() {
		return this.wmaName;
	}

	public void setWmaName(String wmaName) {
		this.wmaName = wmaName;
	}

	@Column(name = "WMA_PLACE", length = 80)
	public String getWmaPlace() {
		return this.wmaPlace;
	}

	public void setWmaPlace(String wmaPlace) {
		this.wmaPlace = wmaPlace;
	}

	@Column(name = "WMA_RUNTIME", length = 19)
	public String getWmaRuntime() {
		return this.wmaRuntime;
	}

	public void setWmaRuntime(String wmaRuntime) {
		this.wmaRuntime = wmaRuntime;
	}

	@Column(name = "WMA_GAME_PROJECT", length = 100)
	public String getWmaGameProject() {
		return this.wmaGameProject;
	}

	public void setWmaGameProject(String wmaGameProject) {
		this.wmaGameProject = wmaGameProject;
	}

	@Column(name = "WMA_YEAR", length = 4)
	public String getWmaYear() {
		return this.wmaYear;
	}

	public void setWmaYear(String wmaYear) {
		this.wmaYear = wmaYear;
	}

	@Column(name = "WMA_SESSION_INDEX")
	public Integer getWmaSessionIndex() {
		return this.wmaSessionIndex;
	}

	public void setWmaSessionIndex(Integer wmaSessionIndex) {
		this.wmaSessionIndex = wmaSessionIndex;
	}

	@Column(name = "WMA_LAST_SESSION_ID")
	public Integer getWmaLastSessionId() {
		return this.wmaLastSessionId;
	}

	public void setWmaLastSessionId(Integer wmaLastSessionId) {
		this.wmaLastSessionId = wmaLastSessionId;
	}

	@Column(name = "WMA_EMERGENCY_CONTRACT", length = 20)
	public String getWmaEmergencyContract() {
		return this.wmaEmergencyContract;
	}

	public void setWmaEmergencyContract(String wmaEmergencyContract) {
		this.wmaEmergencyContract = wmaEmergencyContract;
	}

	@Column(name = "WMA_EMERGENCY_PHONE", length = 20)
	public String getWmaEmergencyPhone() {
		return this.wmaEmergencyPhone;
	}

	public void setWmaEmergencyPhone(String wmaEmergencyPhone) {
		this.wmaEmergencyPhone = wmaEmergencyPhone;
	}

	@Column(name = "WMA_STATUS", length = 10)
	public String getWmaStatus() {
		return this.wmaStatus;
	}

	public void setWmaStatus(String wmaStatus) {
		this.wmaStatus = wmaStatus;
	}

	@Column(name = "WMA_DESC", length = 200)
	public String getWmaDesc() {
		return this.wmaDesc;
	}

	public void setWmaDesc(String wmaDesc) {
		this.wmaDesc = wmaDesc;
	}

	@Column(name = "WMA_REGISTOR")
	public Integer getWmaRegistor() {
		return this.wmaRegistor;
	}

	public void setWmaRegistor(Integer wmaRegistor) {
		this.wmaRegistor = wmaRegistor;
	}

	@Column(name = "WMA_REGIST_DATE", length = 19)
	public String getWmaRegistDate() {
		return this.wmaRegistDate;
	}

	public void setWmaRegistDate(String wmaRegistDate) {
		this.wmaRegistDate = wmaRegistDate;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}