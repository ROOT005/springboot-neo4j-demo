package com.yiqian.personrel.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.yiqian.personrel.neo4j.domain.Person;

public interface RelationShipRepository extends Neo4jRepository<Person, Long> {	
	Long deleteByUid(Long id);
}
