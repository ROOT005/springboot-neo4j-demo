package com.yiqian.relationship.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yiqian.relationship.result.ResponseResult;
import com.yiqian.relationship.domain.Person;
/*import com.yiqian.relationship.repositories.PersonRepository.FriendsData;*/
import com.yiqian.relationship.services.RelationService;

@RestController
@RequestMapping("/relationship")
public class RelationController {
	private final RelationService relationService;
	public RelationController(RelationService relationService) {
		this.relationService = relationService;
	}
	
	/*按名字查找人
	* 请求 localhost:8080/relationship/person?name=Test2
	*/
	@GetMapping("/person")
	public Person findByName(@RequestParam String name) {
		return relationService.findByName(name);
	}
	//模糊查询
	@GetMapping("/friends")
	public Collection<Person> findByNameContaining(@RequestParam String name){
		return relationService.findByNameLike(name);
	}
	
	//所有数据
	@GetMapping("/graph")
	public Collection<Person> graph(@RequestParam int limit){
		return relationService.graph(limit);
	}
	//查找朋友
	@GetMapping("/findfr")
	public Object findFriends(@RequestParam(value="name") String name){
		return relationService.findFriends(name);
	}
	//创建节点
	@PostMapping("/save")
	@Transactional
	public ResponseResult create(@RequestBody Person person) throws Exception{
		System.out.println(person);
		return relationService.save(person);
	}

	//按ID删除数据
	@GetMapping("/delete")
	public Optional<Person> delete(@RequestParam(value="id") Long id){
		return relationService.deleteById(id);
	}
}
