package com.example.springboot_comprehensive_exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot_comprehensive_exercise.entitiy.Position;
import com.example.springboot_comprehensive_exercise.repository.PositionRepository;

public class PositionService {
	
	@Autowired
	PositionRepository positionRepository;
	
	/**
	 * 役職のリストを取得するメソッド
	 * @return 役職のリスト
	 */
	public List<Position> findAll() {
		return positionRepository.findAll();	
	}
	
}
