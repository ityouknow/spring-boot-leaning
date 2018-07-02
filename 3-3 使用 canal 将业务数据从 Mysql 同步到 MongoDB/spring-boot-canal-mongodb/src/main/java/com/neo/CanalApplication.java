package com.neo;

import com.neo.canal.config.CanalProperties;
import com.neo.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import(value = {SpringUtil.class})
@EnableConfigurationProperties({CanalProperties.class})
@SpringBootApplication
public class CanalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanalApplication.class, args);
	}
}
