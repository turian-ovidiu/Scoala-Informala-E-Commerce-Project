package com.Ecommerce.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Ovi on 5/15/2017.
 */

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.Ecommerce.domain"})
@EnableJpaRepositories(basePackages = {"com.Ecommerce.repositories"})
@EnableTransactionManagement
public class RepositoryConfig {
}
