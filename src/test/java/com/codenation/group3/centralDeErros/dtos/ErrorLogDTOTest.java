package com.codenation.group3.centralDeErros.dtos;

import static org.junit.jupiter.api.Assertions.*;
import static com.google.code.beanmatchers.BeanMatchers.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

class ErrorLogDTOTest {

	@Test
	void testErrorLogDTO() {
		assertNotNull(new ErrorLogDTO());
		
		assertThat(
				ErrorLogDTO.class, 
				allOf(hasValidBeanConstructor(), 
				hasValidBeanEquals(), 
				hasValidGettersAndSetters(), 
				hasValidBeanHashCode(), 
				hasValidBeanToString()));
	}

}
