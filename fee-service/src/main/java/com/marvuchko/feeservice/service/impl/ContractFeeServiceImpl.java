package com.marvuchko.feeservice.service.impl;

import com.marvuchko.feeservice.data.entity.ContractFee;
import com.marvuchko.feeservice.data.repository.ContractFeeRepository;
import com.marvuchko.feeservice.service.ContractFeeService;
import com.marvuchko.feeservice.service.feign.TeamsAndPlayersFeignService;
import com.marvuchko.infrastructuremicroservice.exception.core.NotFoundException;
import com.marvuchko.infrastructuremicroservice.service.impl.RepositoryAwareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ContractFeeServiceImpl extends RepositoryAwareServiceImpl<Long, ContractFee, ContractFeeRepository>
        implements ContractFeeService {

    private TeamsAndPlayersFeignService teamsAndPlayersFeignService;

    public ContractFeeServiceImpl(ContractFeeRepository repository) {
        super(repository);
    }

    @Override
    public ContractFee create(ContractFee entity) {
        var contract = recalculateFeeForTeamAndPlayer(entity);
        return super.create(contract);
    }

    @Override
    public ContractFee update(Long id, ContractFee modifiedEntity) {
        var contract = recalculateFeeForTeamAndPlayer(modifiedEntity);
        return super.update(id, modifiedEntity);
    }

    private ContractFee recalculateFeeForTeamAndPlayer(ContractFee entity) {
        var player = teamsAndPlayersFeignService.getPlayerById(entity.getPlayerId());
        var team = teamsAndPlayersFeignService.getTeamById(entity.getTeamId());

        if (isNull(player) || isNull(team)) throw new NotFoundException();

        var playerAge = ChronoUnit.YEARS
                .between(player
                        .getDateOfBirth().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());

        var moneyPerMonthOfExperience = 100000f;

        var monthsOfExperience = ChronoUnit.MONTHS
                .between(player
                        .getCareerStartDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());

        var transferFee = monthsOfExperience * moneyPerMonthOfExperience / playerAge;
        var teamCommission = 0.1f * transferFee;
        var contractFee = transferFee + teamCommission;

        entity.setTransferFee(transferFee);
        entity.setTeamCommission(teamCommission);
        entity.setTotalFee(contractFee);

        var currency = nonNull(entity.getCurrency()) ? entity.getCurrency() : team.getPreferredCurrency();

        entity.setCurrency(currency);

        return entity;
    }

    @Autowired
    public void setTeamsAndPlayersFeignService(TeamsAndPlayersFeignService teamsAndPlayersFeignService) {
        this.teamsAndPlayersFeignService = teamsAndPlayersFeignService;
    }

    @Override
    public Set<ContractFee> deleteByPlayer(Long id) {
        return repository.deleteByPlayer(id);
    }

    @Override
    public Set<ContractFee> deleteByTeam(Long id) {
        return repository.deleteByTeam(id);
    }
}
