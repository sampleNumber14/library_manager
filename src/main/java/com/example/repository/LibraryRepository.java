package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer> {
	// 移行必要な機能の実装
	
	public List<Library> findAll();
	
	Optional<Library> findById(Integer id);
}
