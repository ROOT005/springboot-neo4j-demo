package com.yiqian.personrel.neo4j.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiqian.personrel.neo4j.domain.Person;
import com.yiqian.personrel.neo4j.repository.RelationShipRepository;

@Service
//保证事务完整性
@Transactional(readOnly=true,value="neo4jTransactionManager")
public class RelationshipService {
	private final RelationShipRepository relationShipRepository;
	public RelationshipService(RelationShipRepository relationShipRepository) {
		this.relationShipRepository = relationShipRepository;
	}
	
	public Optional<Person> findById(Long id) {
		Optional<Person> result = relationShipRepository.findById(id);
		return result;
	}
	//保存
	public Person save(Person person) {
		Person result = relationShipRepository.save(person);
		return result;
	}
	
	//删除
	public Long delete(Long id) {
		Long result = relationShipRepository.deleteByUid(id);
		return result;
	}
}
