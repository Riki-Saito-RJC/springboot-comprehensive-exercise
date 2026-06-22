package com.example.springboot_comprehensive_exercise.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.springboot_comprehensive_exercise.form.MemberForm;
import com.example.springboot_comprehensive_exercise.service.MemberService;

/**
 * メンバー情報 入力チェック用のバリデーションクラス
 * @author riki_saito
 */
@Component
public class MemberValidator implements Validator{
	
	@Autowired
	private MemberService memberService;

	/**
	 * キー名("member")オブジェクトの型判定メソッド
	 * @return 
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO 自動生成されたメソッド・スタブ
		//MemberFormに割り当て可能かどうか
		return MemberForm.class.isAssignableFrom(clazz);
	}

	/**
	 * フォーム入力値を検証するメソッド
	 * 
	 * @param target
	 * @param errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// TODO 自動生成されたメソッド・スタブ
		
		//検証対象をキャスト
		MemberForm form = (MemberForm) target;
		
		//memberIdチェック
		//nullか空文字か空白なら
		if(Objects.isNull(form.getMemberId()) || form.getMemberId().isBlank()) {
			return;
		}
	}

}
