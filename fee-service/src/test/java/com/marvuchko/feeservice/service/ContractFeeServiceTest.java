package com.marvuchko.feeservice.service;

import com.marvuchko.feeservice.data.entity.ContractFee;
import com.marvuchko.feeservice.data.repository.ContractFeeRepository;
import com.marvuchko.feeservice.dto.feign.PlayerDto;
import com.marvuchko.feeservice.dto.feign.TeamDto;
import com.marvuchko.feeservice.service.feign.TeamsAndPlayersFeignService;
import com.marvuchko.feeservice.service.impl.ContractFeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ContractFeeServiceTest {

    @Mock
    private ContractFeeRepository contractFeeRepository;

    @Mock
    private TeamsAndPlayersFeignService teamsAndPlayersFeignService;

    @InjectMocks
    private ContractFeeServiceImpl contractFeeService;

    @Test
    void shouldSavedContractSuccessfully() {
        final var contractFee = new ContractFee();
        contractFee.setTeamId(1l);
        contractFee.setPlayerId(1l);

        var calendar = Calendar.getInstance();
        calendar.set(1997, 5, 5);

        final var player = new PlayerDto();
        player.setCareerStartDate(calendar.getTime());
        player.setDateOfBirth(calendar.getTime());

        final var team = new TeamDto();
        team.setName("Manchester United");
        team.setPreferredCurrency("USD");

        given(teamsAndPlayersFeignService.getPlayerById(1l)).willReturn(player);
        given(teamsAndPlayersFeignService.getTeamById(1l)).willReturn(team);
        given(contractFeeRepository.save(contractFee)).will(invocation -> invocation.getArgument(0));

        var savedContractFee = contractFeeService.create(contractFee);

        assertThat(savedContractFee).isNotNull();
        assertThat(savedContractFee.getCurrency()).isEqualTo("USD");
        assertThat(savedContractFee.getTeamCommission()).isGreaterThan(0);
        assertThat(savedContractFee.getTransferFee()).isGreaterThan(0);
        assertThat(savedContractFee.getTotalFee()).isGreaterThan(0);
        assertThat(savedContractFee.getTotalFee())
                .isEqualTo(savedContractFee.getTeamCommission() + savedContractFee.getTransferFee());

        verify(contractFeeRepository).save(any(ContractFee.class));
    }

}
