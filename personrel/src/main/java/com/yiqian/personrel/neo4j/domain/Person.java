package com.yiqian.personrel.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	private Long uid;

	@JsonIgnoreProperties("person")
	@Relationship(type="Relationship")
	private List<RelationShip> friends = new ArrayList<>();
	public Person() {}
	public Person(Long uid) {
		this.uid = uid;
	}
	public Long getId() {
		return id;
	}
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public List<RelationShip> getFriends(){
		return friends;
	}
	
}
