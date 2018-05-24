package com.yiqian.personrel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude= {Neo4jDataAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class PersonrelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonrelApplication.class, args);
	}
}
