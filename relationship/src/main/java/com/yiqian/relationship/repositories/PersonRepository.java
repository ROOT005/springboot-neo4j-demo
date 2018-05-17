package com.yiqian.relationship.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
//import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.yiqian.relationship.domain.Person;
//import com.yiqian.relationship.domain.RelationShip;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
	Person findByName(@Param("name") String name);
	
	Collection<Person> findByNameContaining(@Param("name") String name);
	
	@Query("MATCH(p:Person)-[f:Relationship]-(fp:Person) RETURN p,f,fp LIMIT {limit}")
	Collection<Person> graph(@Param("limit") int limit);
	
	//Match(n:Person) where n.name={name} with n  return [(n)-[f:Relationship]-(p:Person) | [n,f,p]]
	@Query("Match(n:Person) where n.name={name} with n  return [(n)-[f:Relationship]-(p:Person) | [n,f,p]] as result")
	Object getFriendsData(@Param("name") String name);
	
/*	@QueryResult
	public class FriendsData{
		Person person;
		RelationShip relationShip;
		Person friend;
	}*/
}
