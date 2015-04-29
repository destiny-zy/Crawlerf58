package cn.zy.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zjw_house")
public class ZJW_house extends IdEntity {
	private Long LANDLORD_ID;
	private String TITLE;
	private Integer STATE;
	private String PROVINCE;
	private String CITY;
	private String DISTRICT;
	private String BUSINESS_ZONE;
	private String STREET;
	private String ADDRESS;
	private Integer RENT_STYLE;
	private Integer RENT;
	private Integer RENT_PAYMENT; // 1 押一付三 2 押一付一
	private Integer BEDROOM_NUM;
	private Integer LIVING_ROOM_NUM;
	private Double ROOM_SIZE;
	private Integer HOUSE_FLOOR;
	private Integer TOTAL_FLOOR;
	private Integer DECORATION_DEGREE;
	// private Integer ORIENTATION;
	private Integer IS_AUTH;
	private Integer PIC_NUM;
	private String DESCRIPTION;
	private Date CREATE_TIME;

	public Long getLANDLORD_ID() {
		return LANDLORD_ID;
	}

	public void setLANDLORD_ID(Long lANDLORD_ID) {
		LANDLORD_ID = lANDLORD_ID;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public Integer getSTATE() {
		return STATE;
	}

	public void setSTATE(Integer sTATE) {
		STATE = sTATE;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getDISTRICT() {
		return DISTRICT;
	}

	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}

	public String getBUSINESS_ZONE() {
		return BUSINESS_ZONE;
	}

	public void setBUSINESS_ZONE(String bUSINESS_ZONE) {
		BUSINESS_ZONE = bUSINESS_ZONE;
	}

	public String getSTREET() {
		return STREET;
	}

	public void setSTREET(String sTREET) {
		STREET = sTREET;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public Integer getRENT_STYLE() {
		return RENT_STYLE;
	}

	public void setRENT_STYLE(Integer rENT_STYLE) {
		RENT_STYLE = rENT_STYLE;
	}

	public Integer getRENT() {
		return RENT;
	}

	public void setRENT(Integer rENT) {
		RENT = rENT;
	}

	public Integer getRENT_PAYMENT() {
		return RENT_PAYMENT;
	}

	public void setRENT_PAYMENT(Integer rENT_PAYMENT) {
		RENT_PAYMENT = rENT_PAYMENT;
	}

	public Integer getBEDROOM_NUM() {
		return BEDROOM_NUM;
	}

	public void setBEDROOM_NUM(Integer bEDROOM_NUM) {
		BEDROOM_NUM = bEDROOM_NUM;
	}

	public Integer getLIVING_ROOM_NUM() {
		return LIVING_ROOM_NUM;
	}

	public void setLIVING_ROOM_NUM(Integer lIVING_ROOM_NUM) {
		LIVING_ROOM_NUM = lIVING_ROOM_NUM;
	}

	public Double getROOM_SIZE() {
		return ROOM_SIZE;
	}

	public void setROOM_SIZE(Double rOOM_SIZE) {
		ROOM_SIZE = rOOM_SIZE;
	}

	public Integer getHOUSE_FLOOR() {
		return HOUSE_FLOOR;
	}

	public void setHOUSE_FLOOR(Integer hOUSE_FLOOR) {
		HOUSE_FLOOR = hOUSE_FLOOR;
	}

	public Integer getTOTAL_FLOOR() {
		return TOTAL_FLOOR;
	}

	public void setTOTAL_FLOOR(Integer tOTAL_FLOOR) {
		TOTAL_FLOOR = tOTAL_FLOOR;
	}

	public Integer getDECORATION_DEGREE() {
		return DECORATION_DEGREE;
	}

	public void setDECORATION_DEGREE(Integer dECORATION_DEGREE) {
		DECORATION_DEGREE = dECORATION_DEGREE;
	}

	public Integer getIS_AUTH() {
		return IS_AUTH;
	}

	public void setIS_AUTH(Integer iS_AUTH) {
		IS_AUTH = iS_AUTH;
	}

	public Integer getPIC_NUM() {
		return PIC_NUM;
	}

	public void setPIC_NUM(Integer pIC_NUM) {
		PIC_NUM = pIC_NUM;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public ZJW_house(Long lANDLORD_ID, String tITLE, Integer sTATE,
			String pROVINCE, String cITY, String dISTRICT,
			String bUSINESS_ZONE, String sTREET, String aDDRESS,
			Integer rENT_STYLE, Integer rENT, Integer rENT_PAYMENT,
			Integer bEDROOM_NUM, Integer lIVING_ROOM_NUM, Double rOOM_SIZE,
			Integer hOUSE_FLOOR, Integer tOTAL_FLOOR,
			Integer dECORATION_DEGREE, Integer iS_AUTH, Integer pIC_NUM,
			String dESCRIPTION, Date cREATE_TIME) {
		super();
		LANDLORD_ID = lANDLORD_ID;
		TITLE = tITLE;
		STATE = sTATE;
		PROVINCE = pROVINCE;
		CITY = cITY;
		DISTRICT = dISTRICT;
		BUSINESS_ZONE = bUSINESS_ZONE;
		STREET = sTREET;
		ADDRESS = aDDRESS;
		RENT_STYLE = rENT_STYLE;
		RENT = rENT;
		RENT_PAYMENT = rENT_PAYMENT;
		BEDROOM_NUM = bEDROOM_NUM;
		LIVING_ROOM_NUM = lIVING_ROOM_NUM;
		ROOM_SIZE = rOOM_SIZE;
		HOUSE_FLOOR = hOUSE_FLOOR;
		TOTAL_FLOOR = tOTAL_FLOOR;
		DECORATION_DEGREE = dECORATION_DEGREE;

		IS_AUTH = iS_AUTH;
		PIC_NUM = pIC_NUM;
		DESCRIPTION = dESCRIPTION;
		CREATE_TIME = cREATE_TIME;
	}

	public ZJW_house() {

	}
}
