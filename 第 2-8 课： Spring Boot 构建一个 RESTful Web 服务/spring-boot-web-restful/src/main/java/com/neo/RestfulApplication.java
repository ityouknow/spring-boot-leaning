
package com.neo;

import com.neo.model.Message;
import com.neo.repository.InMemoryMessageRepository;
import com.neo.repository.MessageRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;


@SpringBootApplication
public class RestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}

}
