package com.cts.pensionmanagementsystem.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.pensionmanagementsystem.authorization.entity.UserInfo;


@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo findByUsername(String username);

}
