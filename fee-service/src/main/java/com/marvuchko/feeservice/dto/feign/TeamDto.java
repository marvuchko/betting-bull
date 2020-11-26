package com.marvuchko.feeservice.dto.feign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marvuchko.infrastructuremicroservice.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDto extends BaseDto {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 2)
    private String preferredCurrency;

}
