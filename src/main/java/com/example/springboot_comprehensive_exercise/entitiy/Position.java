package com.example.springboot_comprehensive_exercise.entitiy;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mst_position")
public class Position {

	@Id
	@Column(name = "position_id")
	private String positionId;

	@Column(name = "position_name")
	private String positionName;

	@Column(name = "access_authority")
	private String accessAuthority;

	@Column(name = "regist_date")
	private LocalDateTime registDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column(name = "delete_flg")
	private Integer deleteFlg;
	
	public String getPositionId() {
		return positionId;
	}
	
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public LocalDateTime getRegistDate() {
		return registDate;
	}

	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
}
