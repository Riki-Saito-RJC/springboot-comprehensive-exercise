package com.example.springboot_comprehensive_exercise.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springboot_comprehensive_exercise.entitiy.Member;
import com.example.springboot_comprehensive_exercise.entitiy.Place;
import com.example.springboot_comprehensive_exercise.entitiy.Position;
import com.example.springboot_comprehensive_exercise.form.MemberForm;
import com.example.springboot_comprehensive_exercise.service.MemberService;
import com.example.springboot_comprehensive_exercise.service.PlaceService;
import com.example.springboot_comprehensive_exercise.service.PositionService;

@Controller
public class MemberController {

	@Autowired
	private PositionService positionService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private MemberService memberService;
	
//	/**
//	 * メソッド実行前処理用メソッド
//	 * Modelにキー名「user」で登録されたオブジェクトが存在する場合に実行される
//	 * 
//	 * @param binder
//	 */
//	@InitBinder("member")
//	public void initVinder(WebDataBinder binder) {
//		//フォームの値が自動的に MemberForm に入るとき（データバインド）、
//		//独自のバリデーターが実施される
//		binder.addValidators(this.memberValidator);
//	}
	
	/**
	 * ホーム画面の表示
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * 新規登録画面の表示
	 * @param model モデル
	 * @return 新規登録画面
	 */
	@GetMapping("/member/insert")
	public String showInsert(@ModelAttribute("member") MemberForm form, Model model) {

		//初期値を設定
		form.setMemberId("ME000000__");
		form.setPositionId("PO00000007");
		form.setPlaceId("PL00000001");

		//モデルに役職と事業所のリストを追加
		setMasterData(model);

		return "member/insert";
	}

	/**
	 * 新規登録確認画面の表示
	 * @param model モデル
	 * @return 新規登録確認画面
	 */
	@PostMapping("/member/insertConf")
	public String insertConf(@Validated @ModelAttribute("member") MemberForm form,
			BindingResult result, Model model) {

		//入力値にエラーがあれば登録画面を再表示
		if (result.hasErrors()) {
			//モデルに役職と事業所のリストを追加
			setMasterData(model);
			return "member/insert";
		}

		// formからModelに役職と事業所の表示名をセット
		setDisplayNames(form);

		return "member/insertConf";
	}

	/**
	 * 新規登録画面の表示（戻る）
	 * @param form フォーム
	 * @param model モデル
	 * @return 新規登録画面
	 */
	//リクエストパラメーターに"back"が含まれている場合にこのメソッドが呼び出される
	@PostMapping(value = "/member/insert", params = "back")
	public String back(@ModelAttribute("member") MemberForm form, Model model) {

		//モデルに役職と事業所のリストを追加
		setMasterData(model);

		return "member/insert";
	}

	/**
	 * 新規登録完了画面の表示
	 * @param model モデル
	 * @return 新規登録完了画面
	 */
	@PostMapping("/member/insertComp")
	public String insertComp(@ModelAttribute("member") MemberForm form, Model model) {

		//DBに保存
		memberService.createMember(form);

		return "member/insertComp";
	}

	/**
	 * 一覧画面の表示
	 * @param model
	 * @return
	 */
	@RequestMapping("/member/list")
	public String list(Model model) {

		model.addAttribute("members", memberService.findAll());
		return "member/list";
	}

	/**
	 * 更新画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/member/update/{id}")
	public String showUpdate(@PathVariable String id,
			@ModelAttribute("member") MemberForm form, Model model) {

		//IDからMemberのインスタンスを取得
		Member member = memberService.findById(id).orElse(new Member());

		if (Objects.isNull(member.getMemberId())) {
			return "redirect:/member/list";
		}

		//エンティティからフォームに変換
		form = MemberForm.fromEntity(member);
		//モデルにフォームを追加
		model.addAttribute("member", form);
		//モデルに役職と事業所のリストを追加
		setMasterData(model);

		return "member/update";
	}

	/**
	 * 更新確認画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/member/updateConf")
	public String updateConf(@ModelAttribute("member") MemberForm form, Model model) {

		// Modelに役職と事業所の表示名をセット
		setDisplayNames(form);

		return "member/updateConf";
	}

	/**
	 * 更新完了画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/member/updateComp")
	public String updateComp(@ModelAttribute("member") MemberForm form, Model model) {

		///formから役職と事業所を取得
		Position position = memberService.getPosition(form.getPositionId());
		Place place = memberService.getPlace(form.getPlaceId());

		//存在しないidであれば一覧画面へリダイレクト
		if (Objects.isNull(position) || Objects.isNull(place)) {
			return "redirect:/member/list";
		}

		//更新処理
		memberService.updateMember(form, position, place);

		// Modelに役職と事業所の表示名をセット
		setDisplayNames(form);

		return "member/updateComp";
	}

	/**
	 * 削除確認画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/member/deleteConf/{id}")
	public String showDelete(@PathVariable String id,MemberForm form, Model model) {

		//nullなら空のインスタンスを返す
		Member member = memberService.findById(id).orElse(new Member());
		Position position = member.getPosition();
		Place place = member.getPlace();

		if (Objects.isNull(position) || Objects.isNull(place)) {
			//メンバー一覧へ
			return "redirect:/member/list";
		}

		//EntityをFormに変換
		MemberForm memberForm = MemberForm.fromEntity(member);
		//Modelに役職と事業所の表示名をセット
		setDisplayNames(memberForm);
		//ModelにFormをセット
		model.addAttribute("member", memberForm);
		
		return "member/deleteConf";
	}
	

	/**
	 * 削除完了画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/member/deleteComp")
	public String detele(MemberForm form, Model model) {
		
		String id = form.getMemberId();
		
		//idからMemberを取得
		Member member = memberService.findById(id).orElse(new Member());
		Position position = member.getPosition();
		Place place = member.getPlace();

		if (Objects.isNull(position) || Objects.isNull(place)) {
			//メンバー一覧へ
			return "redirect:/member/list";
		}

		//EntityをFormに変換
		MemberForm memberForm = MemberForm.fromEntity(member);
		// Modelに役職と事業所の表示名をセット
		setDisplayNames(memberForm);
		//modelにセット
		model.addAttribute("member", memberForm);

		//削除処理
		memberService.deleteById(id);

		return "member/deleteComp";
	}

	/**
	 * 詳細画面の表示
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/member/detail/{id}")
	public String showDetail(@PathVariable String id, Model model) {

		//IDからMemberのインスタンスを取得
		Member member = memberService.findById(id).orElse(new Member());

		if (Objects.isNull(member.getMemberId())) {
			return "redirect:/member/list";
		}

		model.addAttribute("member", member);

		return "member/detail";
	}

	/**
	 * Modelに全ての役職と事業所をセットするメソッド
	 * @param model
	 */
	private void setMasterData(Model model) {
		model.addAttribute("positions", positionService.findAll());
		model.addAttribute("places", placeService.findAll());
	}

	/**
	 * formに役職と事業所の表示名をセットするメソッド
	 * @param form
	 * @param model
	 */
	private void setDisplayNames(MemberForm form) {
		Position position = memberService.getPosition(form.getPositionId());
		Place place = memberService.getPlace(form.getPlaceId());

		form.setPositionName(position.getPositionName());
		form.setPlaceName(place.getPlaceName());
	}

}