//package com.codenation.group3.centralDeErros.dtos;
//
//import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
//import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
//import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
//import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
//import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
//import static org.hamcrest.CoreMatchers.allOf;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//class UserDTOTest {
//
//	@Test
//	void testUserDTO() {
//		assertNotNull(new UserDTO());
//		
//		assertThat(
//				UserDTO.class, 
//				allOf(hasValidBeanConstructor(), 
//				hasValidBeanEquals(), 
//				hasValidGettersAndSetters(), 
//				hasValidBeanHashCode(), 
//				hasValidBeanToString()));
//	}
//}
