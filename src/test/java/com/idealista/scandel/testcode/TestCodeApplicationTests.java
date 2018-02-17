package com.idealista.scandel.testcode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCodeApplicationTests {

	/**
	 * Empty method to test context injection
	 */
	@Test
	public void contextLoads() {
		//Do nothing
		Assert.assertTrue(true);
	}

}
