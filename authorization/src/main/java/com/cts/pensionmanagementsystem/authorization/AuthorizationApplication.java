package com.cts.pensionmanagementsystem.authorization;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.cts.pensionmanagementsystem.authorization.entity.UserInfo;
import com.cts.pensionmanagementsystem.authorization.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class AuthorizationApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		log.info("START - main() of AuthorizationApplication");
		SpringApplication.run(AuthorizationApplication.class, args);
		log.info("END - main() of AuthorizationApplication");
	}

	@PostConstruct
	public void initUser() {

		log.info("START - initUser() of AuthorizationApplication");

		List<UserInfo> usersInfoList = Stream.of(new UserInfo(1,"priya", "priya@123"), new UserInfo(2,"sai", "sai@123")).toList();
		userRepository.saveAll(usersInfoList);

		log.info("END - initUser() of AuthorizationApplication");
	}

}
