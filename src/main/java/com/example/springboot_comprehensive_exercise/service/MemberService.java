package com.example.springboot_comprehensive_exercise.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot_comprehensive_exercise.entitiy.Member;
import com.example.springboot_comprehensive_exercise.entitiy.Place;
import com.example.springboot_comprehensive_exercise.entitiy.Position;
import com.example.springboot_comprehensive_exercise.form.MemberForm;
import com.example.springboot_comprehensive_exercise.repository.MemberRepository;
import com.example.springboot_comprehensive_exercise.repository.PlaceRepository;
import com.example.springboot_comprehensive_exercise.repository.PositionRepository;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private PlaceRepository placeRepository;

	/**
	 * 全メンバーのリストを取得するメソッド
	 * @return 全メンバーのリスト
	 */
	public List<Member> findAll() {
		return memberRepository.findAll(Sort.by("memberId"));
	}
	
	/**
	 * IDで指定したメンバーを取得するメソッド
	 * @param メンバーID
	 * @return Optional型メンバークラス
	 */
	public Optional<Member> findById(String id) {
		return memberRepository.findById(id);
	}
	
	/**
	 * IDで指定したメンバーを削除するメソッド
	 * @param メンバーID
	 */
	public void deleteById(String id) {
		memberRepository.deleteById(id);
	}
	/**
	 * 
	 * @param form
	 * @return Memberのインスタンス
	 */
	public Member createMember(MemberForm form) {

		//IDから役職と事業所のインスタンスを取得
		Position position = getPosition(form.getPositionId());
		Place place = getPlace(form.getPlaceId());

		//MemberFormクラスのインスタンスをMemberエンティティに変換
		Member member = form.toEntity(position, place);
		
		//登録日時、更新日時、削除フラグを設定
		member.setRegistDate(LocalDateTime.now());
		member.setUpdateDate(LocalDateTime.now());
		member.setDeleteFlg(0);
		
		//Memberエンティティを保存
		return memberRepository.save(member);
	}

	/**
	 * IDから役職と事業所のインスタンスを取得するメソッド
	 * @param positionId 役職ID
	 * @param placeId 事業所ID
	 * @return 役職と事業所のインスタンス
	 */
	public Position getPosition(String positionId) {
		return positionRepository.findById(positionId).orElse(null);
	}

	/**
	 * IDから事業所のインスタンスを取得するメソッド
	 * @param placeId 事業所ID
	 * @return 事業所のインスタンス
	 */
	public Place getPlace(String placeId) {
		return placeRepository.findById(placeId).orElse(null);
	}
	
	/**
	 * Memberのプロパティを更新するメソッド
	 * @param id
	 * @param form
	 * @return
	 */
	public Member updateMember(String id, MemberForm form, Position position, Place place) {
		
		//更新前の情報を持ったMemberを用意
		Member member = memberRepository.findById(id).orElse(null);

		//フォームに入力されたプロパティのみ更新
		member.update(form, position, place);
		
		//DBに保存
		return memberRepository.save(member);
	}
}