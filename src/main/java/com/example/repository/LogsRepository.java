package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Logs;

@Repository
public interface LogsRepository extends JpaRepository<Logs,Integer> {
	// 移行必要な機能の実装

}
