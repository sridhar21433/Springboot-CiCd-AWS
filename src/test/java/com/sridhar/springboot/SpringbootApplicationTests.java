package com.sridhar.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

	@Test
	void contextLoads() {
	}
    @Test
	void pipelineShouldPass(){
		org.junit.jupiter.api.Assertions.assertEquals(2, 2);
	}
}
