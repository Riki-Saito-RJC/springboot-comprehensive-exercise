package com.example.springboot_comprehensive_exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_comprehensive_exercise.entitiy.Place;

public interface PlaceRepository extends JpaRepository<Place, String> {

}
