package com.neo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * maxInactiveIntervalInSeconds: 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3000)
public class SessionConfig {
}
