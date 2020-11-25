package com.marvuchko.feeservice.service;

import com.marvuchko.feeservice.data.entity.ContractFee;
import com.marvuchko.feeservice.data.repository.ContractFeeRepository;
import com.marvuchko.infrastructuremicroservice.service.RepositoryAwareService;

public interface ContractFeeService extends RepositoryAwareService<Long, ContractFee, ContractFeeRepository> {
}
