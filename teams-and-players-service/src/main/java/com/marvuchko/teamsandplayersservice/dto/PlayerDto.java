package com.marvuchko.teamsandplayersservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marvuchko.infrastructuremicroservice.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayerDto extends BaseDto {

    @NotNull
    @Size(min = 2)
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private Date careerStartDate;

}
