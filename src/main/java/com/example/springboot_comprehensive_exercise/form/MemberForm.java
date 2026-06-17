package com.example.springboot_comprehensive_exercise.form;

import com.example.springboot_comprehensive_exercise.entitiy.Member;
import com.example.springboot_comprehensive_exercise.entitiy.Place;
import com.example.springboot_comprehensive_exercise.entitiy.Position;

import lombok.Data;

/**
 * メンバーフォームクラス
 * フォームの入力値を保持するクラス
 * @author riki_saito
 */
@Data
public class MemberForm {

	/** メンバーID */
	private String memberId;

	/** メンバー名 */
	private String memberName;

	/** 年齢 */
	private Integer age;

	/** 性別フラグ */
	private Integer sexFlg;

	/** 住所 */
	private String address;

	/** 電話番号 */
	private String telephone;

	/** メールアドレス */
	private String mail;

	/** 役職ID */
	private String positionId;

	/** 事業所ID */
	private String placeId;

	/**
	 * フォームの入力値をMemberエンティティに変換するメソッド
	 * @param position 役職
	 * @param place 事業所
	 * @return Memberエンティティ
	 */
	public Member toEntity(Position position, Place place) {
		
		Member member = new Member();
		
		member.setMemberId(memberId);
		member.setMemberName(memberName);
		member.setAge(age);
		member.setSexFlg(sexFlg);
		member.setAddress(address);
		member.setTelephone(telephone);
		member.setMail(mail);

		member.setPosition(position);
		member.setPlace(place);

		return member;
	}
	
	/**
	 * Memberエンティティからフォームに変換するメソッド
	 * @param member
	 * @return Memberフォーム
	 */
	public static MemberForm fromEntity(Member member) {

		MemberForm form = new MemberForm();

		form.setMemberId(member.getMemberId());
		form.setMemberName(member.getMemberName());
		form.setAge(member.getAge());
		form.setSexFlg(member.getSexFlg());
		form.setAddress(member.getAddress());
		form.setTelephone(member.getTelephone());
		form.setMail(member.getMail());

		if (member.getPosition() != null) {
			form.setPositionId(
					member.getPosition().getPositionId());
		}

		if (member.getPlace() != null) {
			form.setPlaceId(
					member.getPlace().getPlaceId());
		}

		return form;
	}

}
