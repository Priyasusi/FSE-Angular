//package com.cts.pensionmanagementsystem.authorization.service;
//
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//import com.cts.pensionmanagementsystem.authorization.AuthorizationApplication;
//import com.cts.pensionmanagementsystem.authorization.controller.AuthorizationController;
//import com.cts.pensionmanagementsystem.authorization.entity.UserInfo;
//import com.cts.pensionmanagementsystem.authorization.repository.UserRepository;
//import com.cts.pensionmanagementsystem.authorization.service.UserDetailsServiceImpl;
//
//@SpringBootTest
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes = UserDetailsServiceImpl.class)
//public class UserDetailsServiceImplTest {
//	
//	@Mock
//	private UserRepository userRepository;
//	
//	@InjectMocks
//	private UserDetailsServiceImpl userDetailsServiceImpl;	
//	
//	@Test
//	void loadUserByUserNameShouldUserNameTest() throws Exception{
//		when(userRepository.findByUsername("priya")).thenReturn(new UserInfo(1,"priya","priya@123"));
//		assertNotNull(userDetailsServiceImpl.loadUserByUsername("priya"));
//		verify(userRepository,Mockito.times(1)).findByUsername("priya");
//	}
//
//}
