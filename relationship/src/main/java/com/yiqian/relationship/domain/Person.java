package com.yiqian.relationship.domain;

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
	private String name;
	
	@JsonIgnoreProperties("person")
	@Relationship(type="Relationship")
	private List<RelationShip> friends = new ArrayList<>();
	public Person() {}
	public Person(String name) {
		this.name=name;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<RelationShip> getFriends(){
		return friends;
	}
}
