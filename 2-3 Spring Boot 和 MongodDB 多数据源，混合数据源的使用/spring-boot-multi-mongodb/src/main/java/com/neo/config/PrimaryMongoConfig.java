package com.neo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.neo.config.props.MultipleMongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author neo
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.neo.repository.primary",
		mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
	@Autowired
	private MultipleMongoProperties mongoProperties;

	@Primary
	@Bean
	public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception {
		MongoClient client = new MongoClient(new MongoClientURI(mongoProperties.getPrimary().getUri()));
		return new SimpleMongoDbFactory(client, mongoProperties.getPrimary().getDatabase());
	}

	@Primary
	@Bean(name = "primaryMongoTemplate")
	public MongoTemplate primaryMongoTemplate() throws Exception {
		return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
	}
}
