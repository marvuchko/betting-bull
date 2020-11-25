package com.marvuchko.infrastructuremicroservice.exception.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
public class ErrorDto {

    private String path;

    private int errorCode;

    private Set<String> errorMessages;

}
