package br.com.molar.produtos.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Setter
public class RedisConfig {

    private String host;
//    private String password;

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisConfiguration defaultRedisConfig) {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl().and()
                .commandTimeout(Duration.ofMillis(60000)).build();
        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    }

    @Bean
    public RedisConfiguration defaultRedisConfig() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
//        config.setPassword(RedisPassword.of(password));
        return config;
    }
}

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//@ConfigurationProperties(prefix = "spring.redis")
//public class RedisConfig {
//
////    @Value("${spring.redis.host:localhost}")
//    private String redisHost;
//
////    @Value("${spring.redis.port:6379}")
//    private int redisPort;
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost,
//                redisPort);
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//
//    }
//
//    @Bean
//    public <T> RedisTemplate<String, T> redisTemplate() {
//        RedisTemplate<String, T> redisTemplate = new RedisTemplate<String, T>();
//
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        redisTemplate.afterPropertiesSet();
//
//        return redisTemplate;
//    }
//
//}