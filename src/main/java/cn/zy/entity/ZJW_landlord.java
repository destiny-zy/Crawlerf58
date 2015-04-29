package cn.zy.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zjw_landlord")
public class ZJW_landlord {
	@Id
	private Long id;
	private Integer CREDIT_VALUE;
	private Integer IS_AUTH;
	private Date CREATE_TIME;

	public ZJW_landlord(Long id, Integer cREDIT_VALUE, Integer iS_AUTH,
			Date cREATE_TIME) {
		super();
		this.id = id;
		CREDIT_VALUE = cREDIT_VALUE;
		IS_AUTH = iS_AUTH;
		CREATE_TIME = cREATE_TIME;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCREDIT_VALUE() {
		return CREDIT_VALUE;
	}

	public void setCREDIT_VALUE(Integer cREDIT_VALUE) {
		CREDIT_VALUE = cREDIT_VALUE;
	}

	public Integer getIS_AUTH() {
		return IS_AUTH;
	}

	public void setIS_AUTH(Integer iS_AUTH) {
		IS_AUTH = iS_AUTH;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public ZJW_landlord() {
	}
}
