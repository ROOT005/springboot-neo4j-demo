package com.yiqian.personrel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yiqian.personrel.message.ResponseResult;
import com.yiqian.personrel.mysql.domain.User;
import com.yiqian.personrel.mysql.services.PersonService;
import com.yiqian.personrel.neo4j.domain.Person;
import com.yiqian.personrel.neo4j.services.RelationshipService;


@RestController
@RequestMapping("/relationship")
public class RelationController {
	
	@Autowired
	private final RelationshipService relationshipService;

	@Autowired
	private final PersonService personService;
	
	public RelationController(RelationshipService relationshipService,
			PersonService personService) {
		this.relationshipService = relationshipService;
		this.personService = personService;
	}
	//查
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET)
	public  Optional<User> findById(@PathVariable Long id) {
		Optional<Person> person = relationshipService.findById(id);
		Optional<User> user = personService.findById(person.get().getUid());
		return user;
	}
	
	//增
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public  ResponseResult home(@RequestBody User user) {
		//新建MySql记录
		User result = personService.save(user);
		if (result != null) {
			//把返回的ID写入新建的图数据库节点ID
			//新建图数据库空节点
			Person newPerson = new Person(result.getId());
			
			Person person = relationshipService.save(newPerson);
			if(person!=null) {
				return new ResponseResult(200,"创建成功");
			}
			return new ResponseResult(500,"创建失败");
		}
		return new ResponseResult(500,"创建失败");
	}
	//添加关系
	
	//删
	@RequestMapping(value="/id/{id}", method=RequestMethod.DELETE)
	public ResponseResult delete(@PathVariable Long id) {
		personService.delete(id);
		Long result = relationshipService.delete(id);
		if(result != null) {
			return new ResponseResult(200,"删除成功");
		}
		return new ResponseResult(500,"删除失败");
	}
	
}
