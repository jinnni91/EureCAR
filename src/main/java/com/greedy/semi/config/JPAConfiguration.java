package com.greedy.semi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages= {"com.greedy.data"})
@EnableJpaRepositories(basePackages="com.greedy.data")
public class JPAConfiguration {

}
