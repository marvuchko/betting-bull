package com.marvuchko.feeservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marvuchko.feeservice.dto.feign.PlayerDto;
import com.marvuchko.feeservice.dto.feign.TeamDto;
import com.marvuchko.infrastructuremicroservice.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractFeeDto extends BaseDto {

    @NotNull
    private Long teamId;

    @NotNull
    private Long playerId;

    @NotNull
    private Float transferFee;

    @NotNull
    private Float teamCommission;

    @NotNull
    private Float totalFee;

    private PlayerDto player;

    private TeamDto team;

}
