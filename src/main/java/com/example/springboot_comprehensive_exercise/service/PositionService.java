package com.example.springboot_comprehensive_exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot_comprehensive_exercise.entitiy.Position;
import com.example.springboot_comprehensive_exercise.repository.PositionRepository;

@Service
@Transactional
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
