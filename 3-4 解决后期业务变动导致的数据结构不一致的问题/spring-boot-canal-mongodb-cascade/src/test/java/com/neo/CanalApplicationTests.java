package com.neo;

import com.neo.canal.config.CanalProperties;
import com.neo.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(value = {SpringUtil.class})
@EnableConfigurationProperties({CanalProperties.class})
public class CanalApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("hello world");
	}

}
