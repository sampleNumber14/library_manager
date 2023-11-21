package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "libraries")
public class Library {
	
	@Id
    @SequenceGenerator(name = "LIBRARY_ID_GENERATOR", sequenceName = "LIBRARY_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIBRARY_ID_GENERATOR")
	
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "USER_ID")
	private Integer userId;
	
	//本のID取得
	public Integer getId() {
		return this.id;
	}
	
	//本のIDセット
	public void setId(Integer id) {
		this.id = id;
	}
	
	// 本を借りた奴のID取得
	public Integer getUserId() {
		return this.userId;
	}
	
	// 本に借りた奴のユーザIDセット
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	//本の名前取得
	public String getName() {
		return this.name;
	}
	
	//本の名前セット
	public void setName(String name) {
		this.name = name;
	}

}
