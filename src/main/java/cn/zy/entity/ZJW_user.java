package cn.zy.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zjw_user")
public class ZJW_user extends IdEntity {
	private String LAST_NAME;
	private String PHONE;
	private Date CREATE_TIME;
	private Integer GENDER;
	private Integer USER_TYPE;

	public ZJW_user(String lAST_NAME, String pHONE, Date cREATE_TIME,
			Integer gender, Integer uSER_TYPE) {
		super();
		LAST_NAME = lAST_NAME;
		PHONE = pHONE;
		CREATE_TIME = cREATE_TIME;
		GENDER = gender;
		USER_TYPE = uSER_TYPE;
	}

	public Integer getGENDER() {
		return GENDER;
	}

	public void setGENDER(Integer gENDER) {
		GENDER = gENDER;
	}

	public ZJW_user() {

	}

	public Integer getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(Integer uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

}
