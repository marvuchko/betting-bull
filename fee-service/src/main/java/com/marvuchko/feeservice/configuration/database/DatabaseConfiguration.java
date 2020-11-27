package com.marvuchko.feeservice.configuration.database;

import com.marvuchko.feeservice.data.repository.ContractFeeRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = ContractFeeRepository.class)
public class DatabaseConfiguration {
}
