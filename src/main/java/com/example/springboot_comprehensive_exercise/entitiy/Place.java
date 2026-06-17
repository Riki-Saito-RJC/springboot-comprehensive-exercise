package com.example.springboot_comprehensive_exercise.entitiy;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mst_place")
public class Place {

	@Id
	@Column(name = "place_id")
	private String placeId;

	@Column(name = "place_name")
	private String placeName;

	@Column(name = "address")
	private String address;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "mail")
	private String mail;

	@Column(name = "regist_date")
	private LocalDateTime registDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column(name = "delete_flg")
	private Integer deleteFlg;
	
	public String getPlaceName() {
		return placeName;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
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
