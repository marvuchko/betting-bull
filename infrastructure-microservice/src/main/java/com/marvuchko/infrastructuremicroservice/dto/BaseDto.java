package com.marvuchko.infrastructuremicroservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class BaseDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createdAt;

    @ApiModelProperty(hidden = true)
    private LocalDateTime updatedAt;

    @ApiModelProperty(hidden = true)
    private String createdBy;

    @ApiModelProperty(hidden = true)
    private String updatedBy;

}