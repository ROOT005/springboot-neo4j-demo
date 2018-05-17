package com.yiqian.relationship.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiqian.relationship.domain.Person;
import com.yiqian.relationship.repositories.PersonRepository;
/*import com.yiqian.relationship.repositories.PersonRepository.FriendsData;*/
import com.yiqian.relationship.result.ResponseResult;

@Service
public class RelationService {
	private final PersonRepository personRepository;
	public RelationService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	@Transactional(readOnly=true)
	public Person findByName(String name) {
		Person result = personRepository.findByName(name);
		return result;
	}
	@Transactional(readOnly=true)
	public Collection<Person> findByNameLike(String name){
		Collection<Person> result = personRepository.findByNameContaining(name);
		return result;
	}
	@Transactional(readOnly=true)
	public Collection<Person> graph(int limit){
		Collection<Person> result = personRepository.graph(limit);
		return result;
	}
	@Transactional(readOnly=true)
	public Object findFriends(String name) {
		Object result = personRepository.getFriendsData(name);
		return result;
	}
	@Transactional(readOnly=true)
	public ResponseResult save(Person person) {
		Person result = personRepository.save(person);
	
		if(result != null) {
			return new ResponseResult(200, result.getName()+"节点创建成功");
		}
		return new ResponseResult(500, person.getName()+"节点创建失败");
	}
	@Transactional(readOnly=true)
	public Optional<Person> deleteById(Long id) {
		Optional<Person> result = personRepository.findById(id);
		personRepository.deleteById(id);
		return result;
	}
}
