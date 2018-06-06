package com.yiqian.personrel.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.lang.Nullable;

import com.yiqian.personrel.neo4j.domain.Person;

public interface RelationShipRepository extends Neo4jRepository<Person, Long> {
	@Nullable
	Long deleteByUid(Long id);
}
