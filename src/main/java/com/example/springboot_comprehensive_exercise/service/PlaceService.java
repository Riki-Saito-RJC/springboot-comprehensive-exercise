package com.example.springboot_comprehensive_exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot_comprehensive_exercise.entitiy.Place;
import com.example.springboot_comprehensive_exercise.repository.PlaceRepository;

@Service
@Transactional
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
