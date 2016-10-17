package com.gardecote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gardecote.GardecoteApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GardecoteApplication.class)
@WebAppConfiguration
public class GardecoteApplicationTests {

	@Test
	public void contextLoads() {
	}

}
