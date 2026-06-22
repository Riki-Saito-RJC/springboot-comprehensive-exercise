package com.example.springboot_comprehensive_exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot_comprehensive_exercise.entitiy.Place;
import com.example.springboot_comprehensive_exercise.repository.PlaceRepository;

public class PlaceService {
	@Autowired
	PlaceRepository placeRepository;
	
	/**
	 * 事業所のリストを取得するメソッド
	 * @return 事業所のリスト
	 */
	public List<Place> findAll() {
		return placeRepository.findAll();	
	}
}
