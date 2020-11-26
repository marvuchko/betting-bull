package com.marvuchko.teamsandplayersservice.service.feign;

import com.marvuchko.infrastructuremicroservice.service.feign.BaseFeignService;
import com.marvuchko.teamsandplayersservice.dto.feign.ContractFeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = ContractFeeFeignService.REMOTE_MICROSERVICE)
public interface ContractFeeFeignService extends BaseFeignService {

    String REMOTE_MICROSERVICE = "fee-service";

    String DELETE_BY_PLAYER_PATH = "/player/{id}";

    String DELETE_BY_TEAM_PATH = "/team/{id}";

    @DeleteMapping(DELETE_BY_PLAYER_PATH)
    Set<ContractFeeDto> deleteByPlayer(@PathVariable Long id);

    @DeleteMapping(DELETE_BY_TEAM_PATH)
    Set<ContractFeeDto> deleteByTeam(@PathVariable Long id);

}
