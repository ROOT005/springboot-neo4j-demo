package com.yiqian.personrel.mysql.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiqian.personrel.mysql.domain.User;
import com.yiqian.personrel.mysql.repository.UserRepository;


@Service
@Transactional(value="mysqlTransactionManager")
public class PersonService{
	@Autowired
	private final UserRepository userRepository;
	
	public PersonService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	//查
	
	public Optional<User> findById(Long id) {
		Optional<User> result = userRepository.findById(id);
		return result;
	}
	//增
	public User save(User user) {
		User result = userRepository.save(user);
		return result;
	}
	//删
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	//改
	
	
}
