package com.yiqian.personrel.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiqian.personrel.mysql.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
