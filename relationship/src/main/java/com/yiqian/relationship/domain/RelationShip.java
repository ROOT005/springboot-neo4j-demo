package com.yiqian.relationship.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="Relationship")
public class RelationShip {
	@Id
	@GeneratedValue
	private Long id;
	
	//关系
	private List<String> relationship = new ArrayList<>();
	
	@StartNode
	private Person person;
	@EndNode
	private Person friend;
	
	public RelationShip() {}
	public RelationShip(Person person, Person friend) {
		this.person = person;
		this.friend = friend;
	}
	public Long getId() {
		return id;
	}
	public List<String> getRelationship(){
		return relationship;
	}
	public Person getPerson() {
		return person;
	}
	public Person getFriend() {
		return friend;
	}
	public void addRelationshipName(String name) {
		if (this.relationship == null) {
			this.relationship = new ArrayList<>();
		}
		this.relationship.add(name);
	}
}
