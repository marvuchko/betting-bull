package com.marvuchko.teamsandplayersservice.dto.feign;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    private String currency;

}
