package com.example.springboot_comprehensive_exercise.entitiy;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.example.springboot_comprehensive_exercise.form.MemberForm;

@Entity
@Table(name = "tbl_member")
public class Member {

	@Id
	@Column(name = "member_id")
	private String memberId;

	@Column(name = "member_name")
	private String memberName;

	@Column(name = "mail")
	private String mail;

	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;
	
//	@Column(name = "position_id")
//	private String positionId;
	
	@ManyToOne
	@JoinColumn(name = "place_id")
	private Place place;
	
//	@Column(name = "place_id")
//	private String placeId;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "address")
	private String address;

	@Column(name = "age")
	private Integer age;

	@Column(name = "sex_flg")
	private Integer sexFlg;

	@Column(name = "delete_flg")
	private Integer deleteFlg;

	@Column(name = "regist_date")
	private LocalDateTime registDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSexFlg() {
		return sexFlg;
	}

	public void setSexFlg(Integer sexFlg) {
		this.sexFlg = sexFlg;
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
	
	/**
	 * フィールドをフォームの入力情報に更新するメソッド
	 * @param form 更新情報
	 * @param position 役職
	 * @param place 事業所
	 */
	public void update(MemberForm form, Position position, Place place) {
		
		this.memberName = form.getMemberName();
		this.age = form.getAge();
		this.sexFlg = form.getSexFlg();
		this.address = form.getAddress();
		this.telephone = form.getTelephone();
		this.mail = form.getMail();
		this.position = position;
		this.place = place;
	}
}