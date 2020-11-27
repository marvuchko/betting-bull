package com.marvuchko.teamsandplayersservice.configuration.database;

import com.marvuchko.teamsandplayersservice.data.repository.PlayerRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = PlayerRepository.class)
public class DatabaseConfiguration {
}
