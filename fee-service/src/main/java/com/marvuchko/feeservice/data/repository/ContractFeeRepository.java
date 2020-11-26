package com.marvuchko.feeservice.data.repository;

import com.marvuchko.feeservice.data.entity.ContractFee;
import com.marvuchko.infrastructuremicroservice.data.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ContractFeeRepository extends BaseRepository<Long, ContractFee> {

    @Query("DELETE FROM ContractFee c WHERE c.playerId = ?1")
    Set<ContractFee> deleteByPlayer(Long id);

    @Query("DELETE FROM ContractFee c WHERE c.teamId = ?1")
    Set<ContractFee> deleteByTeam(Long id);

}
