package com.marvuchko.feeservice.service.impl;

import com.marvuchko.feeservice.data.entity.ContractFee;
import com.marvuchko.feeservice.data.repository.ContractFeeRepository;
import com.marvuchko.feeservice.service.ContractFeeService;
import com.marvuchko.infrastructuremicroservice.service.impl.RepositoryAwareServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ContractFeeServiceImpl extends RepositoryAwareServiceImpl<Long, ContractFee, ContractFeeRepository>
        implements ContractFeeService {

    public ContractFeeServiceImpl(ContractFeeRepository repository) {
        super(repository);
    }
}
