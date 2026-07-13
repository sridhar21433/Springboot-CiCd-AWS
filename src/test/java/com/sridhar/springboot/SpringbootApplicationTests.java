package com.sridhar.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

	@Test
	void contextLoads() {
	}
    @Test
	void pipelineShouldStopForFailedTests(){
		org.junit.jupiter.api.Assertions.assertEquals(1, 2);
	}
}
