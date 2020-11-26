package com.marvuchko.feeservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marvuchko.feeservice.dto.feign.PlayerDto;
import com.marvuchko.feeservice.dto.feign.TeamDto;
import com.marvuchko.infrastructuremicroservice.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Transfers contract fee data.")
public class ContractFeeDto extends BaseDto {

    @NotNull
    private Long teamId;

    @NotNull
    private Long playerId;

    private Float transferFee;

    private Float teamCommission;

    private Float totalFee;

    @Size(max = 3)
    @ApiModelProperty(value = "Currency, not needed when sending data, Maximum length is 3 characters.", example = "USD")
    private String currency;

}
